package com.example.muf_projekt_herdtle_hoch_puglia.Fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.room.Database;

import com.example.muf_projekt_herdtle_hoch_puglia.Database.ProjectApplication;
import com.example.muf_projekt_herdtle_hoch_puglia.Database.ProjectDatabase;
import com.example.muf_projekt_herdtle_hoch_puglia.MainViewModel;
import com.example.muf_projekt_herdtle_hoch_puglia.Data.Memory;
import com.example.muf_projekt_herdtle_hoch_puglia.MediaService;
import com.example.muf_projekt_herdtle_hoch_puglia.R;
import com.example.muf_projekt_herdtle_hoch_puglia.Data.SensorData;
import com.example.muf_projekt_herdtle_hoch_puglia.ViewModel.SensorViewModel;

import com.example.muf_projekt_herdtle_hoch_puglia.MediaService;
import com.example.muf_projekt_herdtle_hoch_puglia.R;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Fragment1 extends Fragment {
    private SensorViewModel sensorViewModel;
    private String dataname = "Messung";

    private MainViewModel mainViewModel;
    private Observer<SensorData> observer;
    private ArrayList<Memory> datalist;
    private int count = 0;

    private Button ding;
    private MediaServiceConenction mediaServiceConnection = null;
    private MediaService.MediaBinder mediaBinder;



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


        /*ImageView imageView=(ImageView) view.findViewById(R.id.startFragment);
        imageView.setImageResource(R.drawable.ic_launcher_background);*/


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final Button StartButton = view.findViewById(R.id.startButton);
        final Button FeedbackButton = view.findViewById(R.id.feedbackButton);

        final Button Special = view.findViewById(R.id.specialButton);

        final Button InfoButton = view.findViewById(R.id.infoButton);


        observer = null;
        datalist = new ArrayList<>();
        mainViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MainViewModel.class);

        //Buttons

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Messung gestartet", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_startFragment_to_feedbackfragement);









            }

        });

        FeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                mainViewModel.sensorData.removeObserver(observer);
                observer = null;

                count = 0;
                //datalist.clear();

                Navigation.findNavController(view).navigate(R.id.action_startFragment_to_zweitesFragment);
            }
        });

        InfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.sensorData.removeObserver(observer);
                observer = null;

                count = 0;
                //datalist.clear();

                Navigation.findNavController(view).navigate(R.id.action_startFragment_to_infoFragment);
            }
        });

        Special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaBinder == null) return;
                mediaBinder.play(R.raw.dings);
            }
        });


    }

    @Override
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
    }


}
