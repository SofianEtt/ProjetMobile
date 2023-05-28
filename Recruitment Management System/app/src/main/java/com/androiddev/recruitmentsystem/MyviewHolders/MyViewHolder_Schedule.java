package com.androiddev.recruitmentsystem.MyviewHolders;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.recruitmentsystem.R;


public class MyViewHolder_Schedule extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView name_tv, date_tv, time_tv,address_tv, accept_tv;
    public FrameLayout remove_btn, accept_btn,view_btn;
    public ImageView  call_btn;
    public LinearLayout card;



    public MyViewHolder_Schedule(View view) {
        super(view);
        imageView = view.findViewById(R.id.card_iv);
        name_tv = view.findViewById(R.id.name_tv);
        date_tv = view.findViewById(R.id.adpdate_tv);
        time_tv = view.findViewById(R.id.adptime_tv);
        card = view.findViewById(R.id.card);
        remove_btn = view.findViewById(R.id.remove_btn);
        accept_btn = view.findViewById(R.id.accept_btn);
        accept_tv = view.findViewById(R.id.accept_tv);
        call_btn = view.findViewById(R.id.phone_btn);
        address_tv = view.findViewById(R.id.address_tv);
        view_btn = view.findViewById(R.id.view_cv_btn);





    }
}
