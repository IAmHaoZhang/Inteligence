package com.example.administrator.inteligence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/4.
 */
public class GuizekuAdapter extends ArrayAdapter {
    private int myResource;
    private TextView guize;

    public GuizekuAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        myResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        Guizeku guizeku = (Guizeku) getItem(position);
        view = LayoutInflater.from(getContext()).inflate(myResource, null);
        guize = (TextView) view.findViewById(R.id.guize);
        guize.setText(guizeku.getTite() + " " + guizeku.getGuizeName() + " " + guizeku.getConclusion());
        return view;
    }
}
