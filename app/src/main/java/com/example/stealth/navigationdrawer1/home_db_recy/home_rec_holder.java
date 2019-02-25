package com.example.stealth.navigationdrawer1.home_db_recy;

/**
 * Created by stealth on 11/1/18.
 */

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.stealth.navigationdrawer1.R;


/**
 * Created by Oclemmy on 3/29/2016 for ProgrammingWizards Channel.
 */
public class home_rec_holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView name,eid,dept;
    ItemClickListener itemClickListener;



    public home_rec_holder(View itemView) {
        super(itemView);

        name= (TextView) itemView.findViewById(R.id.name);
        eid= (TextView) itemView.findViewById(R.id.eid);
        dept= (TextView) itemView.findViewById(R.id.dept);

        Typeface myCustomFont = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Lobster-Regular.ttf");
        Typeface myCustomFont2 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Quicksand-Medium.ttf");
        Typeface myCustomFont3 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Quicksand-Light.ttf");
        Typeface myCustomFont4 = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/Pacifico-Regular.ttf");
        name.setTypeface(myCustomFont2);
        eid.setTypeface(myCustomFont2);
        dept.setTypeface(myCustomFont2);

        itemView.setOnClickListener(this);
    }

    public  void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(getLayoutPosition());
    }


}
