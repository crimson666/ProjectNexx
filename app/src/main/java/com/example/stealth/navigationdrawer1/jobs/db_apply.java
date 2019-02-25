package com.example.stealth.navigationdrawer1.jobs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.MainActivity;
import com.example.stealth.navigationdrawer1.job_details;
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
 * Created by stealth on 10/4/18.
 */

public class db_apply extends AsyncTask<String,Void,String> {
    Context c;
    String eid,user_name,job_id;
    AlertDialog alertDialog;
    main_link obj_link=new main_link();
    String primary_link=obj_link.host_link();

    String login_url=primary_link+"job_apply.php";

    public db_apply(Context c, String eid, String user_name, String job_id) {
        this.c=c;
        this.eid=eid;
        this.user_name=user_name;
        this.job_id=job_id;

    }

    @Override
    protected String doInBackground(String... params) {

        String type = "apply";
       // String login_url = "http://pentelican-thing.000webhostapp.com/nexval/job_apply.php";
        // String login_url = "https://e738d6aa.ngrok.io/nexval/login.php";
        if (type.equals("apply")) {

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
                        + URLEncoder.encode("job_id", "UTF-8") + "=" + URLEncoder.encode(job_id, "UTF-8");
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

            //alertDialog.setMessage("You have successfully applied for the job");
        Toast.makeText(c,result,Toast.LENGTH_LONG).show();

       // alertDialog.setMessage(result);
           // alertDialog.show();

        Intent intent = new Intent(c,MainActivity.class);
        intent.putExtra("KEY",eid);
        intent.putExtra("KEY2",user_name);
        c.startActivity(intent);



    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
