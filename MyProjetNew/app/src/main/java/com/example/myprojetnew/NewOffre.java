package com.example.myprojetnew;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewOffre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = null;
        // Create the LinearLayout
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20, 20, 20, 20);
        layout.setBackgroundColor(Color.LTGRAY);

        // Create the name TextView and EditText
        TextView offreTextView = new TextView(this);
        offreTextView.setText("Entrez le nom de l'offre");
        EditText offreEditText = new EditText(this);

        // Create the surname TextView and EditText
        TextView descriptionTextView = new TextView(this);
        descriptionTextView.setText("Décrivez l'offre:");
        EditText descriptionEditText = new EditText(this);

        layout.addView(offreTextView);
        layout.addView(offreEditText);
        layout.addView(descriptionTextView);
        layout.addView(descriptionEditText);

        Button valide=new Button(this);
        valide.setText("OK");
        layout.addView(valide);
        setContentView(layout);

    }
}