package com.example.hermes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hermes.ui.CustomListAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;

// Adapted from https://github.com/mitchtabian/TabFragments <- could be removed?

public class deliveryTabs extends AppCompatActivity {

    /*
    Uncomment when database works
    public static DatabaseManager db = DatabaseManager.getDatabaseManager();
    public static ArrayList<Delivery> deliveries = db.allDeliveries();
     */
    private static final String TAG = "deliveryTabs";
    private CustomListAdapter adapter;
    private ListView listView;
    private int imageId = R.drawable.box;
    private String[] categories = {"Descending Alphabetical", "Ascending Alphabetical", "Oldest", "Newest"};
    private ArrayList<Delivery> deliveries = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private boolean showOngoing = true;
    private boolean showCompleted = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_deliveries);
        Log.d(TAG, "onCreate: Starting.");

        listView = findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(this, deliveries, nameList, imageId));

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories));

        //spinner selection event
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0 && i < categories.length) {
                    switch (i) {
                        case 0:
                            updateList(Delivery.byName);
                            break;
                        case 1:
                            updateList(Delivery.byNameReverse);
                            break;
                        case 2:
                            updateList(Delivery.byOldest);
                            break;
                        case 3:
                            updateList(Delivery.byNewest);
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //sorts deliveries with comparator
    private void updateList(Comparator<Delivery> comparator) {
        deliveries.sort(comparator);
        updateNameList(deliveries);
        adapter = new CustomListAdapter(this, deliveries, nameList, imageId);
        listView.setAdapter(adapter);
    }

    //filters deliveries when checkbox is checked or unchecked
    @SuppressLint("NonConstantResourceId")
    public void filter(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        ArrayList<Delivery> deliveriesCopy = new ArrayList<>();

        switch (view.getId()) {
            case R.id.checkBox_ongoing:
                showOngoing = checked;
                break;
            case R.id.checkbox_completed:
                showCompleted = checked;
                break;
        }
        for (Delivery delivery : deliveries) {
            if (showOngoing && !delivery.getDone()) {
                deliveriesCopy.add(delivery);
            }
            if (showCompleted && delivery.getDone()) {
                deliveriesCopy.add(delivery);
            }
        }
        updateNameList(deliveriesCopy);
        adapter = new CustomListAdapter(this, deliveriesCopy, nameList, imageId);
        listView.setAdapter(adapter);
    }

    private void updateNameList(ArrayList<Delivery> updatedDeliveries) {
        nameList.clear();
        for (Delivery delivery : updatedDeliveries) {
            //nameList.add(delivery.getCustomerID());
        }
    }
}

