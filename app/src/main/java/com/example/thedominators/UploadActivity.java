package com.example.thedominators;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class UploadActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST =1;

    private ImageButton mButtonChooseImage;
    private ImageButton mButtonUpload;
    private EditText mEditTextFileName , etLong , etLati, etAbout , etCity, etRiver;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    private  String valid = "no";
    private Spinner spinner;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private double latitude , longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        mButtonChooseImage = findViewById(R.id.btnchs);
        mButtonUpload = findViewById(R.id.btnupload);
        mEditTextFileName = findViewById(R.id.etName);
        spinner=findViewById(R.id.etType);
        etAbout = findViewById(R.id.etAbout);
        etCity = findViewById(R.id.etCity);
        etRiver = findViewById(R.id.etRiver);
        mImageView = findViewById(R.id.imgview);
        mStorageRef = FirebaseStorage.getInstance().getReference("Uploaded Data");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Uploaded Data");
        mProgressBar = findViewById(R.id.progressbar);
        mImageView.setVisibility(View.GONE);


        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();

            }
        });
        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUploadTask!= null&& mUploadTask.isInProgress()){
                    Toast.makeText(UploadActivity.this,"Upload in Progress",Toast.LENGTH_LONG).show();
                }
                else {
                    uploadFile();
                }

            }
        });
        findViewById(R.id.btnback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UploadActivity.this,MainActivity.class));

            }
        });

    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK
                && data!= null && data.getData()!=null){
            mImageUri=data.getData();
            mImageView.setVisibility(View.VISIBLE);
            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR=getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if(mImageUri!=null){
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
            mUploadTask=fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            },500);
                            Toast.makeText(UploadActivity.this,"Uplaod Successfull",Toast.LENGTH_SHORT).show();

                           /* Upload upload = new Upload(mEditTextFileName.getText().toString().trim(),
                                    taskSnapshot.getStorage().getDownloadUrl().toString());
                            String uploadId=mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);*/
                            Task<Uri> urlTask= taskSnapshot.getStorage().getDownloadUrl();
                            while(!urlTask.isSuccessful());
                            Uri downloadUrl=urlTask.getResult();
                            Upload upload=new Upload(mEditTextFileName.getText().toString().trim(),downloadUrl.toString(),etCity.getText().toString(),etAbout.getText().toString(),String.valueOf(longitude),String.valueOf(latitude),etRiver.getText().toString(),spinner.getSelectedItem().toString(),valid);
                            String uploadId=mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);
                            Intent intent = new Intent (UploadActivity.this , MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UploadActivity.this , e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>(){
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot){

                            mProgressBar.setVisibility(View.VISIBLE);
                            double progress = (100.0*taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int)progress);
                            ProgressDialog progressDialog = new ProgressDialog(UploadActivity.this);
                            progressDialog.setMessage("Uploading");
                        }
                    });
        }
        else{
            Toast.makeText(this,"No File Selected",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityCompat.requestPermissions(UploadActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
        LocationManager locationManager = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
            builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {
            getCurrentLocation();
        }
    }
    private void getCurrentLocation() {

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(UploadActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
            builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        LocationServices.getFusedLocationProviderClient(UploadActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(UploadActivity.this).removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestlocationindex = locationResult.getLocations().size() - 1;
                            latitude = locationResult.getLocations().get(latestlocationindex).getLatitude();
                            longitude = locationResult.getLocations().get(latestlocationindex).getLongitude();

                        }
                    }
                }, Looper.getMainLooper());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            getCurrentLocation();
        } else {
            Toast.makeText(UploadActivity.this, "Permision Denied", Toast.LENGTH_LONG).show();
        }
    }
}
