package com.example.thedominators;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Visited extends AppCompatActivity {

    DataAdapter adapter;
    private DatabaseReference mDatabaseref;
    private List<Upload> UploadList;
    ListView listView;
    FirebaseAuth mAuth;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_later);

        listView = findViewById(R.id.listlater);
        mAuth = FirebaseAuth.getInstance();
        userID=mAuth.getCurrentUser().getUid();
        mDatabaseref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("Visited");
        UploadList = new ArrayList<>();
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
                adapter = new DataAdapter(Visited.this,UploadList);
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
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
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
    }
}