package com.example.stealth.navigationdrawer1;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.stealth.navigationdrawer1.post.db_mypost;

public class my_post extends AppCompatActivity {
    String me_id,user_name;
    TextView txt_header,txt_title,txt_body,txt_fb,txt_linkiedin,txt_twitter;
    EditText title,body,fb,linkedin,twitter;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            me_id= extras.getString("KEY");
            user_name= extras.getString("NAME");

        }

        txt_header=(TextView)findViewById(R.id.txt_header);
        txt_title=(TextView)findViewById(R.id.txt_title);
        txt_body=(TextView)findViewById(R.id.txt_body);
        txt_fb=(TextView)findViewById(R.id.txt_fb);
        txt_linkiedin=(TextView)findViewById(R.id.txt_linkedin);
        txt_twitter=(TextView)findViewById(R.id.txt_twitter);

        title=(EditText)findViewById(R.id.title);
        body=(EditText)findViewById(R.id.body);
        fb=(EditText)findViewById(R.id.facebook);
        linkedin=(EditText)findViewById(R.id.linkedin);
        twitter=(EditText)findViewById(R.id.twitter);

        post=(Button)findViewById(R.id.button1);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Light.ttf");

        txt_header.setTypeface(myCustomFont2);
        txt_title.setTypeface(myCustomFont2);
        txt_body.setTypeface(myCustomFont2);
        txt_fb.setTypeface(myCustomFont2);
        txt_linkiedin.setTypeface(myCustomFont2);
        txt_twitter.setTypeface(myCustomFont2);
        title.setTypeface(myCustomFont2);
        body.setTypeface(myCustomFont2);
        fb.setTypeface(myCustomFont2);
        linkedin.setTypeface(myCustomFont2);
        twitter.setTypeface(myCustomFont2);
        post.setTypeface(myCustomFont3);



    }

    public void post(View view) {
        post.setEnabled(false);

        db_mypost backWork = new db_mypost(this);

        backWork.execute("add",me_id,title.getText().toString(),body.getText().toString(),fb.getText().toString(),linkedin.getText().toString(),twitter.getText().toString(),user_name);


    }
}
