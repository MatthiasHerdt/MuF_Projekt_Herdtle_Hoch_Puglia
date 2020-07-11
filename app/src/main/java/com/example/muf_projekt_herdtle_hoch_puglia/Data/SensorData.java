package com.example.muf_projekt_herdtle_hoch_puglia.Data;


import android.hardware.Sensor;

import androidx.room.Entity;

public class SensorData {

    private Sensor sensor;
    private float p1;
    private float p2;
    private float p3;
    //private float p4;


    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public float getP1() {
        return p1;
    }

    public void setP1(float p1) {
        this.p1 = p1;
    }

    public float getP2() {
        return p2;
    }

    public void setP2(float p2) {
        this.p2 = p2;
    }

    public float getP3() {
        return p3;
    }

    public void setP3(float p3) {
        this.p3 = p3;
    }

/*    public float getP4() {
        return p4;
    }

    public void setP4(float p4) {
        this.p4 = p4;
    }*/

    public void setAllP(float p1, float p2, float p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        //this.p4 = p4;
    }


}

