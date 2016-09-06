package com.example.administrator.inteligence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/4.
 */
public class DaipipeiAdapter extends ArrayAdapter {
    private int myResource;
    private TextView pipei;
    public DaipipeiAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        myResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Daipipei daipipei = (Daipipei) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(myResource,null);
        pipei = (TextView) view.findViewById(R.id.daipipei);
        pipei.setText(daipipei.getDaipipeiName());
        return view;
    }
}
