package com.example.muf_projekt_herdtle_hoch_puglia.Data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "daten")
public class Memory {
    @PrimaryKey

    private int idline;

    private String dataname;
    private float x;
    private float y;
    private float z;
    private long time;

    public Memory(String dataname, float x, float y, float z, long time, int idline) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.time = time;
        this.idline = idline;
        this.dataname = dataname;
    }


    public void deleteAll(){

    }
    public String getDataname() { return dataname;}

    public void setDataname(String dataname) { this.dataname = dataname;}

    public int getIdline() {
        return idline;
    }

    public void setIdline(int line) {
        this.idline = idline;
    }

    public float getX() { return x; }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


}
