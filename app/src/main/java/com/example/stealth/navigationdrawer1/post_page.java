package com.example.stealth.navigationdrawer1;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class post_page extends AppCompatActivity {
    TextView title,name,body,read_more;
    ImageView fb,linkedin,twitter;
    String fname_str,lname_str,title_str,body_str,fb_str,linkedin_str,twitter_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_page);



        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            fname_str = extras.getString("fname");
            lname_str = extras.getString("lname");
            title_str = extras.getString("title");
            body_str = extras.getString("body");
            fb_str = extras.getString("fb");
            linkedin_str = extras.getString("linkedin");
            twitter_str = extras.getString("twitter");

        }



        title=(TextView)findViewById(R.id.title);
        name=(TextView)findViewById(R.id.name);
        body=(TextView)findViewById(R.id.body);
        read_more=(TextView)findViewById(R.id.read_more);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Light.ttf");



        name.setTypeface(myCustomFont2);
        body.setTypeface(myCustomFont2);
        title.setTypeface(myCustomFont);
        read_more.setTypeface(myCustomFont2);

        title.setText(title_str);
        name.setText("by "+ fname_str+" "+lname_str);
        body.setText(body_str);





    }

    public void click1(View view) {

        try {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(fb_str));
            startActivity(i);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Link down or corrupt link",Toast.LENGTH_LONG).show();

        }
    }

    public void click2(View view) {

        try {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(linkedin_str));
            startActivity(i);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Link down or corrupt link",Toast.LENGTH_LONG).show();


        }
    }

    public void click3(View view) {

        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(twitter_str));
            startActivity(i);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Link down or corrupt link",Toast.LENGTH_LONG).show();


        }
    }
}
