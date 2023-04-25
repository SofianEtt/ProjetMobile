package com.example.myprojetnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeGest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_gest);

        // Récupérer le bouton de gestion des inscriptions
        Button GestionInscriptions = findViewById(R.id.GestionInscriptions);
        // Ajouter un écouteur d'événements pour détecter les clics sur le bouton de gestion des inscriptions
        GestionInscriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour démarrer la nouvelle activité
                Intent intent = new Intent(HomeGest.this, GestionInscriptionsActivity.class);
                startActivity(intent);
            }
        });

        // Récupérer le bouton de statistiques
        Button Statistiques = findViewById(R.id.Statistiques);
        // Ajouter un écouteur d'événements pour détecter les clics sur le bouton de statistiques
        Statistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour démarrer la nouvelle activité
                Intent intent = new Intent(HomeGest.this, Statistiques.class);
                startActivity(intent);
            }
        });
    }
}