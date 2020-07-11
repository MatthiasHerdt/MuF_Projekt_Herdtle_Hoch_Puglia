package com.example.muf_projekt_herdtle_hoch_puglia.Database;

import android.app.Application;

import androidx.room.Room;

public class ProjectApplication extends Application {
    private ProjectDatabase projectDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        projectDatabase = Room
                .databaseBuilder(this, ProjectDatabase.class, "dbMUF")
                .build();
    }

    public ProjectDatabase getDatabase() {
        return projectDatabase;
    }

}
