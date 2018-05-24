package com.example.jose.myaplicacion.registrado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jose.myaplicacion.R;

public class RegistroActivity extends AppCompatActivity {

    Button Registro1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Registro1 = (Button) findViewById(R.id.boton_siguiente_registro1);
    }

    public void CotinueACamara(View view) {
        switch (view.getId()){
            case R.id.boton_siguiente_registro1:
                Intent miIntent=new Intent(RegistroActivity.this,FotosActivity.class);
                startActivity(miIntent);
                break;

        }
    }
}
