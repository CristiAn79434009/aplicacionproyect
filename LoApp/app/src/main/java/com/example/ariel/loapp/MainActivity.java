package com.example.ariel.loapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class    MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient client;
    private int GOOGLE_CODE = 12355;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions options = new  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        client = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,options)
                .build();
        loadComponts();
    }

    private void loadComponts() {
        SignInButton googlebtn = (SignInButton)this.findViewById(R.id.googlebutton);
        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(client);
                startActivityForResult(intent, GOOGLE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                //////
                Intent loginr = new Intent(this,LoginResult.class  );
                Uri photo =result.getSignInAccount().getPhotoUrl();
                String completeurl = "https://lh3.googleusercontent.com" + photo.getPath();
                loginr.putExtra("avatar", completeurl);
                loginr.putExtra("email", result.getSignInAccount().getEmail());
                loginr.putExtra("name", result.getSignInAccount().getDisplayName());
                startActivity(loginr);
                //result.getSignInAccount().

               // Toast.makeText(this, "OK", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.error_login, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
