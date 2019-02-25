package com.example.stealth.navigationdrawer1.profile_backgroud;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.MainActivity;
import com.example.stealth.navigationdrawer1.edit_profile;
import com.example.stealth.navigationdrawer1.main_link;
import com.example.stealth.navigationdrawer1.me_profile;

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
 * Created by stealth on 4/3/18.
 */

public class db_editprofile extends AsyncTask<String,Void,String> {


    Context context;
    AlertDialog alertDialog;
    String emp_id,usr_name;

    main_link obj_link=new main_link();
    String primary_link=obj_link.host_link();

    String login_url=primary_link+"edit.php";


    public db_editprofile(Context ctx) {
        context = ctx;

    }


    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
       // String login_url = "https://4c58912a.ngrok.io/nexval/edit.php";
       // String login_url = "http://pentelican-thing.000webhostapp.com/nexval/edit.php";

        if (type.equals("update")) {

            try {
                emp_id=params[1];
                String phone = params[2];
                String email = params[3];
                String address = params[4];

                String fb = params[5];
                String linkedin = params[6];
                String about = params[7];
                String skill = params[8];
                usr_name=params[9];
                String result="";


                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("emp_id", "UTF-8") + "=" + URLEncoder.encode(emp_id, "UTF-8") + "&"
                        + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8")+"&"
                        + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")+"&"
                        + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8")+"&"
                        + URLEncoder.encode("about", "UTF-8") + "=" + URLEncoder.encode(about, "UTF-8")+"&"
                        + URLEncoder.encode("fb", "UTF-8") + "=" + URLEncoder.encode(fb, "UTF-8")+"&"
                        + URLEncoder.encode("linkedin", "UTF-8") + "=" + URLEncoder.encode(linkedin, "UTF-8")+"&"
                        + URLEncoder.encode("skill", "UTF-8") + "=" + URLEncoder.encode(skill, "UTF-8");
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

        if(result.equals("success")) {
            alertDialog.setMessage("successfully updated");
            alertDialog.show();
            Intent intent = new Intent(context,MainActivity.class);
            intent.putExtra("KEY",emp_id);
            intent.putExtra("KEY2",usr_name);
            context.startActivity(intent);



        }
        else {
             alertDialog.setMessage("oh no");
             alertDialog.show();

        }

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
