package com.example.jose.myaplicacion.verPublicidad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.jose.myaplicacion.ListDataSourse.CustomAdapter;
import com.example.jose.myaplicacion.ListDataSourse.ItemList;
import com.example.jose.myaplicacion.R;

import java.util.ArrayList;

public class PublicidadActivity extends AppCompatActivity {

    private ListView LIST;
    private ArrayList<ItemList> LISINFO;
    private CustomAdapter ADAPTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LISINFO = new ArrayList<ItemList>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicidad);

        loadComponets();
    }

    private void loadComponets() {
        LIST = (ListView) this.findViewById(R.id.listviewlaoyut);
        //probar aplicacion
       // LISINFO.add(new ItemList("alquiler","12222 bs","@drawable/fondo"));


        ADAPTER = new CustomAdapter(this, LISINFO);

        LIST.setAdapter(ADAPTER);

    }

}
