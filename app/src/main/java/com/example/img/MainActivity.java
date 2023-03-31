package com.example.img;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText _EditTaille,_EditPoids,_EditAge;
    TextView _TextTaille,_TextPoids,_TextAge,_TextInterpretation,_TextResultat;
    RadioButton _rdbFemme,_rdbHomme;
    Button _btnCalculeIMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //liaison des variables
        _TextTaille=(TextView) findViewById(R.id.TextTaille);
        _TextPoids=(TextView) findViewById(R.id.TextPoids);
        _TextAge=(TextView) findViewById(R.id.TextAge);
        _TextInterpretation=(TextView) findViewById(R.id.TextInterpretation);
        _TextResultat=(TextView) findViewById(R.id.TextResultat);
        _EditTaille=(EditText) findViewById(R.id.EditTaille);
        _EditPoids=(EditText) findViewById(R.id.EditPoids);
        _EditAge=(EditText) findViewById(R.id.EditAge);
        _rdbFemme=(RadioButton) findViewById(R.id.rdbFemme);
        _rdbHomme=(RadioButton) findViewById(R.id.rdbHomme);
        _btnCalculeIMG=(Button) findViewById(R.id.btnCalculeIMG);



        _btnCalculeIMG.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                
                // Retrieve the input values from the text fields
                double taille = Double.parseDouble(String.valueOf(_EditTaille.getText()));
                double poids = Double.parseDouble(String.valueOf(_EditPoids.getText()));
                int age = Integer.parseInt(String.valueOf(_EditAge.getText()));

                // Determine the sex of the person
                int sexe = 0;
                if (_rdbHomme.isSelected()){
                    sexe = 1;
                }

                // Calculate the IMG
                double imc = poids / (taille * taille);
                double img = 0;
                if (age <= 15) {
                    img = (1.51 * imc) + (0.70 * age) - (3.6 * sexe) + 1.4;
                } else {
                    img = (1.20 * imc) + (0.23 * age) - (10.8 * sexe) - 5.4;
                }

                // Determine the interpretation of the IMG
                String interpretation = "";
                if (_rdbFemme.isSelected()) {
                    if (img < 25) {
                        interpretation = "Trop maigre";
                    } else if (img >= 25 && img < 30) {
                        interpretation = "Pourcentage normal";
                    } else {
                        interpretation = "Trop de graisse";
                    }
                } else {
                    if (img < 15) {
                        interpretation = "Trop maigre";
                    } else if (img >= 15 && img < 20) {
                        interpretation = "Pourcentage normal";
                    } else {
                        interpretation = "Trop de graisse";
                    }
                }

                // Display the result and interpretation
                _TextResultat.setText(String.format("%.2f", img) + "%");
                _TextInterpretation.setText(interpretation);
            }
        });

        // ...
    }

    // ...
}
