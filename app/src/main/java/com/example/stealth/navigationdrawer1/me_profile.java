package com.example.stealth.navigationdrawer1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.exp.db_exp;
import com.example.stealth.navigationdrawer1.profile_backgroud.db_myprofile;
import com.example.stealth.navigationdrawer1.rec_education_profile.db_profile_edu;
import com.example.stealth.navigationdrawer1.rec_exp_profile.db_profile_exp;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class me_profile extends AppCompatActivity {

    public String str_eid,str_name;
    Context c;


    TextView eid;
   TextView name;
    TextView position;

    TextView about;
    TextView phone;
    TextView email;
    TextView address;
    TextView fb;
    TextView linkedin,follower,following,exp_yr,exp,skill;

    ImageButton edit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_profile);


        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            str_eid= extras.getString("KEY");

        }

        Toast.makeText(getApplicationContext(),str_eid,Toast.LENGTH_LONG).show();

        eid=(TextView)findViewById(R.id.eid);
        name=(TextView)findViewById(R.id.name);
        position=(TextView)findViewById(R.id.position);

        about=(TextView)findViewById(R.id.about);
        phone=(TextView)findViewById(R.id.phone);
        email=(TextView)findViewById(R.id.email);
        address=(TextView)findViewById(R.id.address);
        fb=(TextView)findViewById(R.id.fb);
        linkedin=(TextView)findViewById(R.id.linkedin);
        edit=(ImageButton)findViewById(R.id.edit_btn);
        follower=(TextView)findViewById(R.id.num_follower);
        following=(TextView)findViewById(R.id.num_following);
        exp_yr=(TextView)findViewById(R.id.exp_yr);
        exp=(TextView)findViewById(R.id.exp);
        skill=(TextView)findViewById(R.id.skill);


        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Light.ttf");

        name.setTypeface(myCustomFont2);
        position.setTypeface(myCustomFont2);
        about.setTypeface(myCustomFont2);
        phone.setTypeface(myCustomFont2);
        email.setTypeface(myCustomFont2);
        address.setTypeface(myCustomFont2);
        eid.setTypeface(myCustomFont2);
        follower.setTypeface(myCustomFont2);
        following.setTypeface(myCustomFont2);
        exp.setTypeface(myCustomFont2);
        skill.setTypeface(myCustomFont2);




        db_myprofile d=new db_myprofile(me_profile.this,str_eid,eid,name,position,about,phone,email,address,fb,linkedin,following,follower,skill);
        d.execute();


        final RecyclerView rv_edu= (RecyclerView) findViewById(R.id.recycler_view_edu);
        rv_edu.setLayoutManager(new LinearLayoutManager(this));
        rv_edu.setItemAnimator(new DefaultItemAnimator());
        db_profile_edu ed=new db_profile_edu(me_profile.this,str_eid,rv_edu);
        ed.execute();



        final RecyclerView rv_exp= (RecyclerView) findViewById(R.id.recycler_view_exp);
        rv_exp.setLayoutManager(new LinearLayoutManager(this));
        rv_exp.setItemAnimator(new DefaultItemAnimator());
        db_profile_exp ex=new db_profile_exp(me_profile.this,str_eid,rv_exp);
        ex.execute();

        db_exp backWork3 = new db_exp(c,"exp",str_eid,exp_yr);
        backWork3.execute();



        //getSupportActionBar().setTitle("MY PROFILE");
    }

    public void edit_profile(View view) {
        Intent intent = new Intent(me_profile.this,edit_profile.class);
        intent.putExtra("EID",eid.getText());
        intent.putExtra("NAME",name.getText());
        intent.putExtra("PHONE",phone.getText());
        intent.putExtra("EMAIL",email.getText());
        intent.putExtra("ADDRESS",address.getText());
        intent.putExtra("FB",fb.getText().toString());
        intent.putExtra("LINKEDIN",linkedin.getText().toString());
        intent.putExtra("ABOUT",about.getText());
        intent.putExtra("SKILL",skill.getText());

        me_profile.this.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(me_profile.this,MainActivity.class);
        intent.putExtra("KEY",eid.getText());
        intent.putExtra("KEY2",name.getText());


        me_profile.this.startActivity(intent);
    }
}
