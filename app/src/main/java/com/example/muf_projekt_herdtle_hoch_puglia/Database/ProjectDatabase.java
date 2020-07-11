package com.example.muf_projekt_herdtle_hoch_puglia.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.muf_projekt_herdtle_hoch_puglia.Dao.dao;
import com.example.muf_projekt_herdtle_hoch_puglia.Data.SensorData;

@Database(entities = {SensorData.class}, version = 1)
public abstract class ProjectDatabase extends RoomDatabase {

    public abstract dao getSensorDao();
}
