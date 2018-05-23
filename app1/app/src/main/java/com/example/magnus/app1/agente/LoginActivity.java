package com.example.magnus.app1.agente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.magnus.app1.MainActivity;
import com.example.magnus.app1.R;
public class LoginActivity extends AppCompatActivity {

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_agente);

        login= (Button) findViewById(R.id.boton_login_agente);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, EstadoInmuebleActivity.class));
                //hasta aqui
            }
        });
    }
}
