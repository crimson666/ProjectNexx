package com.example.stealth.navigationdrawer1.post;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.stealth.navigationdrawer1.R;


/**
 * Created by stealth on 31/3/18.
 */

public class post_holder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView title,name;
    ClickListener click;
    public post_holder(View itemView) {
        super(itemView);

        title= (TextView) itemView.findViewById(R.id.title);
        name= (TextView) itemView.findViewById(R.id.post_by);
        Typeface myCustomFont2 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Quicksand-Medium.ttf");
        title.setTypeface(myCustomFont2);
        name.setTypeface(myCustomFont2);
        itemView.setOnClickListener(this);
    }


    public  void setItemClickListener(ClickListener ic)
    {
        this.click=ic;
    }

    @Override
    public void onClick(View v) {
        this.click.onclick(getLayoutPosition());
    }




}
