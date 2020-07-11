package com.example.muf_projekt_herdtle_hoch_puglia.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.muf_projekt_herdtle_hoch_puglia.Data.SensorData;

@Dao
public abstract class dao {

    @Query("SELECT * FROM daten WHERE p1=:x")
    public abstract LiveData<SensorData> getSensorDatabyx(Float x);

    @Query("SELECT * FROM daten WHERE p2=:y")
    public abstract LiveData<SensorData> getSensorDatabyy(Float y);

    @Query("SELECT * FROM daten WHERE p3=:z")
    public abstract LiveData<SensorData> getSensorDatabyz(Float z);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(SensorData daten);
}
