package com.example.thedominators;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class DetailsActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        final String name = extras.getString("Name");
        final String url = extras.getString("Url");
        final String city = extras.getString("city");
        final String about = extras.getString("about");
        final String lati = extras.getString("Lati");
        final String longi = extras.getString("Longi");
        final String river = extras.getString("river");
        final String type = extras.getString("type");
        TextView naam = findViewById(R.id.name);
        ImageView urll = findViewById(R.id.url);
        TextView cityy = findViewById(R.id.city);
        TextView aboutt = findViewById(R.id.about);
        TextView riverr = findViewById(R.id.river);

        naam.setText(name.toString());
        Glide.with(this).load(url).into(urll);
        cityy.setText(city.toString());
        aboutt.setText(about.toString());
        riverr.setText(river.toString());
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            userID=mAuth.getCurrentUser().getUid();
        }
        else
        {
            findViewById(R.id.addfav).setVisibility(View.GONE);
            findViewById(R.id.addlater).setVisibility(View.GONE);
            findViewById(R.id.addvisited).setVisibility(View.GONE);
        }


        findViewById(R.id.txtlocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailsActivity.this, LocationActivity.class);
                intent.putExtra("Lati",lati);
                intent.putExtra("Longi",longi);
                intent.putExtra("Name",name);
                startActivity(intent);
            }
        });
        findViewById(R.id.addfav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("Favourites");
                Upload upload=new Upload(name,url,city,about,longi,lati,river,type.toString(),"yes");
                String uploadId=mDatabaseRef.push().getKey();
                mDatabaseRef.child(uploadId).setValue(upload).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Added Succesfully",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        findViewById(R.id.addvisited).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("Visited");
                Upload upload=new Upload(name,url,city,about,longi,lati,river,type.toString(),"yes");
                String uploadId=mDatabaseRef.push().getKey();
                mDatabaseRef.child(uploadId).setValue(upload).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Added Succesfully",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        findViewById(R.id.addlater).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("Later");
                Upload upload=new Upload(name,url,city,about,longi,lati,river,type.toString(),"yes");
                String uploadId=mDatabaseRef.push().getKey();
                mDatabaseRef.child(uploadId).setValue(upload).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Added Succesfully",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });



    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}