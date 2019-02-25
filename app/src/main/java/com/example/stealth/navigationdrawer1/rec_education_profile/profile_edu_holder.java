package com.example.stealth.navigationdrawer1.rec_education_profile;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.stealth.navigationdrawer1.R;
import com.example.stealth.navigationdrawer1.home_db_recy.ItemClickListener;

/**
 * Created by stealth on 15/1/18.
 */

public class profile_edu_holder extends RecyclerView.ViewHolder {

    TextView institute,degree,grade,duration;


    public profile_edu_holder(View itemView) {
        super(itemView);

        institute= (TextView) itemView.findViewById(R.id.institute);
        degree= (TextView) itemView.findViewById(R.id.degree);
        grade= (TextView) itemView.findViewById(R.id.grade);
        duration= (TextView) itemView.findViewById(R.id.duration);
        Typeface myCustomFont = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Quicksand-Light.ttf");
        Typeface myCustomFont4 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Pacifico-Regular.ttf");
        institute.setTypeface(myCustomFont2);
        degree.setTypeface(myCustomFont2);
        grade.setTypeface(myCustomFont2);
        duration.setTypeface(myCustomFont2);



    }
}
