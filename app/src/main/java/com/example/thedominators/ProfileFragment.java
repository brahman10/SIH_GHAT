package com.example.thedominators;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {



    public ProfileFragment() {
        // Required empty public constructor
    }

    FirebaseAuth mAuth ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth= FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null){
            View v = inflater.inflate(R.layout.demo_profile, container, false);
            v.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
            });
            return v;
        }
        else{
            View v= inflater.inflate(R.layout.fragment_profile, container, false);
            ImageView imageView = v.findViewById(R.id.profileimage);
            final TextView textName = v.findViewById(R.id.name);
            TextView textEmail = v.findViewById(R.id.email);
            TextView logout = v.findViewById(R.id.logout);

            FirebaseUser user = mAuth.getCurrentUser();
            String userid = user.getUid();

            DatabaseReference userref = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
            userref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        if(data.getKey().equals("Name"))
                            textName.setText(data.getValue().toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            if(user.getPhotoUrl()!=null) {
                Glide.with(this)
                        .load(user.getPhotoUrl())
                        .into(imageView);
            }
            else
                imageView.setImageResource(R.drawable.ic_baseline_account_circle_24);

            //textName.setText(user.getDisplayName());
            textEmail.setText(user.getEmail());

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }
            });
            v.findViewById(R.id.addplace).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),UploadActivity.class));
                }
            });
            v.findViewById(R.id.visited).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),Visited.class));
                }
            });
            v.findViewById(R.id.favourite).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),Favourites.class));
                }
            });
            v.findViewById(R.id.connect).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),Connect.class));
                }
            });
            v.findViewById(R.id.later).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),VisitLater.class));
                }
            });
            v.findViewById(R.id.subarticle).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url ="http://gangaghat.ezyro.com/submit-your-articles/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }
            });

            v.findViewById(R.id.meal).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url ="https://www.zomato.com";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }
            });

            v.findViewById(R.id.cab).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url ="https://www.olacabs.com";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }
            });

            v.findViewById(R.id.hotel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url ="https://www.makemytrip.com";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }
            });
            return v;


        }

    }
}