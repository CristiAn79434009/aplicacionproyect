package com.example.magnus.app1.agente.inmueble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.magnus.app1.R;
import com.example.magnus.app1.agente.EstadoInmuebleActivity;

public class VentaActivity extends AppCompatActivity {

    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);
        //boton desde aqui
        registrar= (Button) findViewById(R.id.botonregistrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VentaActivity.this, RegitroInmuebleActivity.class));
                //hasta aqui
            }
        });
    }
}
