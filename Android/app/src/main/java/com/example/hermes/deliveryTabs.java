package com.example.hermes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
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
    private int imageId = R.drawable.newpackage;
    private String[] categories = {"Filter","Newest", "Oldest"};
    private ArrayList<Delivery> deliveries = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private boolean showOngoing = true;
    private boolean showCompleted = true;
    private Button goBack;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_deliveries);
        Log.d(TAG, "onCreate: Starting.");

        addDeliveries();

        listView = findViewById(R.id.listView);
        adapter = new CustomListAdapter(this, deliveries, nameList, imageId); 
        listView.setAdapter(adapter);
        //listView.setAdapter(new CustomListAdapter(this, deliveries, nameList, imageId));

        goBack = (Button) findViewById(R.id.alldeliBack);
        goBack.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        });


        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories));

        //spinner selection event
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0 && i < categories.length) {
                    switch (i) {
                        case 0:
                            updateNameList(deliveries);
                            listView.setAdapter(adapter);
                            break;
                        case 1:
                            updateList(Delivery.byNewest);
                            break;
                        case 2:
                            updateList(Delivery.byOldest);
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
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

    private void addDeliveries() {
        Delivery delivery1 = new Delivery("Emrik");
        Delivery delivery2 = new Delivery("Erik");
        Delivery delivery3 = new Delivery("Amin");
        Delivery delivery4 = new Delivery("");
        Delivery delivery5 = new Delivery("nihiliss");
        Delivery delivery6 = new Delivery("Julia");
        Delivery delivery7 = new Delivery("Yasamin");
        Delivery delivery8 = new Delivery("Daniel");
        Delivery delivery9 = new Delivery("Sven");
        Delivery delivery10 = new Delivery("Björn");
        try {
            delivery1.setDate("20221104 13:45:00");
            delivery1.addItem("pain killer");
            delivery1.setDone(true);
            deliveries.add(delivery1);
            delivery2.setDate("20110912 08:23:55");
            delivery2.addItem("covid");
            delivery2.setDone(true);
            deliveries.add(delivery2);
            delivery3.setDate("20222104 13:45:00");
            delivery3.addItem("pain");
            delivery3.setDone(true);
            deliveries.add(delivery3);
            delivery4.setDate("20221120 09:15:02");
            delivery4.addItem("health");
            delivery4.setDone(true);
            deliveries.add(delivery4);
            delivery5.setDate("20110612 08:23:55");
            delivery5.addItem("amin");
            delivery5.setDone(true);
            deliveries.add(delivery5);
            delivery6.setDate("20220113 12:11:56");
            delivery6.addItem("patrick");
            delivery6.setDone(false);
            deliveries.add(delivery6);
            delivery7.setDate("20220113 00:11:56");
            delivery7.addItem("omg");
            delivery7.setDone(false);
            deliveries.add(delivery7);
            delivery8.setDate("19800627 16:00:17");
            delivery8.addItem("death");
            delivery8.setDone(false);
            deliveries.add(delivery8);
            delivery9.setDate("20240520 09:35:08");
            delivery9.addItem("hospital");
            delivery9.setDone(false);
            deliveries.add(delivery9);
            delivery10.setDate("20220512 23:13:44");
            delivery10.addItem("nothing");
            delivery10.setDone(false);
            deliveries.add(delivery10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void updateNameList(ArrayList<Delivery> updatedDeliveries) {
        nameList.clear();
        for (Delivery delivery : updatedDeliveries) {
            nameList.add(delivery.itemList());
        }
    }
}

