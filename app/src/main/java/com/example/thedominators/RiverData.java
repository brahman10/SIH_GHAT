package com.example.thedominators;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RiverData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river_data);

        TextView name = findViewById(R.id.rivername);
        TextView source = findViewById(R.id.riversource);
        TextView destname = findViewById(R.id.riverdest);
        TextView cityname = findViewById(R.id.rivercity);
        TextView ghatname = findViewById(R.id.riverghat);
        TextView length = findViewById(R.id.riverlen);
        TextView area = findViewById(R.id.riverarea);
        ProgressBar progressBar = findViewById(R.id.progressBar1);


        Bundle bun = getIntent().getExtras();
        String rivername , riversource,dest,city,ghat,len , ar;
        rivername= bun.getString("Name");
        riversource = bun.getString("Source");
        dest = bun.getString("Destination");
        city = bun.getString("city");
        ghat = bun.getString("ghat");
        len = bun.getString("Length");
        ar = bun.getString("Area");
        progressBar.setVisibility(View.GONE);


        name.setText(rivername);
        source.setText(riversource);
        destname.setText(dest);
        cityname.setText(city);
        ghatname.setText(ghat);
        length.setText(len);
        area.setText(ar);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
