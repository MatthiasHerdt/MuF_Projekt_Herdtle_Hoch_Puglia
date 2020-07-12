package com.example.muf_projekt_herdtle_hoch_puglia.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.muf_projekt_herdtle_hoch_puglia.R;

public class InfoFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);
        return view;






    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final Button ZurückButton = view.findViewById(R.id.back);

        ZurückButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {



                Navigation.findNavController(view).navigate(R.id.action_infoFragment_to_startFragment);
            }
        });
    }
}
