package com.example.stealth.navigationdrawer1.jobs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stealth.navigationdrawer1.MainActivity;
import com.example.stealth.navigationdrawer1.R;
import com.example.stealth.navigationdrawer1.admin_jobapply;
import com.example.stealth.navigationdrawer1.home_db_recy.ItemClickListener;
import com.example.stealth.navigationdrawer1.home_db_recy.home_rec_holder;
import com.example.stealth.navigationdrawer1.job_details;
import com.example.stealth.navigationdrawer1.profile;

import java.util.ArrayList;

/**
 * Created by stealth on 6/4/18.
 */

public class job_rec_adapter extends RecyclerView.Adapter<job_rec_holder> {

    Context c;
    String eid, user_name,type;
    ArrayList<String> job_id;
    ArrayList<String> job_title;
    ArrayList<String> job_dept;
    ArrayList<String> job_desc;
    ArrayList<String> job_skill;
    ArrayList<String> job_by;
    ArrayList<String> job_req;
    ArrayList<String> job_position;



    public job_rec_adapter(Context c, String eid, String user_name, ArrayList<String> job_title, ArrayList<String> job_id, ArrayList<String> job_dept, ArrayList<String> job_desc, ArrayList<String> job_skill, ArrayList<String> job_by, ArrayList<String> job_req,ArrayList<String> job_position,String type) {
        this.c = c;
        this.eid = eid;
        this.user_name = user_name;
        this.job_id = job_id;
        this.job_title = job_title;
        this.job_dept = job_dept;
        this.job_desc = job_desc;
        this.job_skill = job_skill;
        this.job_by = job_by;
        this.job_req = job_req;
        this.job_position=job_position;
        this.type=type;
    }

    @Override
    public job_rec_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_jobs, parent, false);
        job_rec_holder holder = new job_rec_holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(job_rec_holder holder,final int position) {
        holder.title.setText( job_title.get(position));
        holder.dept.setText( job_dept.get(position));
        holder.skill.setText( ", "+job_skill.get(position));

        holder.setItemClickListener(new ItemClickListener2() {
            @Override
            public void onItemClick(int pos) {

                if(type.equals("USER")){

                Intent intent = new Intent(c,job_details.class);
                //intent.putExtra("fname",fname.get(position));
                intent.putExtra("KEY",eid);
                intent.putExtra("KEY2",user_name);
                intent.putExtra("job_id",job_id.get(position));
                intent.putExtra("job_title",job_title.get(position));
                intent.putExtra("job_dept",job_dept.get(position));
                intent.putExtra("job_desc",job_desc.get(position));
                intent.putExtra("job_skill",job_skill.get(position));
                intent.putExtra("job_by",job_by.get(position));
                intent.putExtra("job_req",job_req.get(position));
                intent.putExtra("job_position",job_position.get(position));
                c.startActivity(intent);
                }
                else if(type.equals("ADMIN")){

                    Intent intent = new Intent(c,admin_jobapply.class);
                    //intent.putExtra("fname",fname.get(position));
                    intent.putExtra("KEY",eid);
                    intent.putExtra("KEY2",user_name);
                    intent.putExtra("job_id",job_id.get(position));
                    intent.putExtra("job_title",job_title.get(position));
                    intent.putExtra("job_dept",job_dept.get(position));
                    intent.putExtra("job_desc",job_desc.get(position));
                    intent.putExtra("job_skill",job_skill.get(position));
                    intent.putExtra("job_by",job_by.get(position));
                    intent.putExtra("job_req",job_req.get(position));
                    intent.putExtra("job_position",job_position.get(position));
                    c.startActivity(intent);


                }



            }
        });

    }

    @Override
    public int getItemCount() {
        return job_title.size();
    }
}
