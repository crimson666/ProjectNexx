package com.example.stealth.navigationdrawer1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.db.db_login;
import com.example.stealth.navigationdrawer1.firebase.FcmInstanceIdService;

import java.util.Calendar;

public class login extends AppCompatActivity {

   public EditText Userid,Password;
   public Button button1,button2;
   Context context;
   String token;


//nexval-e3f46


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Userid = (EditText)findViewById(R.id.userid);
        Password=(EditText)findViewById(R.id.password);
         button1=(Button)findViewById(R.id.button1);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Light.ttf");
        button1.setTypeface(myCustomFont3);
        Userid.setTypeface(myCustomFont2);
        Password.setTypeface(myCustomFont2);

       // int year = Calendar.getInstance().get(Calendar.YEAR);




    }


    public void onclick1(View v){

        String userid = Userid.getText().toString();
        String password = Password.getText().toString();

        FcmInstanceIdService fcmInstanceIdService = new FcmInstanceIdService(this);
        fcmInstanceIdService.onTokenRefresh();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("nexval_grievance_app",
                Context.MODE_PRIVATE);
        System.out.println("in logIn  token      "+sharedPreferences.getString("fcm_token_new", "not found"));
        token = sharedPreferences.getString("fcm_token_new", " ");
       // Toast.makeText(this, token, Toast.LENGTH_LONG).show();

        String type = "login";
       db_login backWork = new db_login(this,button1);
        backWork.execute(type,userid,password, token);
    }


}
