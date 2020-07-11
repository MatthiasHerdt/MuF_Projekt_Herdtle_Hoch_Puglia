package com.example.muf_projekt_herdtle_hoch_puglia.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.muf_projekt_herdtle_hoch_puglia.Data.Memory;

@Dao
public abstract class dao {

    @Query("SELECT * FROM daten")
    public abstract LiveData<Memory> getDaten();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(Memory memory);
}
