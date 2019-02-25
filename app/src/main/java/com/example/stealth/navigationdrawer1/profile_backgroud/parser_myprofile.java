package com.example.stealth.navigationdrawer1.profile_backgroud;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.home_db_recy.home_rec_adapter;
import com.example.stealth.navigationdrawer1.me_profile;
import com.example.stealth.navigationdrawer1.profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by stealth on 28/2/18.
 */

public class parser_myprofile extends AsyncTask<Void,Integer,Integer> {


    Context c;
    String data;
    String eid="";
    String fname="";
    String lname="";
    String position="";
    String dept="";
    String about="";
    String phone ="";
    String email="";
    String address ="";
    String fb="";
    String linkedin ="";
    String follower="";
    String following="";
    String skill="";

    TextView eid_txt,name_txt,position_txt,about_txt,phone_txt,email_txt,address_txt,fb_txt,linkedin_txt,following_txt,follower_txt,skill_txt;


    public parser_myprofile(Context c, String data, TextView eid, TextView name, TextView position, TextView about, TextView phone, TextView email, TextView address, TextView fb, TextView linkedin,TextView following_txt,TextView follower_txt,TextView skill) {

        this.c = c;
        this.data = data;
        this.eid_txt=eid;
        this.name_txt=name;
        this.about_txt=about;
        this.position_txt=position;
        this.skill_txt=skill;

        this.phone_txt = phone;
        this.address_txt=address;
        this.email_txt=email;
        this.fb_txt=fb;
        this.linkedin_txt=linkedin;
        this.follower_txt=follower_txt;
        this.following_txt=following_txt;

    }
    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parse();
    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        //  pd.dismiss();

        eid_txt.setText(eid);
        name_txt.setText(fname + " " +lname);
        position_txt.setText(position +"," + dept);

        about_txt.setText(about);
        phone_txt.setText(phone);
        email_txt.setText(email);
        address_txt.setText(address);
        fb_txt.setText(fb);
        linkedin_txt.setText(linkedin);
        following_txt.setText(following);
        follower_txt.setText(follower);
        skill_txt.setText(skill);
       // Toast.makeText(c,skill, Toast.LENGTH_LONG).show();

    }



    private int parse()
    {
        try
        {
            JSONArray ja=new JSONArray(data);
            JSONObject jo=null;

            Log.d("data", data);

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                fname=jo.getString("first_name");
                lname=jo.getString("last_name");
                eid=jo.getString("e_id");
              dept=jo.getString("dept");
                position=jo.getString("position");
                about=jo.getString("about");

                phone = jo.getString("Phone_no");
                email=jo.getString("email");
                address=jo.getString("address");
                fb=jo.getString("fb");
                linkedin=jo.getString("linkedin");
                following=jo.getString("following");
                follower=jo.getString("follower");
                skill=jo.getString("skill");



            }


            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
