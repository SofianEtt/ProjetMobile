package com.androiddev.recruitmentsystem.Init;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.androiddev.recruitmentsystem.Admin.AdminHomeActivity;
import com.androiddev.recruitmentsystem.MainActivity;
import com.androiddev.recruitmentsystem.Manager.ManagerHomeActivity;
import com.androiddev.recruitmentsystem.R;
import com.androiddev.recruitmentsystem.Recruiter.RecruiterHomeActivity;

public class SuplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suplash);





        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(1000);

                    // After 5 seconds redirect to another intent

                    SharedPreferences preferences = getSharedPreferences("USER",MODE_PRIVATE);
                    if (preferences.contains("phone")){
                        if ((preferences.getString("status","")).equals("user")){
                            startActivity(new Intent(SuplashActivity.this, MainActivity.class));
                            finish();
                        }else if ((preferences.getString("status","")).equals("admin")){
                            startActivity(new Intent(SuplashActivity.this, AdminHomeActivity.class));
                            finish();
                        }else if ((preferences.getString("status","")).equals("recruiter")){
                            startActivity(new Intent(SuplashActivity.this, RecruiterHomeActivity.class));
                            finish();
                        }else if ((preferences.getString("status","")).equals("manager")){
                            startActivity(new Intent(SuplashActivity.this, ManagerHomeActivity.class));
                            finish();
                        }

                    }else {
                        SharedPreferences spreferences = getSharedPreferences("INTRO", MODE_PRIVATE);

                        if (spreferences.contains("intro")){
                            Intent i=new Intent(getBaseContext(), GuestActivity.class);
                            startActivity(i);
                            finish();
                        }else {
                            Intent i=new Intent(getBaseContext(), WelcomeActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }





                } catch (Exception e) {
//                    Toast.makeText(SuplashActivity.this, "exception test", Toast.LENGTH_SHORT).show();
                }
            }
        };
        // start thread
        background.start();



    }
}