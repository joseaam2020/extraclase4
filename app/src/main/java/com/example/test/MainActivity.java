package com.example.test;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListaXOR listaXOR = new ListaXOR();

        final TextView listaView = findViewById(R.id.ListaXORView);
        final EditText inputLista = findViewById(R.id.enterLista);
        Button ingresar = findViewById(R.id.ingresar);
        Button eliminar = findViewById(R.id.eliminar);
        listaView.setMovementMethod(new ScrollingMovementMethod());

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputLista.getText().toString();
                if(!input.isEmpty()){
                    int dato = Integer.parseInt(input);
                    listaXOR.insertar(dato);
                    listaView.setText(listaXOR.print());
                }
                inputLista.setText("");
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputLista.getText().toString();
                System.out.println(input.isEmpty());
                if(!input.isEmpty()){
                    int dato = Integer.parseInt(input);
                    listaXOR.eliminar(dato);
                    listaView.setText(listaXOR.print());
                }
                inputLista.setText("");
            }
        });
    }
}