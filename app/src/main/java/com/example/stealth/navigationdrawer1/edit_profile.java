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

import com.example.stealth.navigationdrawer1.db.db_login;
import com.example.stealth.navigationdrawer1.profile_backgroud.db_editprofile;
import com.example.stealth.navigationdrawer1.profile_backgroud.parser_myprofile;

import java.util.ArrayList;
import java.util.List;

public class edit_profile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText phone,email,address,about,fb,linkedin,skill;
    TextView name,txt_mobile,txt_email,txt_address,txt_fb,txt_linkedin,txt_summary,txt_skill;
    String name_str,eid_str,phone_str,emial_str,address_str,about_str,fb_str,linkedin_str,skill_str;
    Button update;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);



        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            eid_str= extras.getString("EID");
            name_str= extras.getString("NAME");
            phone_str= extras.getString("PHONE");
            emial_str= extras.getString("EMAIL");
            address_str= extras.getString("ADDRESS");
            about_str= extras.getString("ABOUT");
            fb_str= extras.getString("FB");
            linkedin_str= extras.getString("LINKEDIN");
            skill_str= extras.getString("SKILL");
        }

        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
        address=(EditText)findViewById(R.id.address);
        about=(EditText)findViewById(R.id.summary);
        fb=(EditText)findViewById(R.id.facebook);
        linkedin=(EditText)findViewById(R.id.linkedin);
        skill=(EditText)findViewById(R.id.skill);
        name=(TextView)findViewById(R.id.name);

        spinner = (Spinner) findViewById(R.id.spinner);

        txt_mobile=(TextView)findViewById(R.id.txt_mobile);
        txt_email=(TextView)findViewById(R.id.txt_email);
        txt_address=(TextView)findViewById(R.id.txt_address);
        txt_fb=(TextView)findViewById(R.id.txt_fb);
        txt_linkedin=(TextView)findViewById(R.id.txt_linkedin);
        txt_summary=(TextView)findViewById(R.id.txt_summary);
        txt_skill=(TextView)findViewById(R.id.txt_skill);


        update=(Button)findViewById(R.id.button1);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Light.ttf");

        name.setTypeface(myCustomFont2);
        txt_mobile.setTypeface(myCustomFont2);
        txt_email.setTypeface(myCustomFont2);
        txt_address.setTypeface(myCustomFont2);
        txt_summary.setTypeface(myCustomFont2);
        txt_fb.setTypeface(myCustomFont2);
        txt_linkedin.setTypeface(myCustomFont2);
        txt_skill.setTypeface(myCustomFont2);
        update.setTypeface(myCustomFont3);


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



        name.setText(name_str);
        phone.setText(phone_str);
        email.setText(emial_str);
        address.setText(address_str);
        about.setText(about_str);
        fb.setText(fb_str);
        linkedin.setText(linkedin_str);
        skill.setText(skill_str);



    }




    public void update(View view) {
        update.setEnabled(false);

        db_editprofile backWork = new db_editprofile(this);
        backWork.execute("update",eid_str.toString(),phone.getText().toString(),email.getText().toString(),address.getText().toString(),fb.getText().toString(),linkedin.getText().toString(),about.getText().toString(),skill.getText().toString(),name_str.toString());
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        // On selecting a spinner item
        String item = adapterView.getItemAtPosition(position).toString();


        if(item.equals("Select")){
          //  Toast.makeText(adapterView.getContext(),"select skill", Toast.LENGTH_LONG).show();

        }
        
        else{
            String array1[]= skill_str.split(",");
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

                skill_str=skill_str+","+item;
                skill.setText(skill_str);

                Toast.makeText(adapterView.getContext(), item+" "+" is addred to your skill-set", Toast.LENGTH_LONG).show();
                flag=0;

            }





        }







    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

