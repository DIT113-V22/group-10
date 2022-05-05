package com.example.hermes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.example.hermes.databinding.FragmentHomeBinding;
import com.example.hermes.ui.CustomListAdapter;
import com.mongodb.lang.Nullable;
import java.util.ArrayList;

public class tab1Fragment extends Fragment {
    public static final String TAG = "Tab1Fragment";

    private tab1ViewModel tab1ViewModel;
    private FragmentHomeBinding binding;
    private ListView ongoingL;
    private DatabaseManager db = new DatabaseManager();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1fragment, container, false);

        ongoingL = (ListView) view.findViewById(R.id.ongoingList);


        ArrayList<String> nameList = getDeliveryNames();
        ArrayList<String> infoList = getDeliveryInfos();
        int imageID = R.drawable.box;

        CustomListAdapter myAdapter = new CustomListAdapter(getActivity(), nameList, infoList, imageID);
        ongoingL.setAdapter(myAdapter);

        return view;
    }



    public ArrayList<String> getDeliveryNames() {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Delivery> deliveries = db.allDeliveries();
        String id;
        for (Delivery delivey : deliveries) {
            if (!delivey.getDone()) {
                id = Integer.toString(delivey.getID());
                result.add(id);
            }
        }
        return result;
    }

    public ArrayList<String> getDeliveryInfos() {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Delivery> deliveries = db.allDeliveries();
        String info;
        for (Delivery delivey : deliveries) {
            if (!delivey.getDone()) {
                info = "This order was made on "+delivey.getDate()+" at "+delivey.getTime()+".";
                result.add(info);
            }
        }
        return result;
    }

}
