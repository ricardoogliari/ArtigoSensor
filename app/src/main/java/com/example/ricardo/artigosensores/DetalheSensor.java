package com.example.ricardo.artigosensores;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ricardo on 22/01/2015.
 */
/*218.63
itau
ag 8499
cc 15812-2*/
public class DetalheSensor extends Activity implements SensorEventListener{

    private Sensor sensorEmUso;

    private TextView tvValor0;
    private TextView tvValor1;
    private TextView tvValor2;

    private TextView tvMaximumRange;
    private TextView tvMinDelay;
    private TextView tvPower;
    private TextView tvName;
    private TextView tvVendor;
    private TextView tvType;
    private TextView tvVersion;
    private TextView tvResolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_sensor);

        tvValor0 = (TextView) findViewById(R.id.txt0);
        tvValor1 = (TextView) findViewById(R.id.txt1);
        tvValor2 = (TextView) findViewById(R.id.txt2);

        tvMaximumRange = (TextView) findViewById(R.id.tvMaximumRange);
        tvMinDelay = (TextView) findViewById(R.id.tvMinDelay);
        tvPower = (TextView) findViewById(R.id.tvPower);
        tvName = (TextView) findViewById(R.id.tvName);
        tvVendor = (TextView) findViewById(R.id.tvVendor);
        tvType = (TextView) findViewById(R.id.tvType);
        tvVersion = (TextView) findViewById(R.id.tvVersion);
        tvResolution = (TextView) findViewById(R.id.tvResolution);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            int tipo = bundle.getInt("tipo");
            String fabricante = bundle.getString("fabricante");

            for (Sensor sensor : ((Aplicacao)getApplication()).sensores){
                if (sensor.getType() == tipo && sensor.getVendor().equals(fabricante)){
                    sensorEmUso = sensor;
                    tvMaximumRange.setText("Maximum Range: " + sensorEmUso.getMaximumRange());
                    tvMinDelay.setText("Min Delay: " + sensorEmUso.getMinDelay());
                    tvPower.setText("Power: " + sensorEmUso.getPower());
                    tvName.setText("Name: " + sensorEmUso.getName());
                    tvResolution.setText("Resolution: " + sensorEmUso.getResolution());
                    tvVendor.setText("Vendor: " + sensorEmUso.getVendor());
                    tvType.setText("Type: " + sensorEmUso.getType());
                    tvVersion.setText("Version: " + sensorEmUso.getVersion());
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((Aplicacao)getApplication()).sMgr.registerListener(this, sensorEmUso, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ((Aplicacao)getApplication()).sMgr.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        tvValor0.setText("Valor[0]: "+event.values[0]);
        tvValor1.setText("Valor[1]: "+event.values[1]);
        tvValor2.setText("Valor[2]: "+event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
