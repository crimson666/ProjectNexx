package com.example.stealth.navigationdrawer1.post;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stealth.navigationdrawer1.R;
import com.example.stealth.navigationdrawer1.home_db_recy.ItemClickListener;
import com.example.stealth.navigationdrawer1.post_page;
import com.example.stealth.navigationdrawer1.profile;
import com.example.stealth.navigationdrawer1.rec_education_profile.profile_edu_holder;

import java.util.ArrayList;

/**
 * Created by stealth on 31/3/18.
 */

public class post_adapter extends RecyclerView.Adapter<post_holder> {

    Context c;

    ArrayList<String> first_name;
    ArrayList<String> last_name;
    ArrayList<String> title;
    ArrayList<String> body;
    ArrayList<String> fb;
    ArrayList<String> linkedin;
    ArrayList<String> twitter;

    public post_adapter(Context c,ArrayList<String> first_name,ArrayList<String>last_name,ArrayList<String> title,ArrayList<String> body,ArrayList<String> fb,ArrayList<String> linkedin,ArrayList<String> twitter){

        this.c = c;
        this.first_name = first_name;
        this.last_name = last_name;
        this.title = title;
        this.body = body;
        this.fb = fb;
        this.linkedin = linkedin;
        this.twitter = twitter;
    }


    @Override
    public post_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_post,parent,false);
        post_holder holder=new post_holder(v);
        return holder;
    }


    public void onBindViewHolder(post_holder holder, final int position) {

        holder.title.setText(title.get(position));
        holder.name.setText("by "+first_name.get(position) + " " + last_name.get(position));



        holder.setItemClickListener(new ClickListener() {
            @Override
            public void onclick(int position) {
                Intent intent = new Intent(c,post_page.class);
                intent.putExtra("fname",first_name.get(position));
                intent.putExtra("lname",last_name.get(position));
                intent.putExtra("title",title.get(position));
                intent.putExtra("body",body.get(position));
                intent.putExtra("fb",fb.get(position));
                intent.putExtra("linkedin",linkedin.get(position));
                intent.putExtra("twitter",twitter.get(position));

                c.startActivity(intent);

            }


        });
        //end

    }

    @Override
    public int getItemCount() {
        return title.size();
    }
}
