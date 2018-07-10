package com.example.ariel.loapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ariel.loapp.utils.ParamsConnection;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class detallesCasa extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    ArrayList<LatLng> lat=new ArrayList<>();
    ArrayList<LatLng> llenar=new ArrayList<>();
    int latC[]={57,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,59,58,57,57,56,55,57};
    int lonC[]={75,76,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,74,75,76,76,76,76};
    String nom[]={"simeon roncal","Escuela Jose david berrios","Colegio Santa Ana","colegio mixto litoral","Colegio medinaceli","Sedes Potosi","Unidad Educativa Cristo Maestro","Colegio particular Santa Maria","escuela Simon Rodriguez","Colegio Santa Maria","Liceo de Señoritas Potosi","colegio Don Bosco","Colegio Calero","Colegio Jose Alonzo de Ibañes","Escuela Luis Subieta Sagarnaga","Colegio Tomas Frias","Colegio Liceo Sucre","Papa Pichincha","Liceo de Señoritas Santa Rosa","Unidad educativa Macedonio Nogales","Instituto Tecnico Boliviano Suizo","Escuela Juana Azurduy de Padilla","Unidad Educativa Copacabana","Unidad Educativa Mejillones","Normal Eduardo Avaroa","Facultad De Economia","Universidad Privada Domingo Savio","Ciudadela Universitaria","Kinder Niño Jesus"};
    int latCoin;
    int lonCoin;
    Double latitud;
    Double longitud;
    String c;
    String email;
    TextView ciudad;
    TextView descripcionn;
    TextView tip;
    ImageView gallerre;
    TextView hab;

    TextView ban;
    TextView superficie;

    TextView est;
    TextView pre;
    //consultas
    ArrayList<ArrayList<String>> resu=new ArrayList<>();
    ListView lista;
    ArrayList url=new ArrayList();
    ArrayList name=new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_casa);
        //rescatamos datos de la casa
        Bundle rescatar=getIntent().getExtras();
        String a=rescatar.getString("latitud");
        String b=rescatar.getString("longitud");
        c=rescatar.getString("id");
        latCoin =Integer.parseInt(a.substring(4,6));
        lonCoin =Integer.parseInt(b.substring(4,6));
        latitud=Double.parseDouble(a);
        longitud=Double.parseDouble(b);
        inicializarLat();
        llenar();
        //inicio de consumo de servicio

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(ParamsConnection.REST_HOME_POST, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @SuppressLint("WrongViewCast")
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                JSONArray aux = timeline;
                try {
                    JSONArray jsonArray = timeline;
                    //https://es.androids.help/q28175
                    String tipo="";
                    String esta="";
                    String prec="";
                    String ciud="";
                    String descrip="";
                    String cantC="";
                    String cantB="";
                    String supr="";
                    String galle="";
                    ArrayList<String> datos = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject res= (JSONObject) jsonArray.get(i);
                        if(c.equals(res.get("_id"))){
                            tipo=res.get("tipo")+"";
                            esta= (String) res.get("estado"+"");
                            prec=res.get("precio")+"";
                            ciud=res.get("ciudad")+"";
                            galle=res.get("gallery")+"";
                            descrip=res.get("descripcion")+"";
                            cantC=res.get("cantCuartos")+"";
                            cantB=res.get("cantBaños")+"";
                            supr=res.get("superficie")+"";


                            // Toast.makeText(getApplicationContext(),prec+"",Toast.LENGTH_LONG).show();

                        }
                    }
                    tip=(TextView) findViewById(R.id.tipo);
                    tip.setText(tipo);
                    est=(TextView)findViewById(R.id.estado);
                    est.setText(esta);
                    pre=(TextView)findViewById(R.id.precio);
                    pre.setText( prec);
                    ImageView gallerre=(ImageView)findViewById(R.id.imageGallery);

                  //  gallerre.setImageBitmap(galle);
                    descripcionn =(TextView)findViewById(R.id.detalles);
                    descripcionn.setText( descrip);
                    hab =(TextView)findViewById(R.id.roon);
                    hab.setText( cantC);
                    ban =(TextView)findViewById(R.id.banos);
                    ban.setText( cantB);
                    superficie =(TextView)findViewById(R.id.superfi);
                    superficie.setText( supr);
                } catch (Exception e) {

                };
            }
        });
        lista=(ListView)findViewById(R.id.lis);
        //load();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

       /* Button btn= (Button) findViewById(R.id.);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent in = new Intent(getApplicationContext(), programar_citasActivity.class);
                in.putExtra("ema", email);
                startActivity(in);*/
    }
    private void inicializarLat() {
        lat.add(new LatLng(-19.578711, -65.751183));
        lat.add(new LatLng(-19.580478, -65.761759));
        lat.add(new LatLng(-19.581559, -65.758314));
        lat.add(new LatLng(-19.581569, -65.754838));
        lat.add(new LatLng(-19.582074, -65.757590));
        lat.add(new LatLng(-19.582074, -65.753502));
        lat.add(new LatLng(-19.582488, -65.752408));
        lat.add(new LatLng(-19.582923, -65.758502));
        lat.add(new LatLng(-19.583186, -65.757472));
        lat.add(new LatLng(-19.584298, -65.759360));
        lat.add(new LatLng(-19.585289, -65.753330));
        lat.add(new LatLng(-19.586077, -65.753523));
        lat.add(new LatLng(-19.587267, -65.751031));
        lat.add(new LatLng(-19.587651, -65.751439));
        lat.add(new LatLng(-19.587845, -65.750026));
        lat.add(new LatLng(-19.588027, -65.752794));
        lat.add(new LatLng(-19.588330, -65.751110));
        lat.add(new LatLng(-19.588724, -65.752998));
        lat.add(new LatLng(-19.588896, -65.754629));
        lat.add(new LatLng(-19.588876, -65.755026));
        lat.add(new LatLng(-19.589432, -65.751400));
        lat.add(new LatLng(-19.589897, -65.753750));
        lat.add(new LatLng(-19.592272, -65.754640));
        lat.add(new LatLng(-19.586086, -65.743680));
        lat.add(new LatLng(-19.571570, -65.752150));
        lat.add(new LatLng(-19.570542, -65.760913));
        lat.add(new LatLng(-19.568763, -65.764437));
        lat.add(new LatLng(-19.557448, -65.763177));
        lat.add(new LatLng(-19.574769, -65.760715));
    }
    private void llenar() {
        for(int i=0;i<lonC.length;i++){
            if(latCoin==latC[i]&&lonCoin==lonC[i]){
                llenar.add(lat.get(i));
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng potosi=new LatLng(latitud,longitud);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(potosi,14));
        for(int i=0;i<llenar.size();i++){
            mMap.addMarker(new MarkerOptions().position(llenar.get(i)).title(nom[i]));
        }
    }
}
