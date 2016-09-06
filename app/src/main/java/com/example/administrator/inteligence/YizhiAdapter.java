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
public class YizhiAdapter extends ArrayAdapter {
    private int myResource;
    private TextView yizhitiaojian;
    public YizhiAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        myResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Yizhi yizhi = (Yizhi) getItem(position);
        View view;
        view = LayoutInflater.from(getContext()).inflate(myResource,null);
        yizhitiaojian = (TextView) view.findViewById(R.id.yizhi);
        yizhitiaojian.setText(yizhi.getYizhiName());
        return view;
    }
}
