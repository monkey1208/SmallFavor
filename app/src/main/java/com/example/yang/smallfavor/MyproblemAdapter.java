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

public class MyproblemAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private List<Intelligence_information> intelligence_list = new ArrayList<Intelligence_information>();
    private Context c;
    public MyproblemAdapter(Context c, List<Intelligence_information> intelligence_list) {
        this.c = c;
        myInflater = LayoutInflater.from(c);
        this.intelligence_list = intelligence_list;
    }
    public void addItem(Intelligence_information intell_info){
        intelligence_list.add(intell_info);
    }
    @Override
    public int getCount() {
        return intelligence_list.size();
    }

    @Override
    public Object getItem(int position) {
        return intelligence_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertview, ViewGroup viewGroup) {
        convertview = myInflater.inflate(R.layout.myproblem_list, viewGroup, false);
        TextView title = (TextView) convertview.findViewById(R.id.myproblem_textView_title);
        TextView price = (TextView) convertview.findViewById(R.id.myproblem_textView_price);
        TextView ID = (TextView) convertview.findViewById(R.id.myproblem_textView_ID);
        //ImageView heart = (ImageView)  convertview.findViewById(R.id.intelligence_ImageView_icon);
        TextView popularity = (TextView) convertview.findViewById(R.id.myproblem_textView_popularity);
        TextView department = (TextView) convertview.findViewById(R.id.myproblem_textView_department);
        title.setText(intelligence_list.get(position).title);
        price.setText(Integer.toString(intelligence_list.get(position).price));
        ID.setText(intelligence_list.get(position).ID);
        popularity.setText(Integer.toString(intelligence_list.get(position).popularity));
        department.setText(intelligence_list.get(position).department);



        convertview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                ((MainActivity)c).myproblem_content_layout(intelligence_list.get(position));
            }
        });
        return convertview;
    }
}
