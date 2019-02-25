package com.example.stealth.navigationdrawer1.profile_backgroud;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.main_link;
import com.example.stealth.navigationdrawer1.me_profile;
import com.example.stealth.navigationdrawer1.rec_exp_profile.parser_profile_exp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by stealth on 28/2/18.
 */

public class db_myprofile extends AsyncTask<Void,Integer,String> {



    Context c;
    main_link obj_link=new main_link();
    String primary_link=obj_link.host_link();

    String urlAddress=primary_link+"profile.php";


    //String urlAddress = "https://4c58912a.ngrok.io/nexval/profile.php";
  // String urlAddress = "http://pentelican-thing.000webhostapp.com/nexval/profile.php";

    public String a="";
    ProgressDialog pd;
    TextView eid,name,position,about,phone,email,address,fb,linkedin,following,follower,skill;


    public db_myprofile(Context c, String str_eid, TextView eid, TextView name, TextView position, TextView about, TextView phone, TextView email, TextView address, TextView fb, TextView linkedin,TextView following,TextView follower,TextView skill) {
        this.c = c;
        this.a = str_eid;
        this.eid=eid;
        this.name=name;
        this.position=position;

        this.about=about;
        this.phone=phone;
        this.address=address;
        this.email=email;
        this.fb=fb;
        this.linkedin=linkedin;
        this.follower=follower;
        this.following=following;
        this.skill=skill;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Download Data");
        pd.setMessage("Downloading...Pease wait!");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {

        String data=this.downloadData();
        return data;

    }


    @Override
    protected void onPostExecute(String data) {
        super.onPostExecute(data);

        pd.dismiss();

        if(data != null)
        {
            parser_myprofile p_exp=new parser_myprofile(c,data,eid,name,position,about,phone,email,address,fb,linkedin,following,follower,skill);
            p_exp.execute();

        }else {
            Toast.makeText(c,"Unable to download",Toast.LENGTH_SHORT).show();
        }

    }


    private String downloadData()
    {
        InputStream is=null;
        String line=null;

        try
        {
            Log.d("employee id in try", a);
            URL url=new URL(urlAddress+"?Type="+a);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            is=new BufferedInputStream(con.getInputStream());

            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuffer sb=new StringBuffer();

            if(br != null)
            {
                while ((line=br.readLine()) != null)
                {
                    sb.append(line+"\n");
                }
            }else
            {
                return null;
            }

            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null)
            {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
