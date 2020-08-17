package com.example.thedominators;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomFragment extends Fragment {


    public HomFragment() {
        // Required empty public constructor
    }
    List<Upload> topList;
    private List<Upload> UploadList;
    private List<Upload> filteredlist;
    PlaceListAdapter adapter;
    DataAdapter mAdapter;

    ListView listView;
    RecyclerView recyclerTop;
    DatabaseReference mDatabaseref;
    ScrollView data;
    SearchView search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_hom, container, false);

        recyclerTop = v.findViewById(R.id.recyclerTop);
        listView = v.findViewById(R.id.listviewhome);
        data = v.findViewById(R.id.data);
        search = v.findViewById(R.id.llsearchhome);
        mDatabaseref = FirebaseDatabase.getInstance().getReference().child("Uploaded Data");

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerTop.setLayoutManager(mLayoutManager);
        recyclerTop.setItemAnimator(new DefaultItemAnimator());

        topList = new ArrayList<>();
        UploadList = new ArrayList<>();
        filteredlist = new ArrayList<>();

        mDatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                topList.clear();

                for (DataSnapshot placeSnapshot : dataSnapshot.getChildren()) {
                    Upload places = placeSnapshot.getValue(Upload.class);
                    topList.add(places);
                    if(topList.size()>5)
                        break;
                }

                adapter = new PlaceListAdapter(getActivity(),topList);
                recyclerTop.setAdapter(adapter);
                v.findViewById(R.id.prgrshome).setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
                mAdapter = new DataAdapter(getActivity(),UploadList);
                listView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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

                listView.setVisibility(View.VISIBLE);
                data.setVisibility(View.GONE);
                if(search!=null)
                {

                    for (Upload upload:UploadList) {

                        if(upload.name.toLowerCase().contains(newText.toLowerCase()))
                            filteredlist.add(upload);
                    }
                    mAdapter = new DataAdapter(getActivity(),filteredlist);
                    listView.setAdapter(mAdapter);
                }

                return false;
            }
        });

        v.findViewById(R.id.article).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="http://gangaghat.ezyro.com/?i=1";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        return v;

    }



}