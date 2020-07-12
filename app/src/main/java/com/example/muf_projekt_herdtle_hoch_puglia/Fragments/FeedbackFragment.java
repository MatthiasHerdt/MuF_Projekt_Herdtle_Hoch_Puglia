package com.example.muf_projekt_herdtle_hoch_puglia.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.muf_projekt_herdtle_hoch_puglia.Data.Memory;
import com.example.muf_projekt_herdtle_hoch_puglia.Data.SensorData;
import com.example.muf_projekt_herdtle_hoch_puglia.MainViewModel;
import com.example.muf_projekt_herdtle_hoch_puglia.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class FeedbackFragment extends Fragment {

    private MainViewModel mainViewModel;
    private Observer<SensorData> observer;
    private ArrayList<Memory> datalist;
    private int count = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feedback_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final TextView vendor = view.findViewById(R.id.vendor);
        final TextView name = view.findViewById(R.id.name);
        final TextView version = view.findViewById(R.id.version);
        final TextView xyz = view.findViewById(R.id.xyz);

        final Button StopButton = view.findViewById(R.id.stoppen);
        final Button HomeButton = view.findViewById(R.id.HomeButton);

        LineChart lineChart = view.findViewById(R.id.LiveData);
        Description desc_x = new Description();
        desc_x.setText("");
        lineChart.setDescription(desc_x);
        lineChart.setDrawGridBackground(false);

        datalist = new ArrayList<>();
        mainViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MainViewModel.class);

        ArrayList<Entry> values1 = new ArrayList<Entry>();
        ArrayList<Entry> values2 = new ArrayList<Entry>();
        ArrayList<Entry> values3 = new ArrayList<Entry>();

        if(observer == null){
            observer = (sensorData)->{

                vendor.setText("Vendor" + sensorData.getSensor().getVendor());
                name.setText("Name" + sensorData.getSensor().getName());
                version.setText("Version" + sensorData.getSensor().getVersion());
                xyz.setText("x:" + sensorData.getP1() + "y:" +sensorData.getP2() + "z:" + sensorData.getP3());

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

                LineData data1 = new LineData(lineDataSetX,lineDataSetY,lineDataSetZ);

                lineChart.setData(data1);
                lineChart.invalidate();

                count = count + 1;

            };

            mainViewModel.sensorData.observe(getViewLifecycleOwner(),observer);
        }

        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Messung gestoppt", Toast.LENGTH_SHORT).show();
                mainViewModel.sensorData.removeObserver(observer);
                observer = null;
                xyz.setText("Sie haben die Messung gestoppt." );
                count = 0;
                //datalist.clear();
                values1.clear();
                values2.clear();
                values3.clear();
            }
        });

        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count != 0) {
                    Toast.makeText(getContext(), "Messung gestoppt", Toast.LENGTH_SHORT).show();

                }


                mainViewModel.sensorData.removeObserver(observer);
                observer = null;
                xyz.setText("Sie haben die Messung gestoppt." );
                count = 0;
                //datalist.clear();
                values1.clear();
                values2.clear();
                values3.clear();

                Navigation.findNavController(view).navigate(R.id.action_feedbackfragement_to_startFragment);
            }
        });



    }



}
