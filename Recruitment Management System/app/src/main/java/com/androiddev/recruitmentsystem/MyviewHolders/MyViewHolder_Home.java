package com.androiddev.recruitmentsystem.MyviewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.recruitmentsystem.R;

public class MyViewHolder_Home extends RecyclerView.ViewHolder {

    public ImageView iv_productImage, fav_btn;
    public TextView name_tv, location_tv, subject_tv, price_tv, time_tv;
    public LinearLayout card;
    public ImageView delete_btn, edit_btn;

    public MyViewHolder_Home(View view) {
        super(view);


        iv_productImage = itemView.findViewById(R.id.card_iv);
        name_tv = itemView.findViewById(R.id.name_tv);
        location_tv = itemView.findViewById(R.id.location_tv);
        subject_tv = itemView.findViewById(R.id.subject_tv);
        price_tv = itemView.findViewById(R.id.price_tv);
        time_tv = itemView.findViewById(R.id.time_tv);
        card = itemView.findViewById(R.id.card);
        delete_btn = itemView.findViewById(R.id.delete_btn);
        fav_btn = itemView.findViewById(R.id.fav_btn);
        edit_btn = itemView.findViewById(R.id.edit_btn);


    }
}
