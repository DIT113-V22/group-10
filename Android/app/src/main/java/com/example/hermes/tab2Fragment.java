package com.example.hermes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mongodb.lang.Nullable;

public class tab2Fragment extends Fragment {
    public static final String TAG = "Tab2Fragment";

    //private ListView doneL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2fragment, container, false);

        return view;
    }
}
