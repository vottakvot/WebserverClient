package com.example.user.webserverclient;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity
        extends AppCompatActivity
        implements Button.OnClickListener,
                    ProgressDialog.OnDismissListener {
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    public static final String SITE_LINK = "http://testsimpleapps.sytes.net/tools/androidclient";

    private ListView ipList = null;
    private IpListAdapter ipListAdapter = null;
    private LayoutInflater layoutInflater = null;
    private ArrayList<DataUnit> arrayDataUnit = null;
    private View header = null;
    private Button button = null;
    private ProgressDialog progressDialog = null;
    private DownloadFile downloadFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        ipList = (ListView)findViewById(R.id.ip_list);
        button = (Button)findViewById(R.id.refresh_ip_list);
        button.setOnClickListener(this);
        createProgressDialog();
        arrayDataUnit = new ArrayList<DataUnit>();
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        header = layoutInflater.inflate(R.layout.ip_list_item, null);

        ((TextView)header.findViewById(R.id.numberRecord)).setText("N");
        ((TextView)header.findViewById(R.id.routerIP)).setText("Router IP");
        ((TextView)header.findViewById(R.id.hostIP)).setText("Host IP");
        ((TextView)header.findViewById(R.id.dateTime)).setText("Date");

        ipListAdapter = new IpListAdapter(this, arrayDataUnit);
        ipList.addHeaderView(header);
        ipList.setAdapter(ipListAdapter);
        createProgressDialog();
    }

    private Dialog createProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Downloading data..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true);
        progressDialog.setOnDismissListener(this);
        return progressDialog;
    }

    public static String getDataDir(final Context context) throws Exception {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.dataDir;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.refresh_ip_list :
                downloadFile = new DownloadFile(this, progressDialog, button, ipListAdapter);
                downloadFile.execute(SITE_LINK);
                break;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        if(downloadFile.cancel(true))
            button.setEnabled(true);
    }
}
