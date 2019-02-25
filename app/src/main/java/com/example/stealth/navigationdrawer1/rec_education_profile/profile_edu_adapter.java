package com.example.stealth.navigationdrawer1.rec_education_profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stealth.navigationdrawer1.R;

import java.util.ArrayList;

/**
 * Created by stealth on 15/1/18.
 */

public class profile_edu_adapter extends RecyclerView.Adapter<profile_edu_holder> {

    Context c;

    ArrayList<String> institute;
    ArrayList<String> degree;
    ArrayList<String> grade;
    ArrayList<String> duration;

    public profile_edu_adapter(Context c,ArrayList<String> institute,ArrayList<String>degree,ArrayList<String> grade,ArrayList<String> duration){

        this.c = c;
        this.institute = institute;
        this.degree = degree;
        this.grade = grade;
        this.duration = duration;
    }

    @Override
    public profile_edu_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_education,parent,false);
        profile_edu_holder holder=new profile_edu_holder(v);
        return holder;
    }

    public void onBindViewHolder(profile_edu_holder holder, final int position) {

        holder.institute.setText(institute.get(position));
        holder.degree.setText(degree.get(position) + ", ");
        holder.grade.setText(grade.get(position));
        holder.duration.setText(duration.get(position));

    }

    @Override
    public int getItemCount() {
        return institute.size();
    }

}
