package com.example.ariel.camera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceActivity;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ariel.camera.utils.ParamsConnection;
import com.example.ariel.camera.utils.UserData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView IMG_CONTAINER;
    private final String CARPERTA_RAIZ = "misImagenesPrueba/";
    private final String RUTA_IMAGEN = CARPERTA_RAIZ + "misFotos";
    private int MEDIA_CODE = 123;
    private int CAMERA_CODE = 124;
    private String ABSOULTE_PATH;
    private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        validaPermiso();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        loadCompones();
    }

    private boolean validaPermiso() {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }

        if((checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED)&&
                (checkSelfPermission(WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);


        return false;

    }

    //permisos para la camara

    private void loadCompones() {
        Button media = (Button) this.findViewById(R.id.media);
        Button photo = (Button) this.findViewById(R.id.photo);
        Button send = (Button) this.findViewById(R.id.sendbutton);

        IMG_CONTAINER = (ImageView) this.findViewById(R.id.photoView);
        media.setOnClickListener(this);
        photo.setOnClickListener(this);
        send.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.media) {
            LoadGallery();
        }
        if (v.getId() == R.id.photo) {
            LoadCamera();
        }
        if (v.getId() == R.id.sendbutton) {
            try {
                sendPhoto();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private void LoadCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        File file = createFile();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri fileuri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
            camera.putExtra(MediaStore.EXTRA_OUTPUT, fileuri);
        } else {
            camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        this.startActivityForResult(camera, CAMERA_CODE);

    }

    private File createFile() {
        File file = new File(Environment.getExternalStorageDirectory(), "misimagnes"); //creacion de directorio de la camera
        if (!file.exists()) {
            file.mkdir();
        }
        String name = "";
        if (file.exists()) {
            name = "IMG_" + System.currentTimeMillis() / 1000 + "_.jpg";        //generando nombre para la foto
        }
        imageFilePath = file.getAbsolutePath() + File.separator + name;
        File fileimg = new File(imageFilePath);
        return fileimg;

    }


    private void LoadGallery() {
        Intent media = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        media.setType("image/");
        startActivityForResult(media.createChooser(media, "Escoja la Aplicacion"), MEDIA_CODE);
    }
    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    private String imageFilePath;

    private void sendPhoto() throws FileNotFoundException {
        if (IMG_CONTAINER.getDrawable() != null) {
            if (imageFilePath != null) {
                File file = new File(imageFilePath);
                RequestParams params = new RequestParams();
                params.put("img", file);
                AsyncHttpClient client = new AsyncHttpClient();
                if (UserData.ID != null) {
                    client.post(ParamsConnection.REST_USERIMG_POST + "/" + UserData.ID, params, new JsonHttpResponseHandler() {

                        public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
                            try {

                                String path = response.getString("path");
                                if (path != null) {
                                    Intent profile = new Intent(root, ContactsContract.Profile.class);
                                    root.startActivity(profile);
                                }
                            } catch (JSONException json) {
                                Log.i("ERROR", json.getMessage());
                            }

                        }

                    });

                }
            }
        }else {
            Toast.makeText(this, "No se ha sacado una foto", Toast.LENGTH_LONG).show();

        }


        //para la galeria
        //ESTO PAR ALA CAMERA


//para ver desde la galeria

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MEDIA_CODE) {
            IMG_CONTAINER.setImageURI(data.getData());
        }
        if(requestCode == CAMERA_CODE) {
            loadImageCamera();
        }
    }

    private void loadImageCamera() {
        Bitmap img = BitmapFactory.decodeFile(imageFilePath);
        if(img !=null){
            IMG_CONTAINER.setImageBitmap(img);
        }
    }
}