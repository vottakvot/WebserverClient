package com.example.user.webserverclient;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUnit implements Parcelable{

    private static final SimpleDateFormat formatterSQLDateTime = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

    public static final String ID = "id";
    public static final String ROUTER_IP = "routerIP";
    public static final String HOST_IP = "hostIP";
    public static final String DATE_IN = "dateIn";

    private String id;
    private String routerIP;
    private String hostIP;
    private String dateIn;

    public DataUnit(String id, String routerIP, String hostIP, String dateIn){
        this.id = id;
        this.routerIP = routerIP;
        this.hostIP = hostIP;
        this.dateIn = dateIn;
    }

    public DataUnit(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);
        id = data[0];
        routerIP = data[1];
        hostIP = data[2];
        dateIn = data[3];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRouterIP() {
        return routerIP;
    }

    public void setRouterIP(String routerIP) {
        this.routerIP = routerIP;
    }

    public String getHostIP() {
        return hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    public String getDateIn() {
        return dateIn;
    }

    public Date getDateInParse() throws ParseException {
        return formatterSQLDateTime.parse(dateIn);
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {id, routerIP, hostIP, dateIn});
    }

    public static final Parcelable.Creator<DataUnit> CREATOR = new Parcelable.Creator<DataUnit>() {

        @Override
        public DataUnit createFromParcel(Parcel source) {
            return new DataUnit(source);
        }

        @Override
        public DataUnit[] newArray(int size) {
            return new DataUnit[size];
        }
    };
}
