package com.example.muf_projekt_herdtle_hoch_puglia.Fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.muf_projekt_herdtle_hoch_puglia.Data.Memory;
import com.example.muf_projekt_herdtle_hoch_puglia.Database.ProjectApplication;
import com.example.muf_projekt_herdtle_hoch_puglia.MediaService;
import com.example.muf_projekt_herdtle_hoch_puglia.R;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.UUID;

import static android.content.ContentValues.TAG;
import static android.content.Context.BIND_AUTO_CREATE;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;


public class Fragment2 extends Fragment {




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final Button HomeBildschrim = view.findViewById(R.id.back);
        //final Button Special = view.findViewById(R.id.specialButton);

        LineChart lineChart_x = view.findViewById(R.id.DataChartX);
        Description desc_x = new Description();
        desc_x.setText("");
        lineChart_x.setDescription(desc_x);
        lineChart_x.setDrawGridBackground(false);

        LineChart lineChart_y = view.findViewById(R.id.DataChartY);
        Description desc_y = new Description();
        desc_y.setText("");
        lineChart_y.setDescription(desc_y);
        lineChart_y.setDrawGridBackground(false);

        LineChart lineChart_z = view.findViewById(R.id.DataChartZ);
        Description desc_z = new Description();
        desc_z.setText("");
        lineChart_z.setDescription(desc_z);
        lineChart_z.setDrawGridBackground(false);

        ArrayList<Entry> x_values = new ArrayList<Entry>();
        ArrayList<Entry> y_values = new ArrayList<Entry>();
        ArrayList<Entry> z_values = new ArrayList<Entry>();


        ((ProjectApplication) getActivity()
                .getApplication())
                .getDatabase()
                .getSensorDao()
                .getAllMemory()
                .observe(getViewLifecycleOwner(),
                        memory -> {
                            for (Memory s : memory) {
                                // TODO: render speicher
                                Log.d(TAG, "onCreate Daten: " + s.getX());

                                x_values.add(new Entry(s.getIdline(), s.getX()));
                                y_values.add(new Entry(s.getIdline(), s.getY()));
                                z_values.add(new Entry(s.getIdline(), s.getZ()));

                                LineDataSet x_lineDataSet = new LineDataSet(x_values, "X-Acc");
                                x_lineDataSet.setColor(Color.RED);
                                x_lineDataSet.setDrawCircles(false);
                                x_lineDataSet.setDrawValues(false);

                                LineDataSet y_lineDataSet = new LineDataSet(y_values, "Y-Acc");
                                y_lineDataSet.setColor(Color.BLUE);
                                y_lineDataSet.setDrawCircles(false);
                                y_lineDataSet.setDrawValues(false);

                                LineDataSet z_lineDataSet = new LineDataSet(z_values, "Z-Acc");
                                z_lineDataSet.setColor(Color.GREEN);
                                z_lineDataSet.setDrawCircles(false);
                                z_lineDataSet.setDrawValues(false);

                                LineData data_x = new LineData(x_lineDataSet);
                                LineData data_y = new LineData(y_lineDataSet);
                                LineData data_z = new LineData(z_lineDataSet);

                                lineChart_x.setData(data_x);
                                lineChart_x.invalidate();

                                lineChart_y.setData(data_y);
                                lineChart_y.invalidate();

                                lineChart_z.setData(data_z);
                                lineChart_z.invalidate();

                            }
                        });

        HomeBildschrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_zweitesFragment_to_startFragment);
            }
        });


        /*Special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaBinder == null) return;
                mediaBinder.play(R.raw.dings);
            }
        });*/

    }




    /*@Override
    public void onResume() {
        super.onResume();
        if (mediaServiceConnection == null) {
            getActivity().bindService(
                    new Intent(getContext(), MediaService.class),
                    mediaServiceConnection = new MediaServiceConenction(),
                    Context.BIND_AUTO_CREATE
            );
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaServiceConnection != null) {
            getActivity().unbindService(mediaServiceConnection);
            mediaServiceConnection = null;
        }
    }

    private final class MediaServiceConenction implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mediaBinder = (MediaService.MediaBinder) service;


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mediaBinder = null;

        }
    }*/
}