package edu.andr.xyzyx.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by asus on 2018/4/29.
 */

public class ActionBean {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String intruducion;
    private Date starttime,deadlinetime;
    private int emergency;
    private String cate;
    private transient SimpleDateFormat format1 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    public String getIntruducion() {
        return intruducion;
    }

    public void setIntruducion(String intruducion) {
        this.intruducion = intruducion;
    }

    public Date getStarttime() {
        return starttime;
    }
    public String getStarttimeToString(){
        String s =format1.format(starttime);
        return s;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public void setStarttimeFromString(String starttime) throws ParseException {
        this.starttime = format1.parse(starttime);
    }
    public Date getDeadlinetime() {
        return deadlinetime;
    }

    public void setDeadlinetime(Date deadlinetime) {
        this.deadlinetime = deadlinetime;
    }
    public String getDeadlinetimeToString() {
        return format1.format(deadlinetime);
    }

    public void setDeadlinetimeFromString(String deadlinetime) throws ParseException {
        this.deadlinetime = format1.parse(deadlinetime);
    }
    public int getEmergency() {
        return emergency;
    }

    public void setEmergency(int emergency) {
        this.emergency = emergency;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public ActionBean(String intruducion, String starttime, String deadlinetime, int emergency, String cate) throws ParseException {
        this.intruducion = intruducion;
        format1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        this.starttime = format1.parse(starttime);
        this.deadlinetime = format1.parse(deadlinetime);
        this.emergency = emergency;
        this.cate = cate;
    }

    public ActionBean() {
        format1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }
}
