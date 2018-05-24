package com.example.jose.myaplicacion.registrado;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.jose.myaplicacion.R;

import java.io.File;
import java.io.FileInputStream;

public class FotosActivity extends AppCompatActivity {

    ImageView imagen;

    private final String CARPETA_RAIZ="misImagenesPrueba/";
    private final String RUTA_IMAGNE=CARPETA_RAIZ+"misFotos";

    public FotosActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);

        imagen = (ImageView) findViewById(R.id.imagenId);

    }
    private static final String CARPETA_RINCIPAL = "misImagnesApp/";
    private static final String CARPETA_IMAGEN = "imagenes/";
    private static final String DIRECTORIO_IMAGEN = CARPETA_RINCIPAL;
    private String path;
    File fileImagen;
    Bitmap bitmap;

    private static final int COD_SELCCIONA = 10;
    private static final int COD_FOTO = 20;




    //PARA CARAGAR DE LA GALERIA

    public void onclick(View view) {
        cargarImagen();
    }

    private void cargarImagen() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "seleccione la Aplicacion"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case RESULT_OK:
                Uri path=data.getData();
                imagen.setImageURI(path);
                break;
        }
        switch (requestCode){
            case COD_SELCCIONA:
                Uri miPath=data.getData();
                imagen.setImageURI(miPath);
            case COD_FOTO:
                MediaScannerConnection.scanFile(getBaseContext(), new String[]{path}, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("Path",""+path);
                            }
                        });
                bitmap = BitmapFactory.decodeFile(path);
                imagen.setImageBitmap(bitmap);
            }

        //if (resultCode==RESULT_OK){
        //    Uri path=data.getData();
        //    imagen.setImageURI(path);
       // }

    }
    //HASTA AQUI CARGA LA IMAGEN DE DE GALERIA

    //ESTO PARA TOMAR FOTO

    private void abrirCamera() {
        File fileImagen = File(Environment.getExternalStoragePublicDirectory(),RUTA_IMAGNE);
        boolean isCreada=fileImagen.exists();
        String nombreImagen="";
        if(isCreada==false){
            isCreada=fileImagen.mkdir();
        }
        if(isCreada==true){
            nombreImagen=(System.currentTimeMillis()/100+".jpg");
        }
        path=Environment.getExternalStorageDirectory()+
                File.separator+RUTA_IMAGNE+File.separator+nombreImagen;

        File iamgen=new File(path);
        Intent intent=null;
        intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //
//


    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
        Intent intent1 = intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
    }
}






h