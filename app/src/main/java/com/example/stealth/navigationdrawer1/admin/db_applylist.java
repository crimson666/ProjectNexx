package com.example.stealth.navigationdrawer1.admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.admin_jobapply;
import com.example.stealth.navigationdrawer1.home_db_recy.db_parser;
import com.example.stealth.navigationdrawer1.main_link;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by stealth on 13/4/18.
 */

public class db_applylist extends AsyncTask<Void,Integer,String> {
    ProgressDialog pd;

    main_link obj_link=new main_link();
    String primary_link=obj_link.host_link();

    String urlAddress=primary_link+"apply_list.php";

   // String urlAddress = "http://pentelican-thing.000webhostapp.com/nexval/apply_list.php";
    Context c;
    String job_id,eid;
    RecyclerView rv;
    public db_applylist(Context c, String job_id,String eid, RecyclerView rv) {
        this.c=c;
        this.job_id=job_id;
        this.rv=rv;
        this.eid=eid;
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
    protected String doInBackground(Void... params) {
        String data=this.downloadData();
        return data;
    }


    @Override
    protected void onPostExecute(String data) {
        super.onPostExecute(data);

        pd.dismiss();

        if(data != null)
        {
            db_parser p=new db_parser(c,data,rv,eid);
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
            URL url=new URL(urlAddress+"?Type="+job_id);
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




