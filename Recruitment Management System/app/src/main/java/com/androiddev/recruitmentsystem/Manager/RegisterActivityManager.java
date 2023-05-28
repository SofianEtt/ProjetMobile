package com.androiddev.recruitmentsystem.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.androiddev.recruitmentsystem.Modules.ManagerModule;
import com.androiddev.recruitmentsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivityManager extends AppCompatActivity {
    TextInputEditText  editTextPhone, cname_box, cregis_box;
    FrameLayout buttonContinue;
    TextInputEditText name_box,pwd_box,pwd_confrim_box;
    Spinner gender_sp;
    String gender;
    DatabaseReference databaseReference;

    TextView loginhere_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_manager);
        editTextPhone = findViewById(R.id.phone_box);
        buttonContinue = findViewById(R.id.signup_btn);
        name_box = findViewById(R.id.name_box);
        pwd_box = findViewById(R.id.pwd_box);
        pwd_confrim_box = findViewById(R.id.cpwd_box);
        gender_sp = findViewById(R.id.gender_sp);
        loginhere_tv = findViewById(R.id.loginhere_tv);
        cname_box = findViewById(R.id.companyName_box);
        cregis_box = findViewById(R.id.companyRegistrationno_box);

        loginhere_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivityManager.this, SigninActivityManager.class));
                finish();
            }
        });





        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_sp.setAdapter(adapter);

        gender_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString().trim();
                String name = name_box.getText().toString().trim();
                String pwd = pwd_box.getText().toString().trim();
                String pwd_cnfrm  = pwd_confrim_box.getText().toString().trim();

                String cname = cname_box.getText().toString().trim();
                String cregi = cregis_box.getText().toString().trim();




                if (name.isEmpty()){
                    name_box.setError("Name is required");
                    name_box.requestFocus();
                    return;
                }

                if (pwd.isEmpty() || pwd_cnfrm.isEmpty()){
                    Toast.makeText(RegisterActivityManager.this, "Please Enter Passwords", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pwd.equals(pwd_cnfrm)){
                    Toast.makeText(RegisterActivityManager.this, "Please confirm Passwords", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.length()<8){
                    Toast.makeText(RegisterActivityManager.this, "Password must be of 8 chars", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (phoneNumber.isEmpty() ) {
                    editTextPhone.setError("Valid number is required");
                    editTextPhone.requestFocus();
                    return;
                }


                if (cname.isEmpty() ) {
                    cname_box.setError("Valid Company Name is required");
                    cname_box.requestFocus();
                    return;
                }


                if (cregi.isEmpty() ) {
                    cregis_box.setError("Valid Registration Number is required");
                    cregis_box.requestFocus();
                    return;
                }




                if (gender.equals("Gender")){
                    Toast.makeText(RegisterActivityManager.this, "Please Select your gender", Toast.LENGTH_SHORT).show();
                    return;
                }


                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Managers");

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phoneNumber)){
                                Toast.makeText(RegisterActivityManager.this, "Manager Already Exists", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            ManagerModule model = new ManagerModule(name,phoneNumber,pwd,gender,cname,cregi);
                            databaseReference.child(phoneNumber).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent intent = new Intent(RegisterActivityManager.this, SigninActivityManager.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

//                                    LoginUserToApp(RegisterActivity.this,name,phoneNumber,email);
                                }
                            });

//                        Intent intent = new Intent(MainActivity.this, VerifyPhoneActivity.class);
//                        intent.putExtra("phoneNumber", number);
//                        intent.putExtra("name",name);
//                        intent.putExtra("pwd",pwd);
//                        startActivity(intent);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });








            }
        });


    }
}