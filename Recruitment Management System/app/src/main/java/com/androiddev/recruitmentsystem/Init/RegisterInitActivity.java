package com.androiddev.recruitmentsystem.Init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.androiddev.recruitmentsystem.Manager.RegisterActivityManager;
import com.androiddev.recruitmentsystem.R;
import com.androiddev.recruitmentsystem.Recruiter.RegisterActivityRecruiter;
import com.androiddev.recruitmentsystem.User.RegisterActivity;

public class RegisterInitActivity extends AppCompatActivity {
    FrameLayout candiate_btn, recruiter_btn, manager_btn;
    TextView login_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_init);
        candiate_btn = findViewById(R.id.candidate_btn);
        manager_btn = findViewById(R.id.manager_btn);
        login_tv = findViewById(R.id.login_instead_tv);

        candiate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterInitActivity.this, RegisterActivity.class));
                finish();
            }
        });
        recruiter_btn = findViewById(R.id.recruiter_btn);

        recruiter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterInitActivity.this, RegisterActivityRecruiter.class));
                finish();
            }
        });
        manager_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterInitActivity.this, RegisterActivityManager.class));
                finish();
            }
        });
        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterInitActivity.this, SigninInitActivity.class));
                finish();
            }
        });

    }
}