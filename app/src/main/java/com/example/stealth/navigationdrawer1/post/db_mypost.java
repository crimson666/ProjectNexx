package com.example.stealth.navigationdrawer1.post;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import com.example.stealth.navigationdrawer1.MainActivity;
import com.example.stealth.navigationdrawer1.main_link;
import com.example.stealth.navigationdrawer1.my_post;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by stealth on 1/4/18.
 */

public class db_mypost extends AsyncTask<String,Void,String> {
    Context context;
    String me_id,user_name;


    AlertDialog alertDialog;
    main_link obj_link=new main_link();
    String primary_link=obj_link.host_link();

    String login_url=primary_link+"my_post.php";


    public db_mypost(Context c) {
        this.context=c;

    }

    @Override
    protected String doInBackground(String... params) {
         String type = params[0];

        // String login_url = "https://4c58912a.ngrok.io/nexval/edit.php";
      //  String login_url = "http://pentelican-thing.000webhostapp.com/nexval/my_post.php";

        if (type.equals("add")) {

            try {


                 me_id=params[1];
                String title=params[2];
                String body=params[3];
                String fb=params[4];
                String linkedin=params[5];
                String twitter=params[6];
                 user_name=params[7];
/*
                me_id="87";
                title="jg";
                body="jg";
                fb="jg";
                linkedin="jg";
                twitter="jg";*/



                String result="";


                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("eid", "UTF-8") + "=" + URLEncoder.encode(me_id, "UTF-8") + "&"
                        +URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8")+"&"
                        +URLEncoder.encode("body", "UTF-8") + "=" + URLEncoder.encode(body, "UTF-8")+"&"
                        +URLEncoder.encode("fb", "UTF-8") + "=" + URLEncoder.encode(fb, "UTF-8")+"&"
                        +URLEncoder.encode("linkedin", "UTF-8") + "=" + URLEncoder.encode(linkedin, "UTF-8")+"&"
                        +URLEncoder.encode("twitter", "UTF-8") + "=" + URLEncoder.encode(twitter, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("procedure status");
    }
    @Override
    protected void onPostExecute(String result) {

        alertDialog.setMessage("successfully updated");
        alertDialog.show();
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra("KEY",me_id);
        intent.putExtra("KEY2",user_name);
        context.startActivity(intent);


    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
