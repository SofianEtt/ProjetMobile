package com.androiddev.recruitmentsystem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.recruitmentsystem.Init.GuestActivity;
import com.androiddev.recruitmentsystem.Init.SigninInitActivity;
import com.androiddev.recruitmentsystem.Modules.PostModule;
import com.androiddev.recruitmentsystem.MyviewHolders.MyViewHolder_Home;
import com.androiddev.recruitmentsystem.User.MappingActivity;
import com.androiddev.recruitmentsystem.User.ScheduleActivity;
import com.androiddev.recruitmentsystem.User.ScheduleRequestActivity;
import com.androiddev.recruitmentsystem.User.UserMyAccountActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.special.ResideMenu.BuildConfig;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    ImageView nav_btn, myAccount_btn,location_btn;
    RecyclerView rv;






    DatabaseReference databaseReference;
    DatabaseReference reference;
    FirebaseRecyclerAdapter<PostModule, MyViewHolder_Home> order_Adapter;
    FirebaseRecyclerOptions<PostModule> orders_options;




    LinearLayout terms_btn, share_btn, rate_btn, logout_btn, myApplies_btn;
    FrameLayout menu_layout;
    ImageView menu_close_btn;
    Boolean isPressed = false;

    EditText search_bar;
    ImageView search_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        location_btn = findViewById(R.id.location_btn);
        search_bar = findViewById(R.id.search_bar);
        search_button = findViewById(R.id.search_button);


        location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MappingActivity.class));
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = search_bar.getText().toString().trim();
                performSearch(searchText);
            }
        });

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);


        myApplies_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScheduleRequestActivity.class));
            }
        });
        myAccount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserMyAccountActivity.class));
            }
        });



        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                        startActivity(new Intent(MainActivity.this, SigninInitActivity.class));
                        finish();
                    }
                });
                builder.show();
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
                startActivity(new Intent(MainActivity.this, TermsActivity.class));
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








        SharedPreferences preferences = getSharedPreferences("USER",MODE_PRIVATE);
        String phone = preferences.getString("phone","");
        load_orderAdapter();

    }

    // This is the method which is getting data from Firebase database, parsing it and
    // showing in the recyclerview using LinearLayout manager.
    public void load_orderAdapter() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("ADS");
//        Query query = databaseReference.orderByChild("phone").equalTo(string);
        orders_options = new FirebaseRecyclerOptions.Builder<PostModule>().setQuery(databaseReference, PostModule.class).build();
        order_Adapter  = new FirebaseRecyclerAdapter<PostModule, MyViewHolder_Home>(this.orders_options) {
            /* access modifiers changed from: protected */
            public void onBindViewHolder(MyViewHolder_Home holder, int i, PostModule module) {
                holder.delete_btn.setVisibility(View.GONE);
                Picasso.with(MainActivity.this).load(module.getImage()).placeholder(R.drawable.round_logo).into(holder.iv_productImage);
                holder.name_tv.setText(module.getName()+", "+module.getEducation());
                holder.location_tv.setText(module.getLocation());
                holder.subject_tv.setText(module.getSubject());
                holder.price_tv.setText(module.getPrice());
                holder.time_tv.setText(module.getTime());

                holder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                        intent.putExtra("name",holder.name_tv.getText().toString());
                        intent.putExtra("phone",module.getPhone());
                        intent.putExtra("desc",module.getDescription());
                        intent.putExtra("id",module.getId());
                        intent.putExtra("image",module.getImage());

                        startActivity(intent);
                    }
                });

                holder.fav_btn.setVisibility(View.VISIBLE);

                holder.fav_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isPressed){
                            isPressed = true;
                            holder.fav_btn.setImageResource(R.drawable.baseline_favorite_24);
                            Toast.makeText(MainActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                        }else if (isPressed){
                            isPressed = false;
                            holder.fav_btn.setImageResource(R.drawable.baseline_favorite_border_24);
                            Toast.makeText(MainActivity.this, "Remove from Favorites", Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }

            public MyViewHolder_Home onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new MyViewHolder_Home(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_adapter_layout, viewGroup, false));
            }
        };
//        this.order_Adapter = order_Adapter;
        order_Adapter.startListening();
        this.rv.setAdapter(this.order_Adapter);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle((CharSequence) "Confirm");
        builder.setMessage((CharSequence) "Are you sure you want to Exit?");
        builder.setPositiveButton((CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton((CharSequence) "Exit", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.super.onBackPressed();
            }
        });
        builder.show();




    }

    private void performSearch(String searchText) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ADS");
        Query searchQuery = databaseReference.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerOptions<PostModule> searchOptions = new FirebaseRecyclerOptions.Builder<PostModule>()
                .setQuery(searchQuery, PostModule.class)
                .build();

        FirebaseRecyclerAdapter<PostModule, MyViewHolder_Home> searchAdapter = new FirebaseRecyclerAdapter<PostModule, MyViewHolder_Home>(searchOptions) {
            protected void onBindViewHolder(MyViewHolder_Home holder, int position, PostModule module) {
                holder.delete_btn.setVisibility(View.GONE);
                Picasso.with(MainActivity.this).load(module.getImage()).placeholder(R.drawable.round_logo).into(holder.iv_productImage);
                holder.name_tv.setText(module.getName()+", "+module.getEducation());
                holder.location_tv.setText(module.getLocation());
                holder.subject_tv.setText(module.getSubject());
                holder.price_tv.setText(module.getPrice());
                holder.time_tv.setText(module.getTime());

                holder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                        intent.putExtra("name",holder.name_tv.getText().toString());
                        intent.putExtra("phone",module.getPhone());
                        intent.putExtra("desc",module.getDescription());
                        intent.putExtra("id",module.getId());
                        intent.putExtra("image",module.getImage());

                        startActivity(intent);
                    }
                });

                holder.fav_btn.setVisibility(View.VISIBLE);

                holder.fav_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isPressed){
                            isPressed = true;
                            holder.fav_btn.setImageResource(R.drawable.baseline_favorite_24);
                            Toast.makeText(MainActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                        }else if (isPressed){
                            isPressed = false;
                            holder.fav_btn.setImageResource(R.drawable.baseline_favorite_border_24);
                            Toast.makeText(MainActivity.this, "Remove from Favorites", Toast.LENGTH_SHORT).show();
                        }

                    }
                });



                // ...
            }

            public MyViewHolder_Home onCreateViewHolder(ViewGroup viewGroup, int viewType) {
                return new MyViewHolder_Home(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_adapter_layout, viewGroup, false));
            }
        };

        // Update the adapter for the search results
        rv.setAdapter(searchAdapter);
        searchAdapter.startListening();
    }

}