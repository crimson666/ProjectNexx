package com.example.stealth.navigationdrawer1.rec_exp_profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.main_link;
import com.example.stealth.navigationdrawer1.rec_education_profile.parser_profile_edu;

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

public class db_profile_exp  extends AsyncTask<Void,Integer,String> {


    Context c;
    main_link obj_link=new main_link();
    String primary_link=obj_link.host_link();

    String urlAddress=primary_link+"exp.php";

    // String urlAddress = "https://4c58912a.ngrok.io/nexval/exp.php";
    //String urlAddress = "http://pentelican-thing.000webhostapp.com/nexval/exp.php";

    RecyclerView rv_exp;
    public String a="";
    ProgressDialog pd;


    public db_profile_exp(Context c, String a, RecyclerView rv) {
        this.c = c;
        this.a = a;
        this.rv_exp = rv;
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
            parser_profile_exp p_exp=new parser_profile_exp(c,data,rv_exp);
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
