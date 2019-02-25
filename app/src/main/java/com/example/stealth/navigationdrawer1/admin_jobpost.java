package com.example.stealth.navigationdrawer1;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.admin.db_jobpost;

import java.util.ArrayList;
import java.util.List;

public class admin_jobpost extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView header;
    Button post_btn;
    String eid,user_name;
    EditText title,job_id,job_position,job_desc,job_dept,job_skill,job_req;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_jobpost);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            eid= extras.getString("KEY");
            user_name= extras.getString("NAME");
        }



        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Light.ttf");


        //textviews
        header=(TextView)findViewById(R.id.txt_header);
        post_btn=(Button)findViewById(R.id.button1);


        //editTexts
        job_id=(EditText)findViewById(R.id.id);
        title=(EditText)findViewById(R.id.title);
        job_position=(EditText)findViewById(R.id.position);
        job_desc=(EditText)findViewById(R.id.body);
        job_dept=(EditText)findViewById(R.id.dept);
        job_skill=(EditText)findViewById(R.id.skill);
        job_req=(EditText)findViewById(R.id.req);

        //spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("JAVA");
        categories.add("C++");
        categories.add("PHP");
        categories.add("HTML");
        categories.add("C#");
        categories.add("ASP.NET");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



        //typeface
        header.setTypeface(myCustomFont2);
        post_btn.setTypeface(myCustomFont3);






    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        // On selecting a spinner item
        String item = adapterView.getItemAtPosition(position).toString();
        String skill_item=job_skill.getText().toString();


        if(item.equals("Select")){
             Toast.makeText(adapterView.getContext(),"select skill", Toast.LENGTH_LONG).show();

        }
        else{
            String array1[]= skill_item.split(",");
            int flag=0;
            for (int i=0;i<array1.length;i++){

                if(item.equals(array1[i])){
                    flag=0;
                    break;
                }
                else
                    flag=1;

            }

            if(flag==0){
                Toast.makeText(adapterView.getContext(), item+" is already on your list", Toast.LENGTH_LONG).show();


            }
            else if (flag==1){

                skill_item=item+","+skill_item;
                job_skill.setText(skill_item);

                Toast.makeText(adapterView.getContext(), item+" "+" is addred to your skill-set", Toast.LENGTH_SHORT).show();
                flag=0;

            }

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void post(View view) {




        String sub=job_skill.getText().toString();
        String job_skillip=sub.substring(0,sub.length()-1);
        db_jobpost backWork = new db_jobpost(this,eid,user_name,title.getText().toString(),job_id.getText().toString(),job_position.getText().toString(),job_dept.getText().toString(),job_desc.getText().toString(),job_req.getText().toString(),job_skillip,post_btn);
        backWork.execute();

        //Toast.makeText(getApplicationContext(),sub2, Toast.LENGTH_LONG).show();


    }
}


