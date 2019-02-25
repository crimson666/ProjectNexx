package com.example.stealth.navigationdrawer1.rec_education_profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.home_db_recy.db_parser;
import com.example.stealth.navigationdrawer1.main_link;
import com.example.stealth.navigationdrawer1.profile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by stealth on 15/1/18.
 */

public class db_profile_edu  extends AsyncTask<Void,Integer,String> {

    Context c;

    main_link obj_link=new main_link();
    String primary_link=obj_link.host_link();

    String urlAddress=primary_link+"education.php";

    // String urlAddress = "https://4c58912a.ngrok.io/nexval/education.php";
   // String urlAddress = "http://pentelican-thing.000webhostapp.com/nexval/education.php";

    RecyclerView rv_edu;
    public String a="";
    ProgressDialog pd;

    public db_profile_edu(Context c, String a, RecyclerView rv) {
        this.c = c;
        this.a = a;
        this.rv_edu = rv;
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
            parser_profile_edu p=new parser_profile_edu(c,data,rv_edu);
            p.execute();

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
