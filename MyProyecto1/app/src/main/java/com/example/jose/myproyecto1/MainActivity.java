package com.example.jose.myproyecto1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button casa, departamento, condominio, publicidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casa = (Button) findViewById(R.id.registro_casa);
        condominio = (Button) findViewById(R.id.registro_departamneto);
        departamento = (Button) findViewById(R.id.registro_condominio);
        publicidad = (Button) findViewById(R.id.publicados);
    }

    public void CasaAlquiler(View view) {
        switch (view.getId()) {
            case R.id.registro_casa:
                Intent miIntent = new Intent(MainActivity.this, RegistrarVentaActivity.class);
                startActivity(miIntent);
                break;
            case R.id.registro_departamneto:
                Intent dosIntent = new Intent(MainActivity.this, RegistrarVentaActivity.class);
                startActivity(dosIntent);
                break;
            case R.id.registro_condominio:
                Intent tresIntent = new Intent(MainActivity.this, RegistrarVentaActivity.class);
                startActivity(tresIntent);
                break;
        }
    }


    public void irAPublicidad(View view) {
        switch (view.getId()){
            case R.id.publicados:
                Intent misIntent = new Intent(MainActivity.this, Listar.class);
                startActivity(misIntent);
                break;
        }

    }
}
