package com.example.user.webserverclient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUnit {

    private static final SimpleDateFormat formatterSQLDateTime = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

    public static final String ID = "id";
    public static final String ROUTER_IP = "routerIP";
    public static final String HOST_IP = "hostIP";
    public static final String DATE_IN = "dateIn";

    public DataUnit(String routerIP, String hostIP){
        this.routerIP = routerIP;
        this.hostIP = hostIP;
    }

    private String id;
    private String routerIP;
    private String hostIP;
    private String dateIn;

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
}
