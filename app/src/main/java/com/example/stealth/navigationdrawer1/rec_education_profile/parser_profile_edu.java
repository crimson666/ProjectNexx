package com.example.stealth.navigationdrawer1.rec_education_profile;

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
 * Created by stealth on 15/1/18.
 */

public class parser_profile_edu extends AsyncTask<Void,Integer,Integer> {


    Context c;
    String data;
    RecyclerView rv_edu;
    ArrayList<String> institute=new ArrayList<>();
    ArrayList<String> degree=new ArrayList<>();
    ArrayList<String> grade=new ArrayList<>();
    ArrayList<String> duration=new ArrayList<>();
    profile_edu_adapter adapter2;

    public parser_profile_edu(Context c, String data, RecyclerView rv_edu) {

        this.c = c;
        this.data = data;
        this.rv_edu = rv_edu;
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
            adapter2=new profile_edu_adapter(c,institute,degree,grade,duration);
            rv_edu.setAdapter(adapter2);
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

            institute.clear();
            degree.clear();
            grade.clear();
            duration.clear();

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                institute.add(jo.getString("institute"));
                degree.add(jo.getString("degree"));
                grade.add(jo.getString("grade"));
                duration.add(jo.getString("duration"));
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
