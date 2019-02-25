package com.example.stealth.navigationdrawer1.jobs;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.stealth.navigationdrawer1.R;

/**
 * Created by stealth on 6/4/18.
 */

public class job_rec_holder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ItemClickListener2 itemClickListener2;

    TextView title,dept,skill;
    public job_rec_holder(View itemView) {
        super(itemView);
        title=(TextView)itemView.findViewById(R.id.job_title);
        dept=(TextView)itemView.findViewById(R.id.job_dept);
        skill=(TextView)itemView.findViewById(R.id.req_skill);
        Typeface myCustomFont = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Quicksand-Light.ttf");
        Typeface myCustomFont4 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Pacifico-Regular.ttf");
        title.setTypeface(myCustomFont2);
        skill.setTypeface(myCustomFont2);
        dept.setTypeface(myCustomFont2);
        itemView.setOnClickListener(this);
    }
    public  void setItemClickListener(ItemClickListener2 ic)
    {
        this.itemClickListener2=ic;
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener2.onItemClick(getLayoutPosition());

    }
}
