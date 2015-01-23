package com.example.ricardo.artigosensores;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {

    /*
    luz - muda a cor de fundo da tela
    proximidade - aumenta e abaixa o volume
    acelerometro - torca a música
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> nomes = new ArrayList<String>();
        for (Sensor sensor : ((Aplicacao)getApplication()).sensores){
            nomes.add(sensor.getName() + " - " + sensor.getVendor());
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, DetalheSensor.class);
        intent.putExtra("tipo", ((Aplicacao)getApplication()).sensores.get(position).getType());
        intent.putExtra("fabricante", ((Aplicacao)getApplication()).sensores.get(position).getVendor());
        startActivity(intent);
    }
}
