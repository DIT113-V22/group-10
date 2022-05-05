package com.example.hermes.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hermes.R;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter {

    private final Activity context;
    private final Integer imageID;
    private final ArrayList<String> nameList;
    private final ArrayList<String> infoList;

    public CustomListAdapter(Activity context, ArrayList<String> nameList, ArrayList<String> infoList, Integer imageID){

        super(context, R.layout.listview_done, nameList);


        this.context=context;
        this.imageID = imageID;
        this.nameList = nameList;
        this.infoList = infoList;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_done, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameTextViewID);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.infoTextViewID);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1ID);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameList.get(position));
        infoTextField.setText(infoList.get(position));
        imageView.setImageResource(imageID);

        return rowView;

    }


}
