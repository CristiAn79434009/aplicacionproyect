package com.example.ariel.loapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends AppCompatActivity {

    Button crear_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        crear_user= (Button) findViewById(R.id.crearuser);
        crear_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opci=new Intent(getApplicationContext(), FormularioActivity.class);
                startActivity(opci);
            }
        });
    }

}