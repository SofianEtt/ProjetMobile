package com.example.myprojetnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Button candidat = (Button) findViewById(R.id.candidat);
        Button employeur = (Button)findViewById(R.id.employeur);
        Button agence = (Button) findViewById(R.id.agence);
        candidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCandidat = new Intent(Inscription.this, InscriptionCandidat.class);
                startActivity(intentCandidat);
            }
        });
        employeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEmployeur = new Intent(Inscription.this, InscriptionEmpAg.class);
                startActivity(intentEmployeur);
            }
        });
        agence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAgence = new Intent(Inscription.this, InscriptionEmpAg.class);
                startActivity(intentAgence);
            }
        });
    }
}