package com.example.stealth.navigationdrawer1;

import android.app.Activity;
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

import com.example.stealth.navigationdrawer1.db.db_login;
import com.example.stealth.navigationdrawer1.exp.db_exp;
import com.example.stealth.navigationdrawer1.follow_btn_pack.db_follow_btn;
import com.example.stealth.navigationdrawer1.home_db_recy.db_home;
import com.example.stealth.navigationdrawer1.rec_education_profile.db_profile_edu;
import com.example.stealth.navigationdrawer1.rec_exp_profile.db_profile_exp;
import com.example.stealth.navigationdrawer1.vsited.db_visited;


public class profile extends Activity {
    String str_eid,me_id;
    TextView eid,name,position,about,phone,email,address,fb,linkedin,following,follower,exp_yr,exp,skill;
    ImageButton follow_btn;
    Context c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        eid=(TextView)findViewById(R.id.eid);
        name=(TextView)findViewById(R.id.name);
        position=(TextView)findViewById(R.id.position);
        about=(TextView)findViewById(R.id.about);
        phone=(TextView)findViewById(R.id.phone);
        email=(TextView)findViewById(R.id.email);
        address=(TextView)findViewById(R.id.address);
        fb=(TextView)findViewById(R.id.fb);
        linkedin=(TextView)findViewById(R.id.linkedin);
        following=(TextView)findViewById(R.id.num_following);
        follower=(TextView)findViewById(R.id.num_follower);
        follow_btn=(ImageButton)findViewById(R.id.follow_btn);
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



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name.setText(extras.getString("fname") + " "+extras.getString("lname"));

            position.setText( extras.getString("position") + ", "+ extras.getString("dept"));
            about.setText( extras.getString("about"));
            eid.setText(extras.getString("eid"));
            phone.setText( extras.getString("phone"));
            email.setText(extras.getString("email"));
            address.setText(
                    extras.getString("address"));
            fb.setText(extras.getString("fb"));
           linkedin.setText(extras.getString("linkedin"));
           str_eid=extras.getString("eid");
            me_id=extras.getString("ME_ID");
            follower.setText(extras.getString("follower"));
            following.setText(extras.getString("following"));
            skill.setText(extras.getString("skill"));



        }



        final RecyclerView rv_edu= (RecyclerView) findViewById(R.id.recycler_view_edu);
        rv_edu.setLayoutManager(new LinearLayoutManager(this));
        rv_edu.setItemAnimator(new DefaultItemAnimator());
        db_profile_edu d=new db_profile_edu(profile.this,str_eid,rv_edu);
        d.execute();


        final RecyclerView rv_exp= (RecyclerView) findViewById(R.id.recycler_view_exp);
        rv_exp.setLayoutManager(new LinearLayoutManager(this));
        rv_exp.setItemAnimator(new DefaultItemAnimator());
        db_profile_exp e=new db_profile_exp(profile.this,str_eid,rv_exp);
        e.execute();

        db_visited backWork2 = new db_visited(this);
        backWork2.execute("visit",me_id,str_eid);


        db_exp backWork3 = new db_exp(c,"exp",str_eid,exp_yr);
        backWork3.execute();



    }


    public void click_following(View view) {

        db_follow_btn backWork = new db_follow_btn(this);
        backWork.execute("follow",me_id,str_eid);
    }



}
