package com.example.stealth.navigationdrawer1.admin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import com.example.stealth.navigationdrawer1.MainActivity;
import com.example.stealth.navigationdrawer1.admin_home;
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

public class db_authentication extends AsyncTask<String,Void,String> {


    String eid,user_name;
    AlertDialog alertDialog;
    Context c;

    main_link obj_link=new main_link();
    String primary_link=obj_link.host_link();

    String url_admin=primary_link+"authentication.php";

    public db_authentication(Context c, String a, String usr_name) {
        this.eid=a;
        this.user_name=usr_name;
        this.c=c;
    }

    @Override
    protected String doInBackground(String... params) {
        String type ="admin";


       // String url_admin = "http://pentelican-thing.000webhostapp.com/nexval/authentication.php";
        // String login_url = "https://e738d6aa.ngrok.io/nexval/login.php";
        if (type.equals("admin")) {

            try {

                String result="";
                URL url = new URL(url_admin);
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
        alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setTitle("procedure status");
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("no")) {
            alertDialog.setMessage("Access denied. Admin only");
            alertDialog.show();

        }
        else {
            Intent intent = new Intent(c,admin_home.class);
            intent.putExtra("KEY",eid);
            intent.putExtra("NAME",user_name);
            c.startActivity(intent);
            //System.out.print("db_login"+ token);

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
