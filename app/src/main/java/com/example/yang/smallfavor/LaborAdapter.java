package com.example.yang.smallfavor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Yang on 2016/12/25.
 */
public class LaborAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private List<Labor_information> labor_list = new ArrayList<Labor_information>();
    public LaborAdapter(Context c, List<Labor_information> labor_list){
        myInflater = LayoutInflater.from(c);
        this.labor_list = labor_list;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        convertview = myInflater.inflate(R.layout.labor_object_list, viewGroup, false);
        TextView title = (TextView)convertview.findViewById(R.id.labor_textView_title);
        TextView price = (TextView)convertview.findViewById(R.id.labor_textView_price);
        TextView ID = (TextView)convertview.findViewById(R.id.labor_textView_ID);
        title.setText(labor_list.get(position).title);
        price.setText(labor_list.get(position).price);
        ID.setText(labor_list.get(position).ID);
        return convertview;
    }
}
