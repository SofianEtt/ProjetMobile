package com.androiddev.recruitmentsystem.Init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.androiddev.recruitmentsystem.R;

public class WelcomeActivity extends AppCompatActivity {
    ImageView next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        next_btn = findViewById(R.id.next_intro_btn);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=getSharedPreferences("INTRO",MODE_PRIVATE).edit();
                editor.putBoolean("intro",true);
                editor.apply();
                Intent i=new Intent(getBaseContext(), GuestActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}