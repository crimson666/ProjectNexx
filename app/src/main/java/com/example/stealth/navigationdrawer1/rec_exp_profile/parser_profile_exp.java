package com.example.stealth.navigationdrawer1.rec_exp_profile;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.rec_education_profile.profile_edu_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by stealth on 28/2/18.
 */

public class parser_profile_exp extends AsyncTask<Void,Integer,Integer> {


    Context c;
    String data;
    RecyclerView rv_exp;
    ArrayList<String> company=new ArrayList<>();
    ArrayList<String> position=new ArrayList<>();
    ArrayList<String> description=new ArrayList<>();
    ArrayList<String> start=new ArrayList<>();
    ArrayList<String> end=new ArrayList<>();
    profile_exp_adapter adapter2;


    public parser_profile_exp(Context c, String data, RecyclerView rv_exp) {

        this.c = c;
        this.data = data;
        this.rv_exp = rv_exp;
    }



    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parse();
    }



    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        //  pd.dismiss();

        if(integer==1)
        {
            adapter2=new profile_exp_adapter(c,company,position,description,start,end);
            rv_exp.setAdapter(adapter2);
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

            company.clear();
            position.clear();
            description.clear();
            start.clear();
            end.clear();

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                company.add(jo.getString("company"));
                position.add(jo.getString("position"));
                description.add(jo.getString("description"));
                start.add(jo.getString("start"));
                end.add(jo.getString("end"));
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
