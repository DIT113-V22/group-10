package com.example.hermes;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.util.ArrayList;


// Adapted from https://github.com/mitchtabian/TabFragments

public class deliveryTabs extends AppCompatActivity {

    //public static DatabaseManager db = DatabaseManager.getDatabaseManager();
    //public static ArrayList<Delivery> deliveries = db.allDeliveries();
    private static final String TAG = "deliveryTabs";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private Spinner spinner;
    String[] categories = {"Descending Alphabetical", "Ascending Alphabetical", "Oldest", "Newest"};
    public static ArrayList<Delivery> deliveries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_deliveries);
        Log.d(TAG, "onCreate: Starting.");

        Delivery delivery1 = new Delivery();
        Delivery delivery2 = new Delivery();
        Delivery delivery3 = new Delivery();
        Delivery delivery4 = new Delivery();
        Delivery delivery5 = new Delivery();
        Delivery delivery6 = new Delivery();
        Delivery delivery7 = new Delivery();
        Delivery delivery8 = new Delivery();
        Delivery delivery9 = new Delivery();
        Delivery delivery10 = new Delivery();
        try {

        delivery1.setCustomerID("Emrik");
        delivery1.setDate("20221104 13:45:00");
        deliveries.add(delivery1);
        delivery2.setCustomerID("Erik");
        delivery2.setDate("20110912 08:23:55");
        deliveries.add(delivery2);
        delivery3.setCustomerID("Amin");
        delivery3.setDate("20222104 13:45:00");
        deliveries.add(delivery3);
        delivery4.setCustomerID("nihiliss");
        delivery4.setDate("20221120 09:15:02");
        deliveries.add(delivery4);
        delivery5.setCustomerID("Julia");
        delivery5.setDate("20110612 08:23:55");
        deliveries.add(delivery5);
        delivery6.setCustomerID("Yasamin");
        delivery6.setDate("20220113 12:11:56");
        deliveries.add(delivery6);
        delivery7.setCustomerID("Daniel");
        delivery7.setDate("20220113 00:11:56");
        deliveries.add(delivery7);
        delivery8.setCustomerID("Sven");
        delivery8.setDate("19800627 16:00:17");
        deliveries.add(delivery8);
        delivery9.setCustomerID("Karl");
        delivery9.setDate("20240520 09:35:08");
        deliveries.add(delivery9);
        delivery10.setCustomerID("Björn");
        delivery10.setDate("20220512 23:13:44");
        deliveries.add(delivery10);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mSectionsPageAdapter = new SectionsPageAdapter(this, getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, categories));

        //spinner selection event
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if( i >= 0 && i < categories.length){
                    switch (i){
                        case 0:
                            deliveries.sort(Delivery.byName);
                            break;
                        case 1:
                            deliveries.sort(Delivery.byNameReverse);
                            break;
                        case 2:
                            deliveries.sort(Delivery.byOldest);
                            break;
                        case 3:
                            deliveries.sort(Delivery.byNewest);
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}

