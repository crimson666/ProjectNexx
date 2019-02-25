package com.example.stealth.navigationdrawer1.exp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

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
import java.util.Calendar;

/**
 * Created by stealth on 30/3/18.
 */

public class db_exp extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog2;
    String type,emp_id;
    TextView exp_yr;

    main_link obj_link=new main_link();
    String primary_link=obj_link.host_link();

    String login_url=primary_link+"exp_yr.php";




    public db_exp(Context ctx, String type, String emp_id, TextView exp_yr) {
        context = ctx;
        this.type=type;
        this.emp_id=emp_id;
        this.exp_yr=exp_yr;

    }
    @Override
    protected String doInBackground(String... params) {


        // String login_url = "https://4c58912a.ngrok.io/nexval/visit.php";
        //String login_url = "http://pentelican-thing.000webhostapp.com/nexval/exp_yr.php";

        if (type.equals("exp")) {

            try {

                String result="";
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("emp_id", "UTF-8") + "=" + URLEncoder.encode(emp_id, "UTF-8");
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



    }

    @Override
    protected void onPostExecute(String result) {

        int result_exp=Integer.parseInt(result);
        int year = Calendar.getInstance().get(Calendar.YEAR);

        if(result_exp>0) {

            exp_yr.setText(year-result_exp+"");

        }



    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

