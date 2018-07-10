package com.example.ariel.loapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ariel.loapp.ListDataSource.OnLoadImage;
import com.example.ariel.loapp.ListDataSource.TaskImg;

public class Login extends AppCompatActivity implements OnLoadImage {

    private String avatar, email, name;
    private Context root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        avatar = this.getIntent().getExtras().getString("avatar");
        email = this.getIntent().getExtras().getString("email");
        name = this.getIntent().getExtras().getString("name") ;

        Button btn = (Button) findViewById(R.id.Login_aMenuPricipal);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cargar = new Intent (v.getContext(), Pricipal.class);
                startActivityForResult(cargar, 0);
            }
        });
        loadComponets();
    }

    private void loadComponets() {
        TextView nametxt = (TextView) this.findViewById(R.id.name);
        TextView emailtxt = (TextView) this.findViewById(R.id.email);
        ImageView img = (ImageView) this.findViewById(R.id.avatar);
        Button btn = (Button)this.findViewById(R.id.formularioUserVista);

        nametxt.setText(name);
        emailtxt.setText(email);
        TaskImg loadimg = new TaskImg();
        loadimg.execute(avatar);
        loadimg.setLoadImage(img, this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent man = new Intent(root, FormularioActivity.class);
                root.startActivity(man);
            }
        });
    }

    @Override
    public void setLoadImage(ImageView container, Bitmap img) {
        container.setImageBitmap(img);
    }

}
