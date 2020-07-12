package com.example.muf_projekt_herdtle_hoch_puglia.Fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.muf_projekt_herdtle_hoch_puglia.MediaService;
import com.example.muf_projekt_herdtle_hoch_puglia.R;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import static android.content.Context.BIND_AUTO_CREATE;

public class Fragment2 extends Fragment {

    private Button ding;
    private MediaServiceConenction mediaServiceConnection = null;
    private MediaService.MediaBinder mediaBinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final Button HomeBildschrim = view.findViewById(R.id.BackToHome);
        final Button Special = view.findViewById(R.id.specialButton);

        HomeBildschrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_zweitesFragment_to_startFragment);
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