package com.example.myprojetnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mail,password;
    Button login,register;
    DBHelper DB = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextNumberPassword);

        login = (Button) findViewById(R.id.button);
        register = (Button) findViewById(R.id.button2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailString = mail.getText().toString();
                String pass = password.getText().toString();

                if(mailString.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Adresse mail/Password invalide", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkMail = DB.checkMail(mailString);
                    if(checkMail == false){
                        Boolean insert = DB.insertData(mailString,pass);
                        if(insert == true){
                            Toast.makeText(MainActivity.this, "Inscription réussite", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(MainActivity.this, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Adresse mail déjà utilisée", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailString = mail.getText().toString();
                String pass = password.getText().toString();
                if(DB.checkMail(mailString) == false){
                    Toast.makeText(MainActivity.this, "Adresse mail inexistante", Toast.LENGTH_SHORT).show();
                }else{
                    if(DB.checkUser(mailString,pass) == true){
                        Toast.makeText(MainActivity.this, "Connexion réussite", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Mot de passe incorecte", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}