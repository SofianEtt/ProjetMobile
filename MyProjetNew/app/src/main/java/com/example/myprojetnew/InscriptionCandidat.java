package com.example.myprojetnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InscriptionCandidat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_candidat);
        Button register = (Button)findViewById(R.id.registerC);
        EditText confirmPW,pW, firstName, mail, name, phone, city;
        confirmPW = (EditText)findViewById(R.id.editConfirmPWC);
        pW = (EditText)findViewById(R.id.editPWC);
        firstName = (EditText)findViewById(R.id.editFNameC);
        mail = (EditText)findViewById(R.id.editMailC);
        name = (EditText)findViewById(R.id.editNameC);
        phone = (EditText)findViewById(R.id.editPhoneC);
        city = (EditText)findViewById(R.id.editCityC);






        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pW.getText().toString().equals("")){
                    Toast.makeText(InscriptionCandidat.this, "Mot de passe vide", Toast.LENGTH_SHORT).show();
                }else if(!confirmPW.getText().toString().equals(pW.getText().toString())){
                    Toast.makeText(InscriptionCandidat.this, "Mot de passe non correspondant", Toast.LENGTH_SHORT).show();
                } else if(firstName.getText().toString().equals("")){
                    Toast.makeText(InscriptionCandidat.this, "Prenom vide", Toast.LENGTH_SHORT).show();
                }else if(mail.getText().toString().equals("")){
                    Toast.makeText(InscriptionCandidat.this, "Mail vide", Toast.LENGTH_SHORT).show();
                }else if(name.getText().toString().equals("")){
                    Toast.makeText(InscriptionCandidat.this, "Nom vide", Toast.LENGTH_SHORT).show();
                }else if(phone.getText().toString().equals("")){
                    Toast.makeText(InscriptionCandidat.this, "Telephone vide", Toast.LENGTH_SHORT).show();
                }else if(city.getText().toString().equals("")){
                    Toast.makeText(InscriptionCandidat.this, "Ville vide", Toast.LENGTH_SHORT).show();
                }else{
                    Intent goHome = new Intent(InscriptionCandidat.this,HomeCandidat.class);
                    startActivity(goHome);
                }
            }
        });
    }
}