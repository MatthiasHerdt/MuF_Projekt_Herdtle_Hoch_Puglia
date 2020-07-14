package com.example.muf_projekt_herdtle_hoch_puglia.ViewModel;

import android.app.Application;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.muf_projekt_herdtle_hoch_puglia.Data.Memory;

import java.util.concurrent.atomic.AtomicBoolean;
import static android.content.ContentValues.TAG;

public class SensorViewModel extends BaseViewModel {
    private Handler handler = new Handler(Looper.getMainLooper());
    private SensorLiveData sensorLiveData = new SensorLiveData();
    public SensorViewModel(@NonNull Application application) { super(application);}

    public LiveData<Memory> setSensor(Memory memory){
        sensorLiveData.insertSensor(memory);
        return sensorLiveData;
    }

    public LiveData<Memory> getMemory(){
        return getDatabase().getSensorDao().getDaten();
    }

    public LiveData<Memory> datenInserted(){
        return sensorLiveData;
    }

    public class SensorLiveData extends LiveData<Memory> {
        private AtomicBoolean active = new AtomicBoolean();

        public void insertSensor(Memory memory) {
            Runnable r = () -> {
                getDatabase().getSensorDao().insert(memory);
                if(active.get()) {
                    handler.post(() -> {
                        setValue(memory);

                    });
                }
            };
            Thread t = new Thread(r);
            t.start();
        }

        @Override
        protected void onActive() {
            active.set(true);
        }

        @Override
        protected void onInactive() {
            active.set(false);
        }
    }


}
