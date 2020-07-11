package com.example.muf_projekt_herdtle_hoch_puglia.Data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "daten")
public class Memory {
    @PrimaryKey

    private int line;

    private String dataname;
    private float x;
    private float y;
    private float z;
    private long time;

    public Memory(String dataname, int line, float x, float y, float z, long time) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.time = time;
        this.line = line;
        this.dataname = dataname;
    }

    public String getDataname() { return dataname;}

    public void setDataname(String dataname) { this.dataname = dataname;}

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
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
