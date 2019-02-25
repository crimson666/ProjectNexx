package com.example.stealth.navigationdrawer1.post;

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
 * Created by stealth on 31/3/18.
 */

public class parse_post extends AsyncTask<Void,Integer,Integer> {

    Context c;
    String data;
    RecyclerView rv_post;
    ArrayList<String> first_name=new ArrayList<>();
    ArrayList<String> last_name=new ArrayList<>();
    ArrayList<String> title=new ArrayList<>();
    ArrayList<String> body=new ArrayList<>();
    ArrayList<String> fb=new ArrayList<>();
    ArrayList<String> linkedin=new ArrayList<>();
    ArrayList<String> twitter=new ArrayList<>();
    post_adapter post_adapter;



    public parse_post(Context c, String data, RecyclerView rv_post) {

        this.c = c;
        this.data = data;
        this.rv_post = rv_post;
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
            post_adapter=new post_adapter(c,first_name,last_name,title,body,fb,linkedin,twitter);
            rv_post.setAdapter(post_adapter);
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

            first_name.clear();
            last_name.clear();
            title.clear();
            body.clear();
            fb.clear();
            linkedin.clear();
            twitter.clear();

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                first_name.add(jo.getString("first_name"));
                last_name.add(jo.getString("last_name"));
                title.add(jo.getString("title"));
                body.add(jo.getString("body"));
                fb.add(jo.getString("fb"));
                linkedin.add(jo.getString("linkedin"));
                twitter.add(jo.getString("twitter"));
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
