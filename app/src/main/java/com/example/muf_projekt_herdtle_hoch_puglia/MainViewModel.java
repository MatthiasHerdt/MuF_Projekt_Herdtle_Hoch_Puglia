package com.example.muf_projekt_herdtle_hoch_puglia;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.muf_projekt_herdtle_hoch_puglia.Data.SensorData;

public class MainViewModel extends AndroidViewModel {

    public LiveData<SensorData> sensorData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        sensorData = new SensorLiveData(application.getApplicationContext());
    }

    private final static class SensorLiveData extends LiveData <SensorData>{
        private final SensorData sensordata = new SensorData();
        private SensorManager sm;
        private Sensor accelerometer;
        private Sensor gravitySensor;
        private float[] gravity;
        private SensorEventListener listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                switch(event.sensor.getType()){
                    case Sensor.TYPE_ACCELEROMETER:
                        float [] values = removeGravity(gravity,event.values);
                        sensordata.setAllP(values[0],values[1],values[2]);
                        sensordata.setSensor(event.sensor);
                        setValue(sensordata);
                        break;
                    case Sensor.TYPE_GRAVITY:
                        gravity = event.values;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        SensorLiveData(Context context) {
            sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            if (sm != null) {
                gravitySensor = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
                accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            } else {
                //should never happen
                throw new RuntimeException("He's down maaan!");
            }
        }
        @Override
        protected void onActive() {
            super.onActive();
            sm.registerListener(listener,gravitySensor,SensorManager.SENSOR_DELAY_NORMAL);
            sm.registerListener(listener,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        protected void onInactive() {
            super.onInactive();
            sm.unregisterListener(listener);
        }

        private float[] removeGravity(float[] gravity, float[] values){
            if(gravity == null){
                return values;
            }
            final float alpha = 0.8f;
            float g[]= new float[3];
            g[0] = alpha * gravity[0] + (1-alpha) * values[0];
            g[1] = alpha * gravity[1] + (1-alpha) * values[1];
            g[2] = alpha * gravity[2] + (1-alpha) * values[2];

            return new float[]{
                    values[0] - g[0],
                    values[1] - g[1],
                    values[2] - g[2]
            };
        }
    }



}
