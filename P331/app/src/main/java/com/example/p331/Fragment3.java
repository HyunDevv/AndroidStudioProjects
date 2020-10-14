package com.example.p331;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Fragment3 extends Fragment {

    Button button;

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = null;
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_3,container,false);
        button = viewGroup.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "tab33", Toast.LENGTH_SHORT).show();
            }
        });

        return viewGroup;
    }
}