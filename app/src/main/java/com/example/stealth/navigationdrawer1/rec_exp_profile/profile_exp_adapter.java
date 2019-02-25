package com.example.stealth.navigationdrawer1.rec_exp_profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stealth.navigationdrawer1.R;
import com.example.stealth.navigationdrawer1.rec_exp_profile.profile_exp_holder;

import java.util.ArrayList;

/**
 * Created by stealth on 28/2/18.
 */

public class profile_exp_adapter extends RecyclerView.Adapter<profile_exp_holder>{


    Context c;

    ArrayList<String> company;
    ArrayList<String> position_exp;
    ArrayList<String> description;
    ArrayList<String> start;
    ArrayList<String> end;



    public profile_exp_adapter(Context c,ArrayList<String>company,ArrayList<String>position_exp,ArrayList<String>description,ArrayList<String>start,ArrayList<String>end){

        this.c = c;
        this.company = company;
        this.position_exp = position_exp;
        this.description = description;
        this.start = start;
        this.end=end;
    }
    @Override
    public profile_exp_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_exp,parent,false);
        profile_exp_holder holder=new profile_exp_holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(profile_exp_holder holder,final int position) {

        holder.company.setText(company.get(position));
        holder.position.setText(position_exp.get(position) + ", ");
        holder.description.setText(description.get(position));
        holder.duration.setText(start.get(position)+"-"+end.get(position));

    }

    @Override
    public int getItemCount()
        {
            return company.size();
    }
}
