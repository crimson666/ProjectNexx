package com.example.stealth.navigationdrawer1.home_db_recy;

/**
 * Created by stealth on 11/1/18.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.stealth.navigationdrawer1.R;
import com.example.stealth.navigationdrawer1.profile;

import java.util.ArrayList;

public class home_rec_adapter extends RecyclerView.Adapter<home_rec_holder> {

    Context c;
    ArrayList<String> fname;
    ArrayList<String> lname;
    ArrayList<String> positions;
    ArrayList<String> about;
    ArrayList<String> phone;
    ArrayList<String> email;
    ArrayList<String> address;
    ArrayList<String> fb;
    ArrayList<String> linkedin;
    ArrayList<String> eid;
    ArrayList<String> dept;
    ArrayList<String> following;
    ArrayList<String> follower;
    ArrayList<String> skill;
    public String send="";
    public String me_id;

    public home_rec_adapter(Context c,ArrayList<String> fname, ArrayList<String> lname, ArrayList<String> eid, ArrayList<String> dept,ArrayList<String> position,ArrayList<String> about,ArrayList<String> phone,ArrayList<String> email,ArrayList<String> address,ArrayList<String> fb,ArrayList<String> linkedin,String me_id,ArrayList<String> following,ArrayList<String> follower,ArrayList<String> skill){
        this.c = c;
        this.fname = fname;
        this.lname = lname;
        this.eid = eid;
        this.positions = position;
        this.about = about;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.fb = fb;
        this.linkedin = linkedin;
        this.dept = dept;
        this.me_id=me_id;
        this.follower=follower;
        this.following=following;
        this.skill=skill;



    }



    @Override
    public home_rec_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_dept,parent,false);
        home_rec_holder holder=new home_rec_holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(home_rec_holder holder, final int position) {



        holder.name.setText(fname.get(position) +" "+ lname.get(position));
        holder.eid.setText("Employee ID :" + eid.get(position));
        holder.dept.setText("Department :" + dept.get(position));
        //send = detail + "";

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {

              Intent intent = new Intent(c,profile.class);
                intent.putExtra("fname",fname.get(position));
                intent.putExtra("lname",lname.get(position));
                intent.putExtra("eid",eid.get(position));
                intent.putExtra("dept",dept.get(position));
                intent.putExtra("position",positions.get(position));
                intent.putExtra("phone",phone.get(position));
                intent.putExtra("email",email.get(position));
                intent.putExtra("address",address.get(position));
                intent.putExtra("fb",fb.get(position));
                intent.putExtra("linkedin",linkedin.get(position));
                intent.putExtra("about",about.get(position));
                intent.putExtra("ME_ID",me_id);
                intent.putExtra("following",following.get(position));
                intent.putExtra("follower",follower.get(position));
                intent.putExtra("skill",skill.get(position));



                c.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return fname.size();
    }
}
