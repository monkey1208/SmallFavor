package com.example.yang.smallfavor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/12/27.
 */

public class Intelligence_depart_Adapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private List<depart_info> intelligence_list = new ArrayList<depart_info>();
    private Context c;
    public Intelligence_depart_Adapter(Context c, List<depart_info> intelligence_list) {
        this.c = c;
        myInflater = LayoutInflater.from(c);
        this.intelligence_list = intelligence_list;
    }

    @Override
    public int getCount() {
        return intelligence_list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertview, ViewGroup viewGroup) {
        convertview = myInflater.inflate(R.layout.intelligence_department, viewGroup, false);
        TextView depart = (TextView) convertview.findViewById(R.id.intelligence_textView_depart);
        depart.setText(intelligence_list.get(position).depart);

        convertview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                if(intelligence_list.get(position).depart.equals("資工系"))
                    ((MainActivity)c).intelligence_sec_layout("CSIE");
                else if(intelligence_list.get(position).depart.equals("中文系"))
                    ((MainActivity)c).intelligence_sec_layout("CL");
                else if(intelligence_list.get(position).depart.equals("法律系"))
                    ((MainActivity)c).intelligence_sec_layout("LAW");
                else if(intelligence_list.get(position).depart.equals("電機系"))
                    ((MainActivity)c).intelligence_sec_layout("EE");
                else if(intelligence_list.get(position).depart.equals("外文系"))
                    ((MainActivity)c).intelligence_sec_layout("OL");
                else if(intelligence_list.get(position).depart.equals("森林系"))
                    ((MainActivity)c).intelligence_sec_layout("F");
                else if(intelligence_list.get(position).depart.equals("日文系"))
                    ((MainActivity)c).intelligence_sec_layout("J");
                else if(intelligence_list.get(position).depart.equals("土木系"))
                    ((MainActivity)c).intelligence_sec_layout("CH");
                else if(intelligence_list.get(position).depart.equals("經濟系"))
                    ((MainActivity)c).intelligence_sec_layout("E");
                else if(intelligence_list.get(position).depart.equals("機械系"))
                    ((MainActivity)c).intelligence_sec_layout("M");
                else if(intelligence_list.get(position).depart.equals("化工系"))
                    ((MainActivity)c).intelligence_sec_layout("CM");
            }
        });
        return convertview;
    }
}
