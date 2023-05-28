package com.androiddev.recruitmentsystem.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.recruitmentsystem.Modules.ScheduleModule;
import com.androiddev.recruitmentsystem.MyviewHolders.MyViewHolder_Schedule;
import com.androiddev.recruitmentsystem.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class ScheduleRequestActivity extends AppCompatActivity {

    RecyclerView rv;
    ImageView back_btn;


    DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<ScheduleModule, MyViewHolder_Schedule> order_Adapter;
    FirebaseRecyclerOptions<ScheduleModule> orders_options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_request);
        back_btn = findViewById(R.id.back_btn);
        rv = findViewById(R.id.sr_rv);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        SharedPreferences preferences = getSharedPreferences("USER",MODE_PRIVATE);
        String phone = preferences.getString("phone","");
        load_orderAdapter(phone);
    }

    // This is the method which is getting data from Firebase database, parsing it and
    // showing in the recyclerview using LinearLayout manager. This is the Adapter
    public void load_orderAdapter(String string) {

        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("Schedules");
        Query query = databaseReference.orderByChild("sphone").equalTo(string);
        orders_options = new FirebaseRecyclerOptions.Builder<ScheduleModule>().setQuery(query, ScheduleModule.class).build();
        order_Adapter  = new FirebaseRecyclerAdapter<ScheduleModule, MyViewHolder_Schedule>(this.orders_options) {
            /* access modifiers changed from: protected */
            public void onBindViewHolder(MyViewHolder_Schedule holder, int i, ScheduleModule module) {
                Picasso.with(ScheduleRequestActivity.this).load(module.getTimage()).placeholder(R.drawable.round_logo).into(holder.imageView);
                holder.name_tv.setText(module.getTname());
                holder.address_tv.setVisibility(View.GONE);
                holder.date_tv.setText(module.getDate()+"");
                holder.time_tv.setText(module.getTime());

                holder.call_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:"+module.getTphone())));

                    }
                });


                holder.accept_btn.setVisibility(View.GONE);

                holder.remove_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleRequestActivity.this);
                        builder.setTitle((CharSequence) "Confirm");
                        builder.setMessage((CharSequence) "Are you sure you want to Remove?");
                        builder.setPositiveButton((CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.setNegativeButton((CharSequence) "Remove", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                databaseReference.child(module.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(ScheduleRequestActivity.this, "Removed", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });

                            }
                        });
                        builder.show();
                    }
                });



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