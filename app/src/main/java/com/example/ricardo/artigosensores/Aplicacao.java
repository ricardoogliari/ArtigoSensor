package com.example.ricardo.artigosensores;

import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import java.util.List;

/**
 * Created by ricardo on 22/01/2015.
 */
public class Aplicacao extends Application {

    public SensorManager sMgr;
    public List<Sensor> sensores;

    @Override
    public void onCreate() {
        super.onCreate();

        sMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensores = sMgr.getSensorList(Sensor.TYPE_ALL);
    }
}
