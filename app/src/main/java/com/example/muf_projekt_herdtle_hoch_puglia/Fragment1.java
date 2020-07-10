package com.example.muf_projekt_herdtle_hoch_puglia;

import android.os.Bundle;
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

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    private MainViewModel mainViewModel;
    private Observer<SensorData> observer;
    private ArrayList<Memory> datalist;
    private int count;

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

        mainViewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MainViewModel.class);

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(observer == null){
                    observer = (sensorData)->{
                        vendor.setText("Vendor" + sensorData.getSensor().getVendor());
                        name.setText("Name" + sensorData.getSensor().getName());
                        version.setText("Version" + sensorData.getSensor().getVersion());
                        xyz.setText(
                                "x:" + sensorData.getP1() + "y:" +sensorData.getP2() + "z:" + sensorData.getP3());
                        datalist.add(new Memory(count, sensorData.getP1(),sensorData.getP2(),sensorData.getP3(),System.currentTimeMillis()));
                        count = count + 1;

                    };

                    mainViewModel.sensorData.observe(getViewLifecycleOwner(),observer);
                }
            }
        });

        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                xyz.setText(
                        "x: 0" + " y:0" + " z:0" );
                mainViewModel.sensorData.removeObserver(observer);
                //datalist.clear();
                //count = 0;

                observer = null;
            }
        });

    }


}
