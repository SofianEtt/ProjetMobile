package com.androiddev.recruitmentsystem.Manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androiddev.recruitmentsystem.Init.GuestActivity;
import com.androiddev.recruitmentsystem.Modules.PostModule;
import com.androiddev.recruitmentsystem.MyviewHolders.MyViewHolder_Manager;
import com.androiddev.recruitmentsystem.R;

import com.androiddev.recruitmentsystem.Init.SigninInitActivity;
import com.androiddev.recruitmentsystem.TermsActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.special.ResideMenu.BuildConfig;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
import com.squareup.picasso.Picasso;

public class ManagerHomeActivity extends AppCompatActivity {

    ImageView nav_btn, myAccount_btn;
    RecyclerView rv;

    LinearLayout terms_btn, share_btn, rate_btn, logout_btn, myApplies_btn;
    FrameLayout menu_layout;
    ImageView menu_close_btn;
    Boolean isPressed = false;


//    private ResideMenu resideMenu;
//    private ResideMenuItem myAccount;
//    private ResideMenuItem terms_btn;
//    private ResideMenuItem shareUs_btn;
//    private ResideMenuItem rateUse_btn;
//    private ResideMenuItem myPosts_btn;
//    private ResideMenuItem logout_btn;



    DatabaseReference databaseReference;
    DatabaseReference reference;
    FirebaseRecyclerAdapter<PostModule, MyViewHolder_Manager> order_Adapter;
    FirebaseRecyclerOptions<PostModule> orders_options;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);
        nav_btn  = findViewById(R.id.nav_btn);
        myAccount_btn  = findViewById(R.id.my_account_btn);
        rv  = findViewById(R.id.rv);
        nav_btn  = findViewById(R.id.nav_btn);
        terms_btn = findViewById(R.id.terms_btn);
        share_btn = findViewById(R.id.share_btn);
        rate_btn = findViewById(R.id.rate_btn);
        logout_btn = findViewById(R.id.login_btn);
        myApplies_btn = findViewById(R.id.applies_btn);
        menu_layout = findViewById(R.id.menu_layout);
        menu_close_btn = findViewById(R.id.menu_close_btn);


        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);


        myAccount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerHomeActivity.this, ManagerMyAccountActivity.class));
            }
        });

        menu_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPressed = false;
                menu_layout.animate().translationX((float)(-menu_layout.getWidth())).alpha(1.0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });

            }
        });

        terms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerHomeActivity.this, TermsActivity.class));
            }
        });






        nav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_layout.animate().translationX(0.0f).alpha(1.0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });

                isPressed = true;

            }
        });


        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                    String shareMessage= "Share";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + com.special.ResideMenu.BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });
        rate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                    String shareMessage= "Share";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });






        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManagerHomeActivity.this);
                builder.setTitle((CharSequence) "Confirm");
                builder.setMessage((CharSequence) "Are you sure you want to LOGOUT?");
                builder.setPositiveButton((CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton((CharSequence) "LOGOUT", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sharedPreferences =getSharedPreferences("USER",MODE_PRIVATE);
                        sharedPreferences.edit().clear().commit();
                        startActivity(new Intent(ManagerHomeActivity.this, SigninInitActivity.class));
                        finish();
                    }
                });
                builder.show();
            }
        });








        reference = FirebaseDatabase.getInstance().getReference().child("Managers");
        //Logout button code




        SharedPreferences preferences = getSharedPreferences("USER",MODE_PRIVATE);
        String phone = preferences.getString("phone","");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String cid = snapshot.child(phone).child("cregis").getValue(String.class);
                Toast.makeText(ManagerHomeActivity.this, cid, Toast.LENGTH_SHORT).show();
                load_orderAdapter(cid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    // This is the method which is getting data from Firebase database, parsing it and
    // showing in the recyclerview using LinearLayout manager.
    public void load_orderAdapter(String cid) {
        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("ADS");
        Query query = databaseReference.orderByChild("cregis").equalTo(cid);
        orders_options = new FirebaseRecyclerOptions.Builder<PostModule>().setQuery(query, PostModule.class).build();
        order_Adapter  = new FirebaseRecyclerAdapter<PostModule, MyViewHolder_Manager>(this.orders_options) {
            /* access modifiers changed from: protected */
            public void onBindViewHolder(MyViewHolder_Manager holder, int i, PostModule module) {
                Picasso.with(ManagerHomeActivity.this).load(module.getImage()).placeholder(R.drawable.round_logo).into(holder.iv_productImage);
                holder.name_tv.setText(module.getName()+", "+module.getEducation());
                holder.subject_tv.setText(module.getSubject());
                holder.price_tv.setText(module.getPrice());
                holder.applies_tv.setText("Applies: "+module.getApplies());
                holder.employes_tv.setText("Interviews: "+module.getInterviewing());

//


            }

            public MyViewHolder_Manager onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new MyViewHolder_Manager(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.manager_adapter_layout, viewGroup, false));
            }
        };
//        this.order_Adapter = order_Adapter;
        order_Adapter.startListening();
        this.rv.setAdapter(this.order_Adapter);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ManagerHomeActivity.this);
        builder.setTitle((CharSequence) "Confirm");
        builder.setMessage((CharSequence) "Are you sure you want to Exit?");
        builder.setPositiveButton((CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton((CharSequence) "Exit", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ManagerHomeActivity.super.onBackPressed();
            }
        });
        builder.show();




    }

}