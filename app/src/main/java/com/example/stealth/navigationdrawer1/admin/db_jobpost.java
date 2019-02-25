package com.example.stealth.navigationdrawer1.admin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.example.stealth.navigationdrawer1.MainActivity;
import com.example.stealth.navigationdrawer1.admin_jobpost;
import com.example.stealth.navigationdrawer1.main_link;

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
 * Created by stealth on 11/4/18.
 */

public class db_jobpost extends AsyncTask<String,Void,String> {

    Context c;
    Button b1;
    String eid,user_name,title,job_id,job_position,job_dept,job_desc,job_req,job_skill;
    AlertDialog alertDialog;

    main_link obj_link=new main_link();
    String primary_link=obj_link.host_link();

    String login_url=primary_link+"job_post.php";
    public db_jobpost(Context c, String eid, String user_name, String title, String job_id, String job_position, String job_dept, String job_desc, String job_req, String job_skillip, Button b1) {
    this.c=c;
    this.eid=eid;
    this.user_name=user_name;
    this.title=title;
    this.job_id=job_id;
    this.job_position=job_position;
    this.job_dept=job_dept;
    this.job_desc=job_desc;
    this.job_req=job_req;
    this.job_skill=job_skillip;
    this.b1=b1;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = "post";
      //  String login_url = "http://pentelican-thing.000webhostapp.com/nexval/job_post.php";
        // String login_url = "https://e738d6aa.ngrok.io/nexval/login.php";
        if (type.equals("post")) {

            try {

                String result="";



                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("eid", "UTF-8") + "=" + URLEncoder.encode(eid, "UTF-8") + "&"
                        + URLEncoder.encode("job_id", "UTF-8") + "=" + URLEncoder.encode(job_id, "UTF-8") + "&"
                        + URLEncoder.encode("job_title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8")+ "&"
                        + URLEncoder.encode("job_position", "UTF-8") + "=" + URLEncoder.encode(job_position, "UTF-8")+ "&"
                        + URLEncoder.encode("job_dept", "UTF-8") + "=" + URLEncoder.encode(job_dept, "UTF-8")+ "&"
                        + URLEncoder.encode("job_desc", "UTF-8") + "=" + URLEncoder.encode(job_desc, "UTF-8")+ "&"
                        + URLEncoder.encode("job_skill", "UTF-8") + "=" + URLEncoder.encode(job_skill, "UTF-8")+ "&"
                        + URLEncoder.encode("job_req", "UTF-8") + "=" + URLEncoder.encode(job_req, "UTF-8");
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
        alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setTitle("procedure status");
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("exist")) {
            alertDialog.setMessage("Check job id/job post already exist");
            alertDialog.show();

        }
        else {
            b1.setEnabled(false);
            alertDialog.setMessage("welcome " + result);
            alertDialog.show();

            Intent intent = new Intent(c,MainActivity.class);
            intent.putExtra("KEY",eid);
            intent.putExtra("KEY2",user_name);
            c.startActivity(intent);
            //System.out.print("db_login"+ token);

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
