package com.example.stealth.navigationdrawer1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.jobs.db_apply;
import com.example.stealth.navigationdrawer1.jobs.db_skill;

public class job_details extends AppCompatActivity {
    String eid,user_name,job_title,job_id,job_dept,job_desc,job_skill,job_by,job_req_str,job_position;
    TextView txt_dept,txt_skill,txt_id,txt_desc,txt_userskill,eligibility,quali_skill;
    Context c;
    ProgressBar progressBar;
    Button b1;
    int job_rec=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

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
       // job_rec=Integer.parseInt(job_rec_str);


        getSupportActionBar().setTitle(job_title);

        txt_dept=(TextView)findViewById(R.id.dept);
        txt_skill=(TextView)findViewById(R.id.skill);
        txt_id=(TextView)findViewById(R.id.jobid);
        txt_desc=(TextView)findViewById(R.id.job_desc);
        txt_userskill=(TextView)findViewById(R.id.user_skill);
        eligibility=(TextView)findViewById(R.id.eligibility);
        quali_skill=(TextView)findViewById(R.id.quali_skill);

        b1=(Button)findViewById(R.id.button1);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Light.ttf");

        b1.setTypeface(myCustomFont3);
        txt_dept.setTypeface(myCustomFont2);
        txt_skill.setTypeface(myCustomFont2);
        txt_desc.setTypeface(myCustomFont2);
        txt_userskill.setTypeface(myCustomFont2);
        eligibility.setTypeface(myCustomFont2);
        quali_skill.setTypeface(myCustomFont2);
        txt_id.setTypeface(myCustomFont2);

        txt_dept.setText(job_position+", " +job_dept);
        txt_skill.setText(job_skill);
        txt_id.setText(job_id);
        txt_desc.setText(job_desc);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //progressBar.setProgress(45);

        db_skill background=new db_skill(this,eid,txt_userskill,job_skill,eligibility,quali_skill,progressBar,job_req_str);
        background.execute();
      // Toast.makeText(getApplicationContext(),txt_userskill.getText().toString(),Toast.LENGTH_LONG).show();

    }

    public void apply(View view) {
    //    Toast.makeText(getApplicationContext(),txt_id.getText().toString(),Toast.LENGTH_LONG).show();

        if(eligibility.getText().equals("YES")){
            db_apply dbApply= new db_apply(this,eid,user_name,job_id);
            dbApply.execute();


        }
        else{
            Toast.makeText(getApplicationContext(),"You Are no eligible for the job",Toast.LENGTH_LONG).show();
        }


    }
}
