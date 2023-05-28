package com.androiddev.recruitmentsystem.Init;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.androiddev.recruitmentsystem.Admin.AdminHomeActivity;
import com.androiddev.recruitmentsystem.Admin.SigninActivityAdmin;
import com.androiddev.recruitmentsystem.MainActivity;
import com.androiddev.recruitmentsystem.Manager.ManagerHomeActivity;
import com.androiddev.recruitmentsystem.Manager.SigninActivityManager;
import com.androiddev.recruitmentsystem.R;
import com.androiddev.recruitmentsystem.Recruiter.RecruiterHomeActivity;
import com.androiddev.recruitmentsystem.Recruiter.SigninActivityRecruiter;
import com.androiddev.recruitmentsystem.User.SigninActivity;

import java.util.Locale;

public class SigninInitActivity extends AppCompatActivity {
    FrameLayout candiate_btn, recruiter_btn, manager_btn, admin_btn;
    TextView register_tv;


    Spinner language_sp;
    String language = "English";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences preferences = getSharedPreferences("USER",MODE_PRIVATE);
        if (preferences.contains("phone")){
            if ((preferences.getString("status","")).equals("user")){
                startActivity(new Intent(SigninInitActivity.this, MainActivity.class));
                finish();
            }else if ((preferences.getString("status","")).equals("admin")){
                startActivity(new Intent(SigninInitActivity.this, AdminHomeActivity.class));
                finish();
            }else if ((preferences.getString("status","")).equals("recruiter")){
                startActivity(new Intent(SigninInitActivity.this, RecruiterHomeActivity.class));
                finish();
            }else if ((preferences.getString("status","")).equals("manager")){
                startActivity(new Intent(SigninInitActivity.this, ManagerHomeActivity.class));
                finish();
            }

        }


        setContentView(R.layout.activity_signin_init);

        candiate_btn = findViewById(R.id.candidate_btn);
        manager_btn = findViewById(R.id.manager_btn);
        admin_btn = findViewById(R.id.admin_btn);
        register_tv = findViewById(R.id.register_instead_tv);
        language_sp = findViewById(R.id.language_sp);

        candiate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninInitActivity.this, SigninActivity.class));
                finish();
            }
        });

        recruiter_btn = findViewById(R.id.recruiter_btn);

        recruiter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninInitActivity.this, SigninActivityRecruiter.class));
                finish();
            }
        });

        manager_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninInitActivity.this, SigninActivityManager.class));
                finish();
            }
        });

        admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninInitActivity.this, SigninActivityAdmin.class));
                finish();
            }
        });
        register_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninInitActivity.this, RegisterInitActivity.class));
                finish();
            }
        });




        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.lang,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language_sp.setAdapter(adapter);

        language_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                language = parent.getItemAtPosition(position).toString();
                if (language.equals("Select Language")){
                    return;
                }else
                if (language.equals("English")){
                    setlanguage(SigninInitActivity.this,"en");
                    startActivity(new Intent(SigninInitActivity.this,SigninInitActivity.class));
                    finish();

                }else if (language.equals("French")){
                    setlanguage(SigninInitActivity.this,"po");
                    startActivity(new Intent(SigninInitActivity.this,SigninInitActivity.class));
                    finish();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







    }

    public void setlanguage (Activity activity, String language){
        Locale locale = new Locale (language);
        Resources resources = activity.getResources ();
        Configuration configuration = resources.getConfiguration ();
        configuration.setLocale (locale);
        resources. updateConfiguration(configuration, resources.getDisplayMetrics ());
    }
}