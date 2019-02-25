package com.example.stealth.navigationdrawer1.jobs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.home_db_recy.home_rec_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by stealth on 6/4/18.
 */

public class parse_job extends AsyncTask<Void,Integer,Integer> {

    Context c;
    String data;
    RecyclerView rv;
    ProgressDialog pd;
    String eid,user_name,type;
    job_rec_adapter adapter;

    ArrayList<String> job_title=new ArrayList<>();
    ArrayList<String> job_id=new ArrayList<>();
    ArrayList<String> job_dept=new ArrayList<>();
    ArrayList<String> job_skill=new ArrayList<>();
    ArrayList<String> job_desc=new ArrayList<>();
    ArrayList<String> job_by=new ArrayList<>();
    ArrayList<String> job_req=new ArrayList<>();
    ArrayList<String> job_position=new ArrayList<>();

    public parse_job(Context c, String data, RecyclerView rv, String eid, String user_name,String type) {
        this.c=c;
        this.rv=rv;
        this.data=data;
        this.eid=eid;
        this.user_name=user_name;
        this.type=type;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parse Data");
        pd.setMessage("Parsing...Please Wait!");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        pd.dismiss();

        if(integer==1)
        {
            adapter=new job_rec_adapter(c,eid,user_name,job_title,job_id,job_dept,job_desc,job_skill,job_by,job_req,job_position,type);
            rv.setAdapter(adapter);
        }else {
            Toast.makeText(c,"Unable to parse data",Toast.LENGTH_SHORT).show();
        }
    }

    private int parse()
    {

        try
        {
            JSONArray ja=new JSONArray(data);
            JSONObject jo=null;

         job_title.clear();
         job_id.clear();
         job_dept.clear();
         job_desc.clear();
         job_skill.clear();
         job_by.clear();
         job_req.clear();
         job_position.clear();



            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                job_title.add(jo.getString("job_title"));
                job_id.add(jo.getString("job_id"));
                job_dept.add(jo.getString("job_dept"));
                job_desc.add(jo.getString("job_desc"));
                job_skill.add(jo.getString("job_skill"));
                job_by.add(jo.getString("emp_id"));
                job_req.add(jo.getString("job_req"));
                job_position.add(jo.getString("job_position"));


            }



            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}


