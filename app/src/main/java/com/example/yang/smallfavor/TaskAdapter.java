package com.example.yang.smallfavor;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Yang on 2016/12/25.
 */
public class TaskAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private List<Labor_information> task_list = new ArrayList<Labor_information>();
    private Context c;
    private int flag;
    public TaskAdapter(Context c, List<Labor_information> task_list, int flag) {
        this.c = c;
        myInflater = LayoutInflater.from(c);
        this.task_list = task_list;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        if(task_list!=null) {
            return task_list.size();
        }else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return task_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertview, ViewGroup viewGroup) {
        TextView title;
        TextView price;
        TextView state;
        if(flag == 0) {
            convertview = myInflater.inflate(R.layout.task_object_list, viewGroup, false);
            title = (TextView) convertview.findViewById(R.id.task_textView_title);
            price = (TextView) convertview.findViewById(R.id.task_textView_price);
            state = (TextView) convertview.findViewById(R.id.task_textView_state);
        }else{
            convertview = myInflater.inflate(R.layout.task2do_object_list, viewGroup, false);
            title = (TextView) convertview.findViewById(R.id.task2do_textView_title);
            price = (TextView) convertview.findViewById(R.id.task2do_textView_price);
            state = (TextView) convertview.findViewById(R.id.task2do_textView_state);
        }
        title.setText(task_list.get(position).title);
        price.setText("$"+Integer.toString(task_list.get(position).price));
        if(task_list.get(position).state == 1) {
            if (flag == 0) {
                state.setText(task_list.get(position).accepter + " running");
                ColorDefiner colorDefiner = new ColorDefiner();
                colorDefiner.SetColor(task_list.get(position).rate, state);
            }else{
                state.setText("your duty");
            }
        convertview.setBackgroundColor(Color.GRAY);

        }else if(task_list.get(position).state == 0){
            state.setText("waiting");
        }else{
            state.setText("canceled");
        }
        convertview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                if(flag == 0) {
                    ((MainActivity) c).task_content_layout(task_list.get(position));
                }else{
                    ((MainActivity) c).task2do_content_layout(task_list.get(position));
                }
            }
        });
        return convertview;
    }
}

