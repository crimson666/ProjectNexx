package com.example.stealth.navigationdrawer1.firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.stealth.navigationdrawer1.R;
import com.example.stealth.navigationdrawer1.login;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ACER on 8/14/2017.
 */

public class FcmInstanceIdService extends FirebaseInstanceIdService {
    login logIn;

    public FcmInstanceIdService(){}

    public FcmInstanceIdService(login logIn) {
        this.logIn = logIn;

    }
    @Override
    public void onTokenRefresh()
    {
        System.out.print("on Token refresh ");
        String recent_token= FirebaseInstanceId.getInstance().getToken();
//        Toast.makeText(this,recent_token,Toast.LENGTH_LONG).show();
        System.out.print("on Token refresh " + recent_token);
        SharedPreferences sharedPreferences= logIn.getSharedPreferences("nexval_grievance_app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("fcm_token_new",recent_token);
        editor.commit();
    }
}
