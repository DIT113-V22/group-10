package com.example.hermes.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.example.hermes.R;
import com.example.hermes.deliveryTabs;

public class HomeFragment extends Fragment {

    private Button allDeliveriesB;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        allDeliveriesB = (Button) view.findViewById(R.id.allDeliveriesB);

        allDeliveriesB.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), deliveryTabs.class);
            startActivity(intent);
        });
        return view;
    }

    private void setContentView(ConstraintLayout root) {
    }


}

