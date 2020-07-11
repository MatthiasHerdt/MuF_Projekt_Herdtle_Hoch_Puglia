package com.example.muf_projekt_herdtle_hoch_puglia.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.muf_projekt_herdtle_hoch_puglia.MainViewModel;
import com.example.muf_projekt_herdtle_hoch_puglia.Data.Memory;
import com.example.muf_projekt_herdtle_hoch_puglia.R;
import com.example.muf_projekt_herdtle_hoch_puglia.Data.SensorData;
import com.example.muf_projekt_herdtle_hoch_puglia.ViewModel.SensorViewModel;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Fragment1 extends Fragment {
    private SensorViewModel sensorViewModel;
    private String dataname = "Messung";

    private MainViewModel mainViewModel;
    private Observer<SensorData> observer;
    private ArrayList<Memory> datalist;
    private int count = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorViewModel =new ViewModelProvider(
                getActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())
        ).get(SensorViewModel.class);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //final NavController controller = Navigation.findNavController(view);

        final TextView vendor = view.findViewById(R.id.vendor);
        final TextView name = view.findViewById(R.id.name);
        final TextView version = view.findViewById(R.id.version);
        final TextView xyz = view.findViewById(R.id.xyz);

        final Button StartButton = view.findViewById(R.id.startButton);
        final Button StopButton = view.findViewById(R.id.stopButton);

        observer = null;
        datalist = new ArrayList<>();
        mainViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MainViewModel.class);

        view.findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(observer == null){
                    observer = (sensorData)->{
                        vendor.setText("Vendor" + sensorData.getSensor().getVendor());
                        name.setText("Name" + sensorData.getSensor().getName());
                        version.setText("Version" + sensorData.getSensor().getVersion());
                        xyz.setText("x:" + sensorData.getP1() + "y:" +sensorData.getP2() + "z:" + sensorData.getP3());
                        Memory tempmemory = new Memory(dataname, count, sensorData.getP1(),sensorData.getP2(),sensorData.getP3(),System.currentTimeMillis());
                        datalist.add(tempmemory);
                        Log.d(TAG,"on Create: Daten: "+datalist.get(count).getX());
                        count=count+1;
                        // eingabe in die Datenbank
                        sensorViewModel.setSensor(tempmemory);

                    };

                    mainViewModel.sensorData.observe(getViewLifecycleOwner(),observer);
                }
            }
        });

        view.findViewById(R.id.stopButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.sensorData.removeObserver(observer);
                observer = null;
                xyz.setText("Sie haben die Messung gestoppt." );
                count = 0;
                datalist.clear();
                }
        });


    }


}
