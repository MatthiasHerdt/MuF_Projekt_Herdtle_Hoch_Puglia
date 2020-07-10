package com.example.muf_projekt_herdtle_hoch_puglia;

public class Memory {

    private int line;
    private float x;
    private float y;
    private float z;
    private long time;

    public Memory(int line, float x, float y, float z, long time) {
        this.line = line;
        this.x = x;
        this.y = y;
        this.z = z;
        this.time = time;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public float getX() {
        return x;
    }

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
