package com.example.stealth.navigationdrawer1;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.stealth.navigationdrawer1.home_db_recy.db_home;


public class rec_dept extends AppCompatActivity {
   public String a = "";
   public String me_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_dept);




        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            a = extras.getString("KEY");
            me_id = extras.getString("ID");

        }

        final RecyclerView rv= (RecyclerView) findViewById(R.id.my_recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        db_home d=new db_home(rec_dept.this,a,rv,me_id);
        d.execute();

        getSupportActionBar().setTitle(a +" DEPARTMENT");


    }
}
