package com.example.user.webserverclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

class DownloadFile extends AsyncTask<String, Integer, String> {

    private Context context = null;
    private ProgressDialog progressDialog = null;
    private Button button = null;
    private IpListAdapter ipListAdapter = null;

    public DownloadFile(Context context, ProgressDialog progressDialog, Button button, IpListAdapter ipListAdapter){
        this.context = context;
        this.progressDialog = progressDialog;
        this.button = button;
        this.ipListAdapter = ipListAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
        button.setEnabled(false);
    }

    @Override
    protected String doInBackground(String... downloadLinks) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        InputStream inputStream = null;
        String resultJson = "";

        try {
                URL url = new URL(downloadLinks[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                inputStream = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                    publishProgress(i++);
                }

                resultJson = buffer.toString();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(reader != null){
                    reader.close();
                }

                if(inputStream != null){
                    inputStream.close();
                }

                if(urlConnection != null){
                    urlConnection.disconnect();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return resultJson;
    }

    protected void onProgressUpdate(Integer... progress) {
        progressDialog.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONArray dataJsonObj = new JSONArray(result);
            ArrayList<DataUnit> arrayDataUnit = new ArrayList<>();
            for (int i = 0; i < dataJsonObj.length(); i++) {
                JSONObject row = dataJsonObj.getJSONObject(i);
                DataUnit dataUnit = new DataUnit(row.getString(DataUnit.ROUTER_IP), row.getString(DataUnit.HOST_IP));
                dataUnit.setId(row.getString(DataUnit.ID));
                dataUnit.setDateIn(row.getString(DataUnit.DATE_IN));
                arrayDataUnit.add(dataUnit);
            }
            ipListAdapter.setNewDataForAdapter(arrayDataUnit);
        } catch(Exception e){
            e.printStackTrace();
        }

        progressDialog.hide();
        button.setEnabled(true);
        Toast.makeText(context, "Download complite!", Toast.LENGTH_LONG);
    }
}