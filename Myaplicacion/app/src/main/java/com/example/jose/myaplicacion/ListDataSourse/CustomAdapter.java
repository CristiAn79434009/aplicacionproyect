package com.example.jose.myaplicacion.ListDataSourse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jose.myaplicacion.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CustomAdapter  extends BaseAdapter{
    private Context CONTEXT;
    private ArrayList <ItemList> LIST;
    public CustomAdapter(Context context, ArrayList<ItemList>list){
        this.CONTEXT =context;
        this.LIST =list;

    }
    @Override
    public int getCount() {
        return this.LIST.size();
    }

    @Override
    public Object getItem(int position) {
        return this.LIST.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflate = (LayoutInflater) this.CONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.item_layout,null);
        }
        TextView type = (TextView)convertView.findViewById(R.id.tipoId);
        TextView precio = (TextView)convertView.findViewById(R.id.precioId);
        type.setText(this.LIST.get(position).getTipo());
        precio.setText(this.LIST.get(position).getPrecio());

        ImageView img = convertView.findViewById(R.id.posterLayout);
        //cargar imagen
        try {
            URL url = new URL(this.LIST.get(position).getPoster());
            InputStream stream = url.openConnection().getInputStream();
            Bitmap imageBitmap = BitmapFactory.decodeStream(stream);
            img.setImageBitmap(imageBitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
