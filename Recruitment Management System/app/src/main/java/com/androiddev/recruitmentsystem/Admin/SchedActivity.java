package com.androiddev.recruitmentsystem.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.recruitmentsystem.Modules.ScheduleModule;
import com.androiddev.recruitmentsystem.MyviewHolders.MyViewHolder_Schedule;
import com.androiddev.recruitmentsystem.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SchedActivity extends AppCompatActivity {
    RecyclerView rv;
    DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<ScheduleModule, MyViewHolder_Schedule> order_Adapter;
    FirebaseRecyclerOptions<ScheduleModule> orders_options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);
        rv  = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);


    }
    public void load_orderAdapter(String string) {

        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("Schedules");
        orders_options = new FirebaseRecyclerOptions.Builder<ScheduleModule>().setQuery(databaseReference, ScheduleModule.class).build();
        order_Adapter  = new FirebaseRecyclerAdapter<ScheduleModule, MyViewHolder_Schedule>(this.orders_options) {
            /* access modifiers changed from: protected */
            public void onBindViewHolder(MyViewHolder_Schedule holder, int i, ScheduleModule module) {
                Picasso.with(SchedActivity.this).load(module.getTimage()).placeholder(R.drawable.round_logo).into(holder.imageView);
                holder.name_tv.setText(module.getSname());
                holder.date_tv.setText(module.getDate()+"");
                holder.address_tv.setVisibility(View.VISIBLE);
                holder.address_tv.setText(module.getSlocation());
                holder.time_tv.setText(module.getTime());




            }

            public MyViewHolder_Schedule onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new MyViewHolder_Schedule(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.schedule_adapter_layout, viewGroup, false));
            }
        };
//        this.order_Adapter = order_Adapter;
        order_Adapter.startListening();
        this.rv.setAdapter(this.order_Adapter);
    }
}