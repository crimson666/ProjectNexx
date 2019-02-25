package com.example.stealth.navigationdrawer1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.stealth.navigationdrawer1.jobs.db_job;

public class admin_joblist extends AppCompatActivity {
    String eid,user_name,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_joblist);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            eid = extras.getString("KEY");
            user_name = extras.getString("NAME");
            type = extras.getString("TYPE");

        }

        final RecyclerView rv= (RecyclerView) findViewById(R.id.admin_recycler_job);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        db_job d= new db_job(this,rv,eid,user_name,type);
        d.execute();





    }
}