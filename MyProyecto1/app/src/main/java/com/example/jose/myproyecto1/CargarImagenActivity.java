package com.example.jose.myproyecto1;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.print.PrinterId;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONObject;

import java.io.File;
import java.lang.ref.ReferenceQueue;

public class CargarImagenActivity extends AppCompatActivity {
    private String APP_DIRECTORY = "myPictureApp/";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME;
    private  String path;
    Bitmap bitmap;

    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;


    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_imagen);

        imageView = (ImageView) findViewById(R.id.setPicture);
        Button capturar = (Button) findViewById(R.id.buttonImage);
        capturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] opciones = {"tomar Foto", "cargar imagen", "cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(CargarImagenActivity.this);
                builder.setTitle("elige una opcion :D");
                builder.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int seleccion) {
                        if (opciones[seleccion]=="tomar foto"){
                            openCamera();
                        }else if (opciones[seleccion] == "elegir de galeria"){
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, "seleciona app imagen"), SELECT_PICTURE);
                        }else if (opciones[seleccion] == "cancelar"){
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
    }


    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdir();
        String path = Environment.getExternalStorageDirectory() + File.separator
                + MEDIA_DIRECTORY +File.separator + TEMPORAL_PICTURE_NAME;
        File newFile = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(newFile));
        startActivityForResult(intent,  PHOTO_CODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PHOTO_CODE:
                if (resultCode == RESULT_OK){
                    String dir = Environment.getExternalStorageDirectory() + File.separator
                            + MEDIA_DIRECTORY +File.separator + TEMPORAL_PICTURE_NAME;
                    decodeBitmap(dir);
                }
            break;
            case SELECT_PICTURE:
                if (requestCode == RESULT_OK){
                    Uri path = data.getData();
                    imageView.setImageURI(path);
                }
            break;
        }

    }

    private void decodeBitmap(String dir) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);

        imageView.setImageBitmap(bitmap);
    }

    public void tommarfoto(View view) {
    }
}