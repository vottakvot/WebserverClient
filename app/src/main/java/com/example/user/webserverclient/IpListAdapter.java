package com.example.user.webserverclient;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class IpListAdapter
        extends BaseAdapter {

    private final Context context;
    private final LayoutInflater inflater;
    private ArrayList<DataUnit> arrayDataUnit = null;

    IpListAdapter(Context context, ArrayList<DataUnit> arrayDataUnit) {
        super();
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayDataUnit = arrayDataUnit;
    }

    public void setNewDataForAdapter(ArrayList<DataUnit> arrayDataUnit){
        this.arrayDataUnit = arrayDataUnit;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return (arrayDataUnit != null)? arrayDataUnit.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (arrayDataUnit != null)? arrayDataUnit.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        public TextView numberRecord;
        public TextView routerIP;
        public TextView hostIP;
        public TextView dateTime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder viewHolder = null;

        // Fill viewHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.ip_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.numberRecord = (TextView) view.findViewById(R.id.numberRecord);
            viewHolder.routerIP = (TextView) view.findViewById(R.id.routerIP);
            viewHolder.hostIP = (TextView) view.findViewById(R.id.hostIP);
            viewHolder.dateTime = (TextView) view.findViewById(R.id.dateTime);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        try {
                viewHolder.numberRecord.setText(Integer.toString(position));
                viewHolder.routerIP.setText(arrayDataUnit.get(position).getRouterIP());
                viewHolder.hostIP.setText(arrayDataUnit.get(position).getHostIP());
                viewHolder.dateTime.setText(arrayDataUnit.get(position).getDateIn());

        } catch (RuntimeException e){
            e.printStackTrace();
        }

        return view;
    }
}