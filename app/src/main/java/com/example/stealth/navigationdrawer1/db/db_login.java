package com.example.stealth.navigationdrawer1.db;
import com.example.stealth.navigationdrawer1.main_link;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.MainActivity;
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
 * Created by stealth on 7/1/18.
 */

public class db_login extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    public String log;
    Button b1;

    main_link obj_link=new main_link();
    String primary_link=obj_link.host_link();

    String login_url=primary_link+"login.php";








    int flag=0;
    public String id,token;
    public db_login(Context ctx, Button b1) {
        context = ctx;
        this.b1=b1;
    }


    @Override
    protected String doInBackground(String... params) {



        String type = params[0];
       // String login_url = "http://pentelican-thing.000webhostapp.com/nexval/login.php";
        // String login_url = "https://e738d6aa.ngrok.io/nexval/login.php";
        if (type.equals("login")) {

            try {
             String user_id = params[1];
                String password = params[2];
                token = params[3];
                String result="";
                id=user_id;


                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                        + URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8");
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

        if(result.equals("log_no")) {
            alertDialog.setMessage("Sorry wrong password or user id");
            alertDialog.show();

        }
        else {
            b1.setEnabled(false);
           alertDialog.setMessage("welcome " + result);
           alertDialog.show();
            Intent intent = new Intent(context,MainActivity.class);
            intent.putExtra("KEY",id);
          intent.putExtra("KEY2",result);
            context.startActivity(intent);
            //System.out.print("db_login"+ token);
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
