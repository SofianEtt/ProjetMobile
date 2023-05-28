package com.androiddev.recruitmentsystem.MyviewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.recruitmentsystem.R;

public class MyViewHolder_Company extends RecyclerView.ViewHolder {

    public TextView name_tv, id_tv;

    public MyViewHolder_Company(View view) {
        super(view);


        name_tv = itemView.findViewById(R.id.name_tv);
        id_tv = itemView.findViewById(R.id.id_tv);


    }
}
