package com.example.stealth.navigationdrawer1.rec_exp_profile;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.stealth.navigationdrawer1.R;

/**
 * Created by stealth on 28/2/18.
 */

public class profile_exp_holder extends RecyclerView.ViewHolder {

    TextView company,position,description,duration;


    public profile_exp_holder(View itemView) {
        super(itemView);

        company= (TextView) itemView.findViewById(R.id.company);
        position= (TextView) itemView.findViewById(R.id.position);
        description= (TextView) itemView.findViewById(R.id.description);
        duration= (TextView) itemView.findViewById(R.id.duration_exp);
        Typeface myCustomFont = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Quicksand-Light.ttf");
        Typeface myCustomFont4 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Pacifico-Regular.ttf");
        company.setTypeface(myCustomFont2);
        position.setTypeface(myCustomFont2);
        description.setTypeface(myCustomFont2);
        duration.setTypeface(myCustomFont2);


    }
}
