package edu.andr.xyzyx.myutil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import edu.andr.xyzyx.Bean.ActionBean;
import edu.andr.xyzyx.Bean.ConstantArgument;

/**
 * Created by asus on 2018/4/29.
 */

public class DBDao implements ConstantArgument{
    private SQLiteDatabase db;
    public DBDao(Context context){
        DBConnect dbConnect=new DBConnect(context);
        db=dbConnect.getReadableDatabase();
    }
    public List<ActionBean> getall(){
        List<ActionBean> list=new ArrayList<>();
        String[] colmnus={"","","","",""};
        Cursor c=db.query(TABLE_NAME,colmnus,null,null,null,null,null);
        if(c.getCount()>0){
            while (c.moveToNext()){
                ActionBean actionBean= null;
                try {
                    actionBean = new ActionBean(
                            c.getString(c.getColumnIndex("intruducion")),
                            c.getString(c.getColumnIndex("starttime")),
                            c.getString(c.getColumnIndex("deadlinetime")),
                            c.getInt(c.getColumnIndex("emergency")),
                            c.getString(c.getColumnIndex("cate")));
                    actionBean.setId(c.getInt(c.getColumnIndex("Id")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                list.add(actionBean);
            }
            c.close();
        }
        return list;
    }
    public boolean insertinfo(ActionBean actionBean){
        boolean flag=false;
        ContentValues values=new ContentValues();
        values.put("intruducion",actionBean.getIntruducion());
        values.put("starttime",actionBean.getStarttimeToString());
        values.put("deadlinetime",actionBean.getDeadlinetimeToString());
        values.put("emergency",actionBean.getEmergency());
        values.put("cate",actionBean.getCate());
        if(db.insert(TABLE_NAME,null,values)>0)
            flag=true;
        return flag;
    }
    public boolean deleteinfo(ActionBean actionBean){
        boolean flag=false;
        if(db.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(actionBean.getId())})>0)
            flag=true;
        return flag;
    }
    /*
    * @param actionBean 选取的是新的内容
    * */
    public boolean updateinfo(ActionBean actionBean){
        boolean flag=false;
        ContentValues values=new ContentValues();
        values.put("intruducion",actionBean.getIntruducion());
        values.put("starttime",actionBean.getStarttimeToString());
        values.put("deadlinetime",actionBean.getDeadlinetimeToString());
        values.put("emergency",actionBean.getEmergency());
        values.put("cate",actionBean.getCate());
        if(db.update(TABLE_NAME,values,"id=?",new String[]{String.valueOf(actionBean.getId())})>0)
            flag=true;
        return flag;
    }
    public void updateinfolist(List<ActionBean> list){
        ContentValues values=new ContentValues();
        for (ActionBean actionBean:list) {
            values.put("intruducion",actionBean.getIntruducion());
            values.put("starttime",actionBean.getStarttimeToString());
            values.put("deadlinetime",actionBean.getDeadlinetimeToString());
            values.put("emergency",actionBean.getEmergency());
            values.put("cate",actionBean.getCate());
            db.update(TABLE_NAME,values,"id=?",new String[]{String.valueOf(actionBean.getId())});
            values.clear();
        }
    }
}
