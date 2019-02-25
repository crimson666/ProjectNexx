package com.example.stealth.navigationdrawer1;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class admin_home extends AppCompatActivity {
    TextView header,txt1,txt2;
    String eid,user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            eid= extras.getString("KEY");
            user_name= extras.getString("NAME");


        }


        header=(TextView)findViewById(R.id.header);
        txt1=(TextView)findViewById(R.id.txt1);
        txt2=(TextView)findViewById(R.id.txt2);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Light.ttf");

        header.setTypeface(myCustomFont2);
        txt2.setTypeface(myCustomFont2);
        txt1.setTypeface(myCustomFont2);

    }

    public void post(View view) {
        Intent intent = new Intent(this,admin_jobpost.class);
        intent.putExtra("KEY",eid);
        intent.putExtra("NAME",user_name);
        startActivity(intent);

    }

    public void apply(View view) {
        Intent intent = new Intent(this,admin_joblist.class);
        intent.putExtra("KEY",eid);
        intent.putExtra("NAME",user_name);
        intent.putExtra("TYPE","ADMIN");
        startActivity(intent);

    }
}
