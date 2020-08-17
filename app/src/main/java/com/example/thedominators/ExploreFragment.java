package com.example.thedominators;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ExploreFragment extends Fragment {



    public ExploreFragment() {
        // Required empty public constructor
    }



    private DatabaseReference mDatabaseref;
    private List<Upload> UploadList;
    private List<Upload> filteredlist;
    private List<Upload> nearestlist;
    private List<Upload> cremlist,ghatlist,frontlist;
    ListView listView;
    // adapter class object
    DataAdapter adapter;
    SearchView search;
    //ProgressBar progressBar;
    //FirebaseAuth mAuth;
    Button add;
    Spinner filtertype , filterlocation;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;


    private static final double r2d = 180.0D / 3.141592653589793D;
    private static final double d2r = 3.141592653589793D / 180.0D;
    private static final double d2km = 111189.57696D * r2d;

    ArrayAdapter mAdapterloca , mAdapter;

    double latitude , longitude;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_explore, container, false);
        listView = v.findViewById(R.id.listview);

        search = v.findViewById(R.id.llsearch);
        //add= v.findViewById(R.id.show);

        //mAuth = FirebaseAuth.getInstance();
        //progressBar = v.findViewById(R.id.progressBar);
        filterlocation = v.findViewById(R.id.filterlocation);
       // filtertype = v.findViewById(R.id.filtertype);
        //progressBar.setVisibility(View.VISIBLE);

        mDatabaseref = FirebaseDatabase.getInstance().getReference().child("Uploaded Data");
        UploadList = new ArrayList<>();
        filteredlist = new ArrayList<>();
        cremlist = new ArrayList<>();
        ghatlist = new ArrayList<>();
        frontlist = new ArrayList<>();
        nearestlist = new ArrayList<>();

        mDatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UploadList.clear();
                for(DataSnapshot placeSnapshot : dataSnapshot.getChildren())
                {
                    Upload places = placeSnapshot.getValue(Upload.class);
                    UploadList.add(places);
                }
                //progressBar.setVisibility(View.GONE);
                adapter = new DataAdapter(getActivity(),UploadList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemposition = position;
                Upload upload = (Upload) listView.getItemAtPosition(position);
                String name = upload.getName();
                String imgurl = upload.getImgurl();
                String city = upload.getCity();
                String longi = upload.getLongi();
                String lati = upload.getLati();
                String about = upload.getAbout();
                String river = upload.getRiver();
                String type = upload.getType();
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("Name", name);
                intent.putExtra("Url", imgurl);
                intent.putExtra("Longi", longi);
                intent.putExtra("Lati", lati);
                intent.putExtra("city", city);
                intent.putExtra("about", about);
                intent.putExtra("river",river);
                intent.putExtra("type",river);
                startActivity(intent);
            }
        });


        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredlist.clear();
                if(search!=null)
                {
                    for (Upload upload:UploadList) {

                        if(upload.name.toLowerCase().contains(newText.toLowerCase()))
                            filteredlist.add(upload);
                    }
                }
                else
                    filteredlist = UploadList;

                adapter = new DataAdapter(getActivity(),filteredlist);
                listView.setAdapter(adapter);
                return false;
            }
        });








        List<String> loca = new ArrayList<>();
        loca.add("All");
        loca.add("Near Me");

        mAdapterloca=new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item,loca);
        mAdapterloca.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        filterlocation.setAdapter(mAdapterloca);



        filterlocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nearestlist.clear();

                String typetext = (String)parent.getItemAtPosition(position);

                if(typetext.equals("All"))
                {
                    adapter = new DataAdapter(getActivity(),UploadList);
                    listView.setAdapter(adapter);


                }
                else {

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
                    LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                        filterlocation.setSelection(0);
                    }
                    else
                    {
                        getCurrentLocation();
                        Upload near = new Upload("Vishram Ghat","https://firebasestorage.googleapis.com/v0/b/thedominators-f48f2.appspot.com/o/Uploaded%20Data%2F1596255744075.jpg?alt=media&token=79c8fa78-d29c-4ce9-bd5c-ed5b65ffad08","Mathura","This is where Krishna rested after killing the despotic ruler of mathura, the evil demon known as king kansa. when Lord Chaitanya visited Mathura, He also took bath here at Vishrama Ghata and rested here for some time. In ancient times, Lord Varahadev, after lifting the earth from the garbha queen and slaying the demon Hiranyaksha, also came here and rested. while He was resting at this place, Lord Varahadev spoke about the glories of Vraja Mandala to the goddes Bhumi Devi, the mother earth, and these description of holy dhama were recorded in the Adi-varaha purana. the word ‘vishrama‘or ‘vishranti‘ means to ‘rest‘ or a ‘resting place‘.","77.685865","27.504581","Yamuna","Ghat","Yes");
                        nearestlist.add(near);
                        Upload near1 = new Upload("Keshi Ghat","https://firebasestorage.googleapis.com/v0/b/thedominators-f48f2.appspot.com/o/Uploaded%20Data%2F1596256376417.jpg?alt=media&token=0cb41932-02c6-48ba-b829-bc062ced3a67","Mathura","Keshi Ghat is the principal bathing place in the town of Vrindavan. It is little east of Chir-ghat on the banks of the Yamuna. Keshi Ghat is one of the most beautiful ghats of Yamuna with stone inlaid palaces on the banks and massive Madanmohan temple visible in the backdrop. Here the sacred river Yamuna flows very graciously and extends herself to everyone without discrimination. Anyone who touches, drinks, sees, smells and bathes in her waters become infinitely purified. This ghat (series of steps leading down to a water body) is named after the pastime of Lord Krishna killing the demon Keshi.","77.700527","27.586452","Yamuna","Ghat","Yes");
                        nearestlist.add(near1);
                        adapter = new DataAdapter(getActivity(),nearestlist);
                        listView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;

    }
    private void getCurrentLocation() {

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
        LocationServices.getFusedLocationProviderClient(getActivity())
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(getActivity()).removeLocationUpdates(this);
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
            Toast.makeText(getActivity(), "Permision Denied", Toast.LENGTH_LONG).show();
        }
    }



}