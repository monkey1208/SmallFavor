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
public class TransactionAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private List<login_information.trade> trade_list = new ArrayList<login_information.trade>();
    private Context c;
    private String myaddress;
    public TransactionAdapter(Context c, List<login_information.trade> trade_list, String myaddress) {
        this.c = c;
        myInflater = LayoutInflater.from(c);
        this.trade_list = trade_list;
        this.myaddress = myaddress;
    }

    @Override
    public int getCount() {
        if(trade_list!=null) {
            return trade_list.size();
        }else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return trade_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertview, ViewGroup viewGroup) {
        convertview = myInflater.inflate(R.layout.transaction_list_object, viewGroup, false);
        TextView tofrom = (TextView) convertview.findViewById(R.id.transaction_list_tofrom_textView);
        TextView address = (TextView) convertview.findViewById(R.id.transaction_list_address_textView);
        TextView price = (TextView) convertview.findViewById(R.id.transaction_price_textView);
        if(trade_list.get(position) != null) {
            if (trade_list.get(position).receiver.equals(myaddress)) {
                tofrom.setText("From");
                address.setText(trade_list.get(position).sender);
                price.setText("$"+Integer.toString(trade_list.get(position).money));
            } else if (trade_list.get(position).sender.equals(myaddress)) {
                tofrom.setText("To");
                address.setText(trade_list.get(position).receiver);
                price.setText("$"+Integer.toString(trade_list.get(position).money));
            } else {
                tofrom.setText("YOU FUCK");
            }
        }else{
            System.out.println("NULL test");
        }

        return convertview;
    }
}

