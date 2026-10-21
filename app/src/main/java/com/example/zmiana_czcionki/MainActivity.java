package com.example.zmiana_czcionki;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SeekBar suwak;
    TextView obecnyRozmiar;
    TextView cytat;
    Button button;
    Button button2;
    int indeks = 0;
    private SharedPreferences preferences;
    int wartosc,obecneWartosci;
    final String[] tablica = {"Dzień dobry","Good morning","Buenos dias"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        suwak = findViewById(R.id.suwak);
        obecnyRozmiar = findViewById(R.id.rozmiar);
        cytat = findViewById(R.id.napis);
        button = findViewById(R.id.przycisk);
        button2 = findViewById(R.id.przycisk2);

        preferences = getSharedPreferences("UstawieniaAplikacji",Context.MODE_PRIVATE);
        obecneWartosci = preferences.getInt("rozmiar",0);
        cytat.setTextSize(obecneWartosci);
        obecnyRozmiar.setText("Rozmiar: "+ obecneWartosci);
        suwak.setProgress(obecneWartosci);


        suwak.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wartosc = suwak.getProgress();
                obecnyRozmiar.setText("Rozmiar: "+wartosc);
                cytat.setTextSize(wartosc);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button.setOnClickListener(v ->{
            indeks++;
            if (indeks == tablica.length){
                indeks = 0;
                cytat.setText(tablica[indeks]);
            } else {
                cytat.setText(tablica[indeks]);
            }
        });
        button2.setOnClickListener(v ->{
            SharedPreferences.Editor edytor = preferences.edit();
            edytor.putInt("rozmiar",wartosc);
            edytor.apply();
            Toast.makeText(this,"Zapisano ustawienia",Toast.LENGTH_SHORT).show();
        });
    }
}