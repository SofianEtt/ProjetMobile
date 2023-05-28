package com.androiddev.recruitmentsystem.Recruiter;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.androiddev.recruitmentsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForgotPasswordRecruiter extends AppCompatActivity {
    TextInputEditText phone_box, pwd_box, cPwd_box;
    FrameLayout okay_btn;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_user);
        phone_box = findViewById(R.id.phone_box);
        pwd_box = findViewById(R.id.pwd_box);
        cPwd_box = findViewById(R.id.cpwd_box);
        okay_btn = findViewById(R.id.okay_btn);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        okay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone= phone_box.getText().toString();
                String pwd = pwd_box.getText().toString();
                String cpwd = cPwd_box.getText().toString();

                if (phone.isEmpty() ) {
                    phone_box.setError("Valid number is required");
                    phone_box.requestFocus();
                    return;
                }
                if (pwd.isEmpty()){
                    Toast.makeText(ForgotPasswordRecruiter.this, "Please Enter Passwords", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pwd.equals(cpwd)){
                    Toast.makeText(ForgotPasswordRecruiter.this, "Please confirm Passwords", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.length()<8){
                    Toast.makeText(ForgotPasswordRecruiter.this, "Password must be of 8 chars", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseReference.child("Recruiters").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(phone)){
                            databaseReference.child("Recruiters").child(phone).child("password").setValue(pwd).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(ForgotPasswordRecruiter.this, "Password changed", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });

                        }else {
                            Toast.makeText(ForgotPasswordRecruiter.this, "No user Registered with this Phone", Toast.LENGTH_SHORT).show();
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