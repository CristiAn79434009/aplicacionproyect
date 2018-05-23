package com.example.magnus.app1.agente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.magnus.app1.MainActivity;
import com.example.magnus.app1.R;
import com.example.magnus.app1.agente.inmueble.VentaActivity;

public class EstadoInmuebleActivity extends AppCompatActivity {


    Button venta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_inmueble);

        venta= (Button) findViewById(R.id.botonventa);

        venta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EstadoInmuebleActivity.this, VentaActivity.class));
                //hasta aqui
            }
        });
    }
}
