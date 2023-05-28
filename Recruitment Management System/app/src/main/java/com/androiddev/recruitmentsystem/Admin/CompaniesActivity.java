package com.androiddev.recruitmentsystem.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.androiddev.recruitmentsystem.Modules.ManagerModule;
import com.androiddev.recruitmentsystem.MyviewHolders.MyViewHolder_Company;
import com.androiddev.recruitmentsystem.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompaniesActivity extends AppCompatActivity {
    RecyclerView rv;
    DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<ManagerModule, MyViewHolder_Company> order_Adapter;
    FirebaseRecyclerOptions<ManagerModule> orders_options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);
        rv  = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);

        load_orderAdapter();

    }
    public void load_orderAdapter() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("Managers");
        orders_options = new FirebaseRecyclerOptions.Builder<ManagerModule>().setQuery(databaseReference, ManagerModule.class).build();
        order_Adapter  = new FirebaseRecyclerAdapter<ManagerModule, MyViewHolder_Company>(this.orders_options) {
            /* access modifiers changed from: protected */
            public void onBindViewHolder(MyViewHolder_Company holder, int i, ManagerModule module) {
                holder.name_tv.setText(module.getCname());
                holder.id_tv.setText(module.getCregis());





            }

            public MyViewHolder_Company onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new MyViewHolder_Company(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.company_adapter_layout, viewGroup, false));
            }
        };
//        this.order_Adapter = order_Adapter;
        order_Adapter.startListening();
        this.rv.setAdapter(this.order_Adapter);
    }
}