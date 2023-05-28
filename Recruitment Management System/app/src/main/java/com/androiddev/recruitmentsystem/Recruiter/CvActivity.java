package com.androiddev.recruitmentsystem.Recruiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.androiddev.recruitmentsystem.R;
import com.squareup.picasso.Picasso;

public class CvActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv);
        imageView = findViewById(R.id.cv_iv);

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");

        Picasso.with(CvActivity.this).load(image).placeholder(R.drawable.round_logo).into(imageView);
    }
}