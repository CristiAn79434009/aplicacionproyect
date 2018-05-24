package com.example.jose.myaplicacion;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jose.myaplicacion.registrado.RegistroActivity;
import com.example.jose.myaplicacion.verPublicidad.PublicidadActivity;

public class MainActivity extends AppCompatActivity {

    Button casa, departamento, condominio, publicidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casa = (Button) findViewById(R.id.registro_casa);
        condominio =(Button) findViewById(R.id.registro_departamneto);
        departamento =(Button) findViewById(R.id.registro_condominio);
        publicidad =(Button) findViewById(R.id.publicidades);


    }


    public void CasaAlquiler(View view) {
        switch (view.getId()){
            case R.id.registro_casa:
                Intent miIntent=new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(miIntent);
                break;
            case R.id.registro_departamneto:
                Intent dosIntent=new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(dosIntent);
                break;
            case R.id.registro_condominio:
                Intent tresIntent=new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(tresIntent);
                break;
        }
    }

    public void irApublicar(View view) {
        switch (view.getId()){
            case R.id.publicidades:
                Intent deIntent=new Intent(MainActivity.this,PublicidadActivity.class);
                startActivity(deIntent);
                break;
        }
    }
}
