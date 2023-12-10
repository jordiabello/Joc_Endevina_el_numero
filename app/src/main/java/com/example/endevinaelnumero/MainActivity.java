package com.example.endevinaelnumero;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int randomNum;
    private int numIntentos;
    private EditText inputnumber;
    private TextView intentos;
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputnumber = findViewById(R.id.inputnumber);
        intentos = findViewById(R.id.intentos);
        Button button_endevinar = findViewById(R.id.button_endevinar);
        Button rankingB = findViewById(R.id.rankingB);


        randomNum = random.nextInt(100) + 1;
        numIntentos = 0;
        button_endevinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGuess();
            }
        });
        rankingB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirRankingWindow(view);
            }
        });

    }
    public void abrirRankingWindow(View view) {
        Intent intent = new Intent(this, RecordsActivity.class);

        startActivity(intent);

    }

    private void checkGuess() {
        numIntentos ++;

        String textoIntento = inputnumber.getText().toString();

        if (!textoIntento.isEmpty()) {
            int userGuess = Integer.parseInt(textoIntento);

            if (userGuess == randomNum) {

                intentos.setText("");
                AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity.this));
                builder.setMessage("¡Has acertado!")
                        .setTitle("¡Muy bien!")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                randomNum = random.nextInt(100) + 1;
                numIntentos = 0;


            } else if (userGuess > randomNum) {
                Toast.makeText(this, "El número es más pequeño", Toast.LENGTH_SHORT).show();
                intentos.append(numIntentos + ": " + userGuess + "\n");


            } else {
                Toast.makeText(this, "El número es más grande", Toast.LENGTH_SHORT).show();
                intentos.append(numIntentos + ": " + userGuess + "\n");
            }

            inputnumber.setText("");

        } else {
            Toast.makeText(this, "Ingrese un número.", Toast.LENGTH_SHORT).show();
        }
    }
}