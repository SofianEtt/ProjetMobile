package com.androiddev.recruitmentsystem.Recruiter;

import static com.androiddev.recruitmentsystem.User.SigninActivity.LoginUserToApp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
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

import com.androiddev.recruitmentsystem.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class SigninActivityRecruiter extends AppCompatActivity {
    TextInputEditText phone_box, pwd_box;
    FrameLayout login_btn;
    TextView newAccount_tv;
    TextView for_tv;
    Spinner language_sp;
    String language = "English";

    DatabaseReference databaseReference;

    TextView asa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        phone_box = findViewById(R.id.phone_box);
        pwd_box = findViewById(R.id.pwd_box);
        login_btn = findViewById(R.id.login_btn);
        newAccount_tv = findViewById(R.id.sighuphere);
        for_tv= findViewById(R.id.for_tv);
        language_sp = findViewById(R.id.language_sp);
        for_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninActivityRecruiter.this, ForgotPasswordRecruiter.class));
            }
        });

        asa = findViewById(R.id.asa);
        asa.setText("AS Recruiter");


        databaseReference = FirebaseDatabase.getInstance().getReference();

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
                    setlanguage(SigninActivityRecruiter.this,"en");
                    startActivity(new Intent(SigninActivityRecruiter.this, SigninActivityRecruiter.class));
                    finish();

                }else if (language.equals("French")){
                    setlanguage(SigninActivityRecruiter.this,"po");
                    startActivity(new Intent(SigninActivityRecruiter.this, SigninActivityRecruiter.class));
                    finish();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        newAccount_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigninActivityRecruiter.this, RegisterActivityRecruiter.class));
                finish();
            }
        });

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
                    Toast.makeText(SigninActivityRecruiter.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.length() < 8){
                    Toast.makeText(SigninActivityRecruiter.this, "Password should have atleast 8 chars", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseReference.child("Recruiters").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(phone)){
                            if (pwd.equals(snapshot.child(phone).child("password").getValue(String.class))){

                                Toast.makeText(SigninActivityRecruiter.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                LoginUserToApp(SigninActivityRecruiter.this,snapshot.child(phone).child("name").getValue(String.class),phone,snapshot.child(phone).child("email").getValue(String.class),"recruiter");
                                startActivity(new Intent(SigninActivityRecruiter.this,RecruiterHomeActivity.class));
                                finish();
                            }else {
                                Toast.makeText(SigninActivityRecruiter.this, "Check your Password", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(SigninActivityRecruiter.this, "No user Registered with this Phone", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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