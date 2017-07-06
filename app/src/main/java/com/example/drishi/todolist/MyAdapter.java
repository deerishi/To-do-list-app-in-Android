package com.example.drishi.todolist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by drishi on 05/07/17.
 */

public class MyAdapter extends ArrayAdapter<String> {
    List<String> data;
    public MyAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.data=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custom_list_layout, null);
        TextView tv=(TextView)view.findViewById(R.id.serial_num);
        ImageView img=(ImageView) view.findViewById(R.id.icon);
        if(data.get(position).length()>5){
            img.setImageResource(R.drawable.icon1);
        }else{
            img.setImageResource(R.drawable.icon2);
        }
        tv.setText(Integer.toString(position+1)+") "+data.get(position));
        return view;
    }
}
