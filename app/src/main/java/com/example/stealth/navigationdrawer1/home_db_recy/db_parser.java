package com.example.stealth.navigationdrawer1.home_db_recy;

/**
 * Created by stealth on 11/1/18.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class db_parser extends AsyncTask<Void,Integer,Integer> {

    Context c;
    String data;
    RecyclerView rv;

    ProgressDialog pd;
    ArrayList<String> fname=new ArrayList<>();
    ArrayList<String> lname=new ArrayList<>();
    ArrayList<String> eid=new ArrayList<>();
    ArrayList<String> dept=new ArrayList<>();
    ArrayList<String> position=new ArrayList<>();
    ArrayList<String> about=new ArrayList<>();
    ArrayList<String> phone=new ArrayList<>();
    ArrayList<String> email=new ArrayList<>();
    ArrayList<String> address=new ArrayList<>();
    ArrayList<String> fb=new ArrayList<>();
    ArrayList<String> linkedin=new ArrayList<>();
    ArrayList<String> follower=new ArrayList<>();
    ArrayList<String> following=new ArrayList<>();
    ArrayList<String> skill=new ArrayList<>();
    home_rec_adapter adapter;
    public String me_id;

    public db_parser(Context c, String data, RecyclerView rv,String me_id) {
        this.c = c;
        this.data = data;
        this.rv = rv;
        this.me_id=me_id;
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
            adapter=new home_rec_adapter(c,fname,lname,eid,dept,position,about,phone,email,address,fb,linkedin,me_id,following,follower,skill);
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

            fname.clear();
            lname.clear();
            eid.clear();
            dept.clear();
            position.clear();
            about.clear();
            phone.clear();
            email.clear();
            address.clear();
            fb.clear();
            linkedin.clear();
            follower.clear();
            following.clear();
            skill.clear();



            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                fname.add(jo.getString("first_name"));
                lname.add(jo.getString("last_name"));
                eid.add(jo.getString("e_id"));
                dept.add(jo.getString("dept"));
                position.add(jo.getString("position"));
                about.add(jo.getString("about"));
                phone.add(jo.getString("Phone_no"));
                email.add(jo.getString("email"));
                address.add(jo.getString("address"));
                fb.add(jo.getString("fb"));
                linkedin.add(jo.getString("linkedin"));
                following.add(jo.getString("following"));
                follower.add(jo.getString("follower"));
                skill.add(jo.getString("skill"));

            }



            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}


