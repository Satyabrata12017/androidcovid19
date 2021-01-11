package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Dashboardpage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardpage);

        toolbar = findViewById(R.id.toolbardash);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.stringopen, R.string.stringclose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.homebottom);



        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,home).commit();
                        Toast.makeText(Dashboardpage.this, "home clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_status:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,status).commit();
                        Toast.makeText(Dashboardpage.this, "status clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_center:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,center).commit();
                        Toast.makeText(Dashboardpage.this, "center clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_data:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,data).commit();
                        Toast.makeText(Dashboardpage.this, "data clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_share:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,share).commit();
                        Toast.makeText(Dashboardpage.this, "share clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_helpsupport:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,helpsupport).commit();
                        Toast.makeText(Dashboardpage.this, "hepsupport clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,settings).commit();
                        Toast.makeText(Dashboardpage.this, "settings clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }

                return true;
            }
        });

    }

    HomeFragment home = new HomeFragment();
    StatusFragment status = new StatusFragment();
    CenterFragment center = new CenterFragment();
    NotificationFragment notification = new NotificationFragment();
    ProfileFragment profile = new ProfileFragment();
    dataFragment data = new dataFragment();
    shareFragment share = new shareFragment();
    settingsFragment settings = new settingsFragment();
    helpsupportFragment helpsupport=new helpsupportFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.homebottom:
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,home).commit();
                return true;
            case R.id.statusbottom:
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,status).commit();
                return true;
            case R.id.centerbottom:
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,center).commit();
                return true;
            case R.id.notificationbottom:
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,notification).commit();
                return true;
            case R.id.profilebottom:
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,profile).commit();
                return true;
        }


        return false;
    }
}
