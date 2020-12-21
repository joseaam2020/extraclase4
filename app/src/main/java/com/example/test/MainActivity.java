package com.example.test;

import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ingresar = findViewById(R.id.ingresar);
        Button eliminar = findViewById(R.id.eliminar);
        ImageView arbol = findViewById(R.id.arbolView);

    }
}