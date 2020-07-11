package com.example.muf_projekt_herdtle_hoch_puglia.Fragments;

import android.graphics.Color;
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
import androidx.navigation.Navigation;

import com.example.muf_projekt_herdtle_hoch_puglia.MainViewModel;
import com.example.muf_projekt_herdtle_hoch_puglia.Data.Memory;
import com.example.muf_projekt_herdtle_hoch_puglia.R;
import com.example.muf_projekt_herdtle_hoch_puglia.Data.SensorData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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
        sensorViewModel = new ViewModelProvider(
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
        final Button FeedbackButton = view.findViewById(R.id.feedbackButton);

        observer = null;
        datalist = new ArrayList<>();
        mainViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MainViewModel.class);

        LineChart lineChart = view.findViewById(R.id.LiveDataChart);
        Description desc_x = new Description();
        desc_x.setText("");
        lineChart.setDescription(desc_x);
        lineChart.setDrawGridBackground(false);

        ArrayList<Entry> values1 = new ArrayList<Entry>();
        ArrayList<Entry> values2 = new ArrayList<Entry>();
        ArrayList<Entry> values3 = new ArrayList<Entry>();
        ArrayList<ILineDataSet> all = new ArrayList<>();


        //view.findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
        // Line Chart for LiveData




        //Click Listener Start here

        StartButton.setOnClickListener(new View.OnClickListener() {
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
                        // kann weg...
                        //Log.d(TAG,"on Create: Daten: "+ datalist.get(count).getX());
                        count=count+1;
                        // eingabe in die Datenbank
                        sensorViewModel.setSensor(tempmemory);
                        xyz.setText(
                                "x:" + sensorData.getP1() + "y:" +sensorData.getP2() + "z:" + sensorData.getP3());
                        //datalist.add(new Memory(count, sensorData.getP1(),sensorData.getP2(),sensorData.getP3(),System.currentTimeMillis()));
                        values1.add(new Entry(count,sensorData.getP1()));
                        values2.add(new Entry(count, sensorData.getP2()));
                        values3.add(new Entry(count, sensorData.getP3()));

                        LineDataSet lineDataSetX = new LineDataSet(values1,"Data Set X");
                        lineDataSetX.setColor(Color.GREEN);
                        lineDataSetX.setDrawCircles(false);
                        lineDataSetX.setDrawCircleHole(false);
                        lineDataSetX.setDrawValues(false);

                        LineDataSet lineDataSetY = new LineDataSet(values2,"Data Set Y");
                        lineDataSetY.setColor(Color.BLUE);
                        lineDataSetY.setDrawCircles(false);
                        lineDataSetY.setDrawCircleHole(false);
                        lineDataSetY.setDrawValues(false);

                        LineDataSet lineDataSetZ = new LineDataSet(values3,"Data Set Z");
                        lineDataSetZ.setColor(Color.BLACK);
                        lineDataSetZ.setDrawCircles(false);
                        lineDataSetZ.setDrawCircleHole(false);
                        lineDataSetZ.setDrawValues(false);

                        //all.add(lineDataSetX);
                        //all.add(lineDataSetY);
                        //all.add(lineDataSetZ);


                        LineData data1 = new LineData(lineDataSetX);

                        LineData data2 = new LineData(lineDataSetY);
                        LineData data3 = new LineData(lineDataSetZ);

                        lineChart.setData(data1);
                        lineChart.invalidate();
                        lineChart.setData(data2);
                        lineChart.invalidate();
                        lineChart.setData(data3);
                        lineChart.invalidate();


                        //  Entscheiden ob alle Senoren oder nur einer






                        count = count + 1;

                    };

                    mainViewModel.sensorData.observe(getViewLifecycleOwner(),observer);
                }
            }
        });

        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.sensorData.removeObserver(observer);
                observer = null;
                xyz.setText("Sie haben die Messung gestoppt." );
                count = 0;
                datalist.clear();
                }
        });


        FeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_startFragment_to_zweitesFragment);
            }
        });

    }


}
