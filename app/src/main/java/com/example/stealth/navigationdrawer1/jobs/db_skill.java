package com.example.stealth.navigationdrawer1.jobs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import com.example.stealth.navigationdrawer1.R;
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
 * Created by stealth on 8/4/18.
 */

public class db_skill extends AsyncTask<String,Void,String> {

    Context c;
    String eid,job_skill,job_req_str;

    TextView txt_userskill,eligibility,quali_skill;
    ProgressBar progressBar;

    main_link obj_link=new main_link();
    String primary_link=obj_link.host_link();

    String login_url=primary_link+"job_skill.php";


    AlertDialog alertDialog;
    public db_skill(Context c,String eid,TextView user_skill,String job_skill,TextView eligibility,TextView quali_skill,ProgressBar progressBar,String job_rec_str) {
        this.txt_userskill=user_skill;
        this.c=c;
        this.eid=eid;
        this.job_skill=job_skill;
        this.eligibility=eligibility;
        this.quali_skill=quali_skill;
        this.progressBar=progressBar;
        this.job_req_str=job_rec_str;

    }

   


    @Override
    protected String doInBackground(String... params) {
        String type = "login";
       // String login_url = "http://pentelican-thing.000webhostapp.com/nexval/job_skill.php";
        // String login_url = "https://e738d6aa.ngrok.io/nexval/login.php";
        if (type.equals("login")) {

            try {

                String result="";



                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("eid", "UTF-8") + "=" + URLEncoder.encode(eid, "UTF-8");

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
       // alertDialog = new AlertDialog.Builder(c).create();
      //  alertDialog.setTitle("procedure status");
    }

    @Override
    protected void onPostExecute(String result) {
        int count=0;
        String quai_skill="";

        txt_userskill.setText(result);
        String array1[]= result.split(",");
        String array2[]= job_skill.split(",");
        String array3[] = new String[10];
        
        for (int i=0;i<array2.length;i++){

            for (int j=0;j<array1.length;j++){
                
                if(array2[i].equals(array1[j])){
                    
                    count=count+1;
                  quai_skill=array2[i]+","+quai_skill;

                }
                
                
            }
        }

        //progressBar rating

        int rating=(count*100)/array2.length;
        progressBar.setProgress(rating);

        //eligibility
        int req_rate=Integer.parseInt(job_req_str);
       // Toast.makeText(c,job_req_str,Toast.LENGTH_LONG).show();
        if(rating>=req_rate){
        }
        else {
            eligibility.setText("NO");

            //eligibility.setTextColor(color.GREEN);
        }




        quali_skill.setText(quai_skill);
      



    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
