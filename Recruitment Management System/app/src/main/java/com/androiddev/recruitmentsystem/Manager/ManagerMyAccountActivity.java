package com.androiddev.recruitmentsystem.Manager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.androiddev.recruitmentsystem.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ManagerMyAccountActivity extends AppCompatActivity {

    TextInputEditText editTextPhone, email_box;
    FrameLayout edit_btn, okay_btn;
    TextInputEditText name_box;
    Spinner gender_sp;
    String gender;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        editTextPhone = findViewById(R.id.phone_box);
        edit_btn = findViewById(R.id.edit_btn);
        okay_btn = findViewById(R.id.okay_btn);
        name_box = findViewById(R.id.name_box);
        email_box = findViewById(R.id.email_box);
        gender_sp = findViewById(R.id.gender_sp);




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

        SharedPreferences preferences = getSharedPreferences("USER",MODE_PRIVATE);
        String phone = preferences.getString("phone","");

        // code to get User data from Firebase realtime database and show
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Managers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name_box.setText(dataSnapshot.child(phone).child("name").getValue(String.class));
                editTextPhone.setText(dataSnapshot.child(phone).child("phone").getValue(String.class));
                email_box.setText(dataSnapshot.child(phone).child("location").getValue(String.class));
                if ((dataSnapshot.child(phone).child("gender").getValue(String.class)).equals("FeMale")){
                    gender_sp.setSelection(2);
                }else if ((dataSnapshot.child(phone).child("gender").getValue(String.class)).equals("Male")){
                    gender_sp.setSelection(1);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_box.setEnabled(true);
                email_box.setEnabled(true);
                edit_btn.setVisibility(View.GONE);
                okay_btn.setVisibility(View.VISIBLE);
            }
        });

        okay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_box.getText().toString();
                String email = email_box.getText().toString();
                if (name.isEmpty()){
                    name_box.setError("Name is required");
                    name_box.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    email_box.setError("Email is required");
                    email_box.requestFocus();
                    return;
                }
                if (gender.equals("Gender")){
                    Toast.makeText(ManagerMyAccountActivity.this, "Please Select your gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                // This code is updating the existing data in Firebase realtime datbase
//                UserModule module=new UserModule(name,email,gender);
//                databaseReference.child("Managers").child(phone).setValue(module).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        finish();
//                    }
//                });
                finish();

            }
        });
    }
}