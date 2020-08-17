package com.example.thedominators;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    TapBarMenu tapBarMenu;
    ImageView home , explore , listed, dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tapBarMenu = findViewById(R.id.tapBarMenu);
        home = findViewById(R.id.home_frag);
        explore = findViewById(R.id.work);
        listed = findViewById(R.id.profile);
        dashboard = findViewById(R.id.dashboard);

        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuButtonClick();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.toggle();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new HomFragment()).commit();
            }
        });
        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.toggle();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new ExploreFragment()).commit();
            }
        });
        listed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.toggle();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new RiverFragment()).commit();
            }
        });
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.toggle();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new ProfileFragment()).commit();
            }
        });
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new HomFragment()).commit();

        }

    }
    private void onMenuButtonClick(){
        tapBarMenu.toggle();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}