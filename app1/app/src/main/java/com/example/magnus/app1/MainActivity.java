package com.example.magnus.app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.magnus.app1.agente.LoginActivity;

public class MainActivity extends AppCompatActivity {

    Button cargar_agente;

    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //para cargar boton desde aqui
    cargar_agente= (Button) findViewById(R.id.boton_carga_agente);

    cargar_agente.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            //hasta aqui
        }
    });
}
}
