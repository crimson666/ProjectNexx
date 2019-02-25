package com.example.stealth.navigationdrawer1;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.stealth.navigationdrawer1.admin.db_authentication;
import com.example.stealth.navigationdrawer1.following.db_following;

import static android.webkit.WebSettings.PluginState.ON;
import static java.util.logging.Level.OFF;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar=null;
    NavigationView navigationView=null;
    ToggleButton toggleButton1;
    //TextView name,eid;
    public String str_eid="";
    public String a;
    public String usr_name;
    TextView name,eid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//problem with the data passing
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            a= extras.getString("KEY");
            usr_name=extras.getString("KEY2");

        }
       // Toast.makeText(getApplicationContext(),a,Toast.LENGTH_LONG).show();

       //NavigationView navigationView = (NavigationView) findViewById(R.id.nave_head);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        name = (TextView) headerView.findViewById(R.id.name_nav);
       eid = (TextView) headerView.findViewById(R.id.eid_nav);
        name.setText(usr_name);
        eid.setText(a);




        //set the main fragment
        home_frag Frag_home = new home_frag(a);
        android.support.v4.app.FragmentTransaction fragmentTransaction=
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_contain,Frag_home);
        fragmentTransaction.commit();



         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

       // eid=(TextView)findViewById(R.id.eid_nav);




    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        str_eid="" + a;



        if (id == R.id.home) {
            //set the main fragment
            home_frag Frag_home = new home_frag(a);
            android.support.v4.app.FragmentTransaction fragmentTransaction=
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_contain,Frag_home);
            fragmentTransaction.commit();

            //title set
            getSupportActionBar().setTitle("HOME");
        }

        /*
        else if (id == R.id.location) {
            //set the main fragment
            frag2 Fragment2 = new frag2();
            android.support.v4.app.FragmentTransaction fragmentTransaction=
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_contain,Fragment2);
            fragmentTransaction.commit();
            //title set
            getSupportActionBar().setTitle("LOCATION");

        }*/
        else if (id == R.id.profile) {
            Intent intent = new Intent(this,me_profile.class);
            intent.putExtra("KEY",a);


            startActivity(intent);

        }

        else if (id == R.id.follower) {
            Intent intent = new Intent(this,activity_follower.class);
            intent.putExtra("KEY",a);
            startActivity(intent);

        }


        else if (id == R.id.following) {
            Intent intent = new Intent(this,activity_following.class);
            intent.putExtra("KEY",a);
            startActivity(intent);

        }
        else if (id == R.id.community) {

            Intent intent = new Intent(this,community.class);
            intent.putExtra("KEY",a);
            intent.putExtra("NAME",usr_name);
            startActivity(intent);

        }

        else if (id == R.id.carrier) {

            Intent intent = new Intent(this,jobpost.class);
            intent.putExtra("KEY",a);
            intent.putExtra("NAME",usr_name);
            intent.putExtra("TYPE","USER");
            startActivity(intent);

        }


        else if (id == R.id.admin) {

            db_authentication d=new db_authentication(this,a,usr_name);
            d.execute();

        }

        else if (id == R.id.logout) {
            finish();
        }




            return true;



    }



}
