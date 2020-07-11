package com.example.muf_projekt_herdtle_hoch_puglia.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.muf_projekt_herdtle_hoch_puglia.Database.ProjectApplication;
import com.example.muf_projekt_herdtle_hoch_puglia.Database.ProjectDatabase;

public abstract class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull Application application) { super(application); }
    public ProjectDatabase getDatabase() {
        return ((ProjectApplication) getApplication()).getDatabase();
    }

}
