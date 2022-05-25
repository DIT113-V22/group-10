package com.example.hermes.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hermes.Delivery;
import com.example.hermes.R;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter {

    private final Activity context;
    private final Integer imageID;
    private final ArrayList<String> nameList;
    private final ArrayList<String> infoList = new ArrayList<>();

    public CustomListAdapter(Activity context, ArrayList<Delivery> deliveries, ArrayList<String> nameList, int imageID){

        super(context, R.layout.listview_done, nameList);
        /*for(Delivery delivery : deliveries){
            nameList.add(delivery.getDate());
        }

         */


        for(Delivery delivery : deliveries){
            infoList.add(delivery.getDate());
        }

        this.nameList = nameList;
        this.context = context;
        this.imageID = imageID;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_done, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = rowView.findViewById(R.id.nameTextViewID);
        TextView infoTextField = rowView.findViewById(R.id.infoTextViewID);
        ImageView imageView = rowView.findViewById(R.id.imageView1ID);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(infoList.get(position));
        infoTextField.setText(nameList.get(position));
        imageView.setImageResource(imageID);

        return rowView;

    }


}
