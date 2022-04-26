package com.example.hermes.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hermes.JsonManager;
import com.example.hermes.R;
import com.example.hermes.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private JsonManager jsonManager;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView deliveryList = root.findViewById(R.id.DList);

        //only fo the functionality. it'll be changed with the data_base
        ArrayList<String> list = new ArrayList<>();
        list.add("Amin");
        list.add("Daniel");

        jsonManager = new JsonManager();
        JSONObject j = jsonManager.readJsonObject("com/example/hermes/data/deliveries.Json");
        JSONArray array = (JSONArray) j.get("deliveries");

        //ArrayAdapter<Delivery> arrayAdapter = new ArrayAdapter<Delivery>(getContext(), android.R.layout.simple_list_item_1, (List<Delivery>) array);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
        
        deliveryList.setAdapter(arrayAdapter);



        return root;

    }

    private void setContentView(ConstraintLayout root) {
    }


}

