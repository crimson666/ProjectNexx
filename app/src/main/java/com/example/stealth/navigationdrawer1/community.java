package com.example.stealth.navigationdrawer1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.home_db_recy.db_home;
import com.example.stealth.navigationdrawer1.post.db_post;

public class community extends AppCompatActivity {
    String me_id,user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {

            me_id = extras.getString("KEY");
            user_name = extras.getString("NAME");

        }

        Toast.makeText(getApplicationContext(),me_id,Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_community);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView rv= (RecyclerView) findViewById(R.id.post_recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        db_post d=new db_post(community.this,rv);
        d.execute();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(community.this,my_post.class);
                intent.putExtra("KEY",me_id);
                intent.putExtra("NAME",user_name);
                startActivity(intent);
            }
        });
    }

}
