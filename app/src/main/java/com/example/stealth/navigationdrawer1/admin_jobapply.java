package com.example.stealth.navigationdrawer1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.admin.db_applylist;
import com.example.stealth.navigationdrawer1.follower.db_follower;
import com.example.stealth.navigationdrawer1.following.db_following;

public class admin_jobapply extends AppCompatActivity {
    String eid,user_name,job_title,job_id,job_dept,job_desc,job_skill,job_by,job_req_str,job_position;
    TextView txt_dept,txt_skill,txt_id,txt_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_jobapply);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            eid= extras.getString("KEY");
            user_name=extras.getString("KEY2");
            job_title=extras.getString("job_title");
            job_id=extras.getString("job_id");
            job_dept=extras.getString("job_dept");
            job_desc=extras.getString("job_desc");
            job_skill=extras.getString("job_skill");
            job_by=extras.getString("job_by");
            job_req_str=extras.getString("job_req");
            job_position=extras.getString("job_position");



        }

        txt_dept=(TextView)findViewById(R.id.dept);
        txt_skill=(TextView)findViewById(R.id.skill);
        txt_id=(TextView)findViewById(R.id.jobid);
        txt_desc=(TextView)findViewById(R.id.job_desc);

        txt_dept.setText(job_position+", " +job_dept);
        txt_skill.setText(job_skill);
        txt_id.setText(job_id);
        txt_desc.setText(job_desc);
        getSupportActionBar().setTitle(job_title);


        final RecyclerView rv= (RecyclerView) findViewById(R.id.admin_recycler_applicant);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        db_applylist d=new db_applylist(this,job_id,eid,rv);
        d.execute();



      //  Toast.makeText(getApplicationContext(),user_name,Toast.LENGTH_LONG).show();

    }
}
