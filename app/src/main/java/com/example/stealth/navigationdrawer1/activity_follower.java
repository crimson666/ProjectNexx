package com.example.stealth.navigationdrawer1;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.follower.db_follower;
import com.example.stealth.navigationdrawer1.home_db_recy.db_home;

public class activity_follower extends AppCompatActivity {

    String str_eid="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            str_eid= extras.getString("KEY");
        }

        Toast.makeText(getApplicationContext(),str_eid,Toast.LENGTH_LONG).show();

        getSupportActionBar().setTitle("FOLLOWER");

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Light.ttf");


        final RecyclerView rv= (RecyclerView) findViewById(R.id.my_recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        db_follower d=new db_follower(activity_follower.this,str_eid,rv);
        d.execute();

    }
}
