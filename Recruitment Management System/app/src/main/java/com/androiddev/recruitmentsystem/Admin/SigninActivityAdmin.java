package com.androiddev.recruitmentsystem.Admin;


import static com.androiddev.recruitmentsystem.User.SigninActivity.LoginUserToApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.androiddev.recruitmentsystem.MainActivity;
import com.androiddev.recruitmentsystem.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SigninActivityAdmin extends AppCompatActivity {
    TextInputEditText phone_box, pwd_box;
    FrameLayout login_btn;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences preferences = getSharedPreferences("USER",MODE_PRIVATE);
        if (preferences.contains("phone")){
            startActivity(new Intent(SigninActivityAdmin.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_signinadmin);

        phone_box = findViewById(R.id.phone_box);
        pwd_box = findViewById(R.id.pwd_box);
        login_btn = findViewById(R.id.login_btn);



        databaseReference = FirebaseDatabase.getInstance().getReference();




        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phone_box.getText().toString().trim();
                String pwd = pwd_box.getText().toString().trim();



                if (phone.equals("")) {
                    phone_box.setError("Valid number is required");
                    phone_box.requestFocus();
                    return;
                }
                if (pwd.isEmpty()){
                    Toast.makeText(SigninActivityAdmin.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.length() < 8){
                    Toast.makeText(SigninActivityAdmin.this, "Password should have atleast 8 chars", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseReference.child("Admins").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(phone)){
                            if (pwd.equals(snapshot.child(phone).child("password").getValue(String.class))){

                                Toast.makeText(SigninActivityAdmin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                LoginUserToApp(SigninActivityAdmin.this,snapshot.child(phone).child("name").getValue(String.class),phone,snapshot.child(phone).child("email").getValue(String.class),"admin");
                                startActivity(new Intent(SigninActivityAdmin.this,AdminHomeActivity.class));
                                finish();
                            }else {
                                Toast.makeText(SigninActivityAdmin.this, "Check your Password", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(SigninActivityAdmin.this, "No Admin Registered with this Phone", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }


}