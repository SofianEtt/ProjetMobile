package com.example.myprojetnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InscriptionEmpAg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_emp_ag);
        Button register = (Button) findViewById(R.id.registerEA);
        EditText number, city,pw,confirmPw,adresse,mail,name;
        number = (EditText)findViewById(R.id.editNumberEA);
        city = (EditText)findViewById(R.id.editCityEA);
        pw = (EditText)findViewById(R.id.editPWEA);
        confirmPw = (EditText)findViewById(R.id.editConfirmPW);
        adresse = (EditText)findViewById(R.id.editAdresseEA);
        mail = (EditText)findViewById(R.id.editMailEA);
        name = (EditText)findViewById(R.id.editNameEA);





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pw.getText().toString().equals("")){
                    Toast.makeText(InscriptionEmpAg.this, "Mot de passe vide", Toast.LENGTH_SHORT).show();
                }else if(!confirmPw.getText().toString().equals(pw.getText().toString())){
                    Toast.makeText(InscriptionEmpAg.this, "Mot de passe non correspondant", Toast.LENGTH_SHORT).show();
                } else if(number.getText().toString().equals("")){
                    Toast.makeText(InscriptionEmpAg.this, "Prenom vide", Toast.LENGTH_SHORT).show();
                }else if(mail.getText().toString().equals("")){
                    Toast.makeText(InscriptionEmpAg.this, "Mail vide", Toast.LENGTH_SHORT).show();
                }else if(name.getText().toString().equals("")){
                    Toast.makeText(InscriptionEmpAg.this, "Nom vide", Toast.LENGTH_SHORT).show();
                }else if(adresse.getText().toString().equals("")){
                    Toast.makeText(InscriptionEmpAg.this, "Telephone vide", Toast.LENGTH_SHORT).show();
                }else if(city.getText().toString().equals("")){
                    Toast.makeText(InscriptionEmpAg.this, "Ville vide", Toast.LENGTH_SHORT).show();
                }else {
                    Intent goHome = new Intent(InscriptionEmpAg.this, HomeEA.class);
                    startActivity(goHome);
                }
            }
        });
    }
}