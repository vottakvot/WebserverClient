package com.example.user.webserverclient;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView ipList = null;
    IpListAdapter ipListAdapter = null;
    LayoutInflater layoutInflater = null;
    ArrayList<DataUnit> arrayDataUnit = null;
    View header = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        ipList = (ListView)findViewById(R.id.ip_list);
        arrayDataUnit = new ArrayList<DataUnit>();
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        header = layoutInflater.inflate(R.layout.ip_list_item, null);
        ((TextView)header.findViewById(R.id.numberRecord)).setText("N");
        ((TextView)header.findViewById(R.id.routerIP)).setText("Router IP");
        ((TextView)header.findViewById(R.id.hostIP)).setText("Host IP");
        ((TextView)header.findViewById(R.id.dateTime)).setText("Date");

        for(int i = 0; i < 100; i++){
            DataUnit dataUnit = new DataUnit(Integer.toString(new Random().nextInt()), Integer.toString(new Random().nextInt()));
            dataUnit.setId(Integer.toString(new Random().nextInt()));
            dataUnit.setDateIn(Integer.toString(new Random().nextInt()));
            arrayDataUnit.add(dataUnit);
        }

        ipListAdapter = new IpListAdapter(this, arrayDataUnit);
        ipList.addHeaderView(header);
        ipList.setAdapter(ipListAdapter);
    }
}
