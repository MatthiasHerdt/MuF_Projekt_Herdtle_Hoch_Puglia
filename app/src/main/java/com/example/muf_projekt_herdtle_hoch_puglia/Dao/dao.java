package com.example.muf_projekt_herdtle_hoch_puglia.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.muf_projekt_herdtle_hoch_puglia.Data.Memory;
import com.example.muf_projekt_herdtle_hoch_puglia.Database.ProjectDatabase;

import java.util.List;

@Dao
public abstract class dao {

    @Query("SELECT * FROM daten")
    public abstract LiveData<Memory> getDaten();

    @Query("SELECT * FROM daten")
    public abstract LiveData<List<Memory>> getAllMemory();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(Memory memory);

    @Delete
    public abstract void deleteDaten(Memory memory);

}
