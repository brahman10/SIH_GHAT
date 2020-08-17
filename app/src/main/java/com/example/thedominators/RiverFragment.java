package com.example.thedominators;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RiverFragment extends Fragment {



    public RiverFragment() {
        // Required empty public constructor
    }

    private DatabaseReference ref;
    RiverAdapter adapter;

    ProgressBar progressBar;
    ListView listRiver;
    List<River> listriver;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_river, container, false);

        LinearLayout btnganga = v.findViewById(R.id.btnganga);
        LinearLayout btngomti = v.findViewById(R.id.btngomti);
        LinearLayout btnghag = v.findViewById(R.id.btnghag);
        LinearLayout btngandak = v.findViewById(R.id.btngandak);
        LinearLayout btnyam = v.findViewById(R.id.btnyam);
        LinearLayout btnson = v.findViewById(R.id.btnson);
        LinearLayout btnkosi = v.findViewById(R.id.btnkosi);
        LinearLayout btnpun = v.findViewById(R.id.btnpun);
        LinearLayout btnmaha = v.findViewById(R.id.btnmaha);
        LinearLayout btnramg = v.findViewById(R.id.btnramg);
        LinearLayout btntamsa = v.findViewById(R.id.btntamsa);
        progressBar=v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        ref= FirebaseDatabase.getInstance().getReference().child("DATA");
        btnganga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ref= FirebaseDatabase.getInstance().getReference().child("DATA").child("GANGA");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String name="GANGA";
                        String source = dataSnapshot.child("Source").getValue().toString();
                        String dest = dataSnapshot.child("Destination").getValue().toString();
                        String len = dataSnapshot.child("Length").getValue().toString();
                        String ar = dataSnapshot.child("Area").getValue().toString();
                        String city = dataSnapshot.child("Major Cities").getValue().toString();
                        String ghat = dataSnapshot.child("Major Ghats").getValue().toString();
                        Intent Nextpage=new Intent(getActivity(),RiverData.class);
                        Nextpage.putExtra("Name",name);
                        Nextpage.putExtra("Source",source);
                        Nextpage.putExtra("Destination",dest);
                        Nextpage.putExtra("Length",len);
                        Nextpage.putExtra("Area",ar);
                        Nextpage.putExtra("city",city);
                        Nextpage.putExtra("ghat",ghat);
                        startActivity(Nextpage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btngomti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ref= FirebaseDatabase.getInstance().getReference().child("DATA").child("GOMTI");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name="GOMTI";
                        String source = dataSnapshot.child("Source").getValue().toString();
                        String dest = dataSnapshot.child("Destination").getValue().toString();
                        String len = dataSnapshot.child("Length").getValue().toString();
                        String ar = dataSnapshot.child("Area").getValue().toString();
                        String city = dataSnapshot.child("Major Cities").getValue().toString();
                        String ghat = dataSnapshot.child("Major Ghats").getValue().toString();
                        Intent Nextpage=new Intent(getActivity(),RiverData.class);
                        Nextpage.putExtra("Name",name);
                        Nextpage.putExtra("Source",source);
                        Nextpage.putExtra("Destination",dest);
                        Nextpage.putExtra("Length",len);
                        Nextpage.putExtra("Area",ar);
                        Nextpage.putExtra("city",city);
                        Nextpage.putExtra("ghat",ghat);
                        startActivity(Nextpage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnghag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ref= FirebaseDatabase.getInstance().getReference().child("DATA").child("GHAGHARA");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name="GHAGHARA";
                        String source = dataSnapshot.child("Source").getValue().toString();
                        String dest = dataSnapshot.child("Destination").getValue().toString();
                        String len = dataSnapshot.child("Length").getValue().toString();
                        String ar = dataSnapshot.child("Area").getValue().toString();
                        String city = dataSnapshot.child("Major Cities").getValue().toString();
                        String ghat = dataSnapshot.child("Major Ghats").getValue().toString();
                        Intent Nextpage=new Intent(getActivity(),RiverData.class);
                        Nextpage.putExtra("Name",name);
                        Nextpage.putExtra("Source",source);
                        Nextpage.putExtra("Destination",dest);
                        Nextpage.putExtra("Length",len);
                        Nextpage.putExtra("Area",ar);
                        Nextpage.putExtra("city",city);
                        Nextpage.putExtra("ghat",ghat);
                        startActivity(Nextpage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btngandak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ref= FirebaseDatabase.getInstance().getReference().child("DATA").child("GANDAK");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name="GANDAK";
                        String source = dataSnapshot.child("Source").getValue().toString();
                        String dest = dataSnapshot.child("Destination").getValue().toString();
                        String len = dataSnapshot.child("Length").getValue().toString();
                        String ar = dataSnapshot.child("Area").getValue().toString();
                        String city = dataSnapshot.child("Major Cities").getValue().toString();
                        String ghat = dataSnapshot.child("Major Ghats").getValue().toString();
                        Intent Nextpage=new Intent(getActivity(),RiverData.class);
                        Nextpage.putExtra("Name",name);
                        Nextpage.putExtra("Source",source);
                        Nextpage.putExtra("Destination",dest);
                        Nextpage.putExtra("Length",len);
                        Nextpage.putExtra("Area",ar);
                        Nextpage.putExtra("city",city);
                        Nextpage.putExtra("ghat",ghat);
                        startActivity(Nextpage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btnramg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ref= FirebaseDatabase.getInstance().getReference().child("DATA").child("RAMGANGA");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name="RAMGANGA";
                        String source = dataSnapshot.child("Source").getValue().toString();
                        String dest = dataSnapshot.child("Destination").getValue().toString();
                        String len = dataSnapshot.child("Length").getValue().toString();
                        String ar = dataSnapshot.child("Area").getValue().toString();
                        String city = dataSnapshot.child("Major Cities").getValue().toString();
                        String ghat = dataSnapshot.child("Major Ghats").getValue().toString();
                        Intent Nextpage=new Intent(getActivity(),RiverData.class);
                        Nextpage.putExtra("Name",name);
                        Nextpage.putExtra("Source",source);
                        Nextpage.putExtra("Destination",dest);
                        Nextpage.putExtra("Length",len);
                        Nextpage.putExtra("Area",ar);
                        Nextpage.putExtra("city",city);
                        Nextpage.putExtra("ghat",ghat);
                        startActivity(Nextpage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btnkosi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ref= FirebaseDatabase.getInstance().getReference().child("DATA").child("KOSI");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name="KOSI";
                        String source = dataSnapshot.child("Source").getValue().toString();
                        String dest = dataSnapshot.child("Destination").getValue().toString();
                        String len = dataSnapshot.child("Length").getValue().toString();
                        String ar = dataSnapshot.child("Area").getValue().toString();
                        String city = dataSnapshot.child("Major Cities").getValue().toString();
                        String ghat = dataSnapshot.child("Major Ghats").getValue().toString();
                        Intent Nextpage=new Intent(getActivity(),RiverData.class);
                        Nextpage.putExtra("Name",name);
                        Nextpage.putExtra("Source",source);
                        Nextpage.putExtra("Destination",dest);
                        Nextpage.putExtra("Length",len);
                        Nextpage.putExtra("Area",ar);
                        Nextpage.putExtra("city",city);
                        Nextpage.putExtra("ghat",ghat);
                        startActivity(Nextpage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnmaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ref= FirebaseDatabase.getInstance().getReference().child("DATA").child("MAHANANDA");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name="MAHANANDA";
                        String source = dataSnapshot.child("Source").getValue().toString();
                        String dest = dataSnapshot.child("Destination").getValue().toString();
                        String len = dataSnapshot.child("Length").getValue().toString();
                        String ar = dataSnapshot.child("Area").getValue().toString();
                        String city = dataSnapshot.child("Major Cities").getValue().toString();
                        String ghat = dataSnapshot.child("Major Ghats").getValue().toString();
                        Intent Nextpage=new Intent(getActivity(),RiverData.class);
                        Nextpage.putExtra("Name",name);
                        Nextpage.putExtra("Source",source);
                        Nextpage.putExtra("Destination",dest);
                        Nextpage.putExtra("Length",len);
                        Nextpage.putExtra("Area",ar);
                        Nextpage.putExtra("city",city);
                        Nextpage.putExtra("ghat",ghat);
                        startActivity(Nextpage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btnyam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ref= FirebaseDatabase.getInstance().getReference().child("DATA").child("Yamuna");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name="YAMUNA";
                        String source = dataSnapshot.child("Source").getValue().toString();
                        String dest = dataSnapshot.child("Destination").getValue().toString();
                        String len = dataSnapshot.child("Length").getValue().toString();
                        String ar = dataSnapshot.child("Area").getValue().toString();
                        String city = dataSnapshot.child("Major Cities").getValue().toString();
                        String ghat = dataSnapshot.child("Major Ghats").getValue().toString();
                        Intent Nextpage=new Intent(getActivity(),RiverData.class);
                        Nextpage.putExtra("Name",name);
                        Nextpage.putExtra("Source",source);
                        Nextpage.putExtra("Destination",dest);
                        Nextpage.putExtra("Length",len);
                        Nextpage.putExtra("Area",ar);
                        Nextpage.putExtra("city",city);
                        Nextpage.putExtra("ghat",ghat);
                        startActivity(Nextpage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btnson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ref= FirebaseDatabase.getInstance().getReference().child("DATA").child("SON");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name="SON";
                        String source = dataSnapshot.child("Source").getValue().toString();
                        String dest = dataSnapshot.child("Destination").getValue().toString();
                        String len = dataSnapshot.child("Length").getValue().toString();
                        String ar = dataSnapshot.child("Area").getValue().toString();
                        String city = dataSnapshot.child("Major Cities").getValue().toString();
                        String ghat = dataSnapshot.child("Major Ghats").getValue().toString();
                        Intent Nextpage=new Intent(getActivity(),RiverData.class);
                        Nextpage.putExtra("Name",name);
                        Nextpage.putExtra("Source",source);
                        Nextpage.putExtra("Destination",dest);
                        Nextpage.putExtra("Length",len);
                        Nextpage.putExtra("Area",ar);
                        Nextpage.putExtra("city",city);
                        Nextpage.putExtra("ghat",ghat);
                        startActivity(Nextpage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnpun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ref= FirebaseDatabase.getInstance().getReference().child("DATA").child("PUNPUN");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name="PUNPUN";
                        String source = dataSnapshot.child("Source").getValue().toString();
                        String dest = dataSnapshot.child("Destination").getValue().toString();
                        String len = dataSnapshot.child("Length").getValue().toString();
                        String ar = dataSnapshot.child("Area").getValue().toString();
                        String city = dataSnapshot.child("Major Cities").getValue().toString();
                        String ghat = dataSnapshot.child("Major Ghats").getValue().toString();
                        Intent Nextpage=new Intent(getActivity(),RiverData.class);
                        Nextpage.putExtra("Name",name);
                        Nextpage.putExtra("Source",source);
                        Nextpage.putExtra("Destination",dest);
                        Nextpage.putExtra("Length",len);
                        Nextpage.putExtra("Area",ar);
                        Nextpage.putExtra("city",city);
                        Nextpage.putExtra("ghat",ghat);
                        startActivity(Nextpage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btntamsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ref= FirebaseDatabase.getInstance().getReference().child("DATA").child("TAMSA");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name="TAMSA";
                        String source = dataSnapshot.child("Source").getValue().toString();
                        String dest = dataSnapshot.child("Destination").getValue().toString();
                        String len = dataSnapshot.child("Length").getValue().toString();
                        String ar = dataSnapshot.child("Area").getValue().toString();
                        String city = dataSnapshot.child("Major Cities").getValue().toString();
                        String ghat = dataSnapshot.child("Major Ghats").getValue().toString();
                        Intent Nextpage=new Intent(getActivity(),RiverData.class);
                        Nextpage.putExtra("Name",name);
                        Nextpage.putExtra("Source",source);
                        Nextpage.putExtra("Destination",dest);
                        Nextpage.putExtra("Length",len);
                        Nextpage.putExtra("Area",ar);
                        Nextpage.putExtra("city",city);
                        Nextpage.putExtra("ghat",ghat);
                        startActivity(Nextpage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);

    }
}