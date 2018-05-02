package edu.andr.xyzyx.myutil;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import edu.andr.xyzyx.Bean.ActionBean;

/**
 * Created by asus on 2018/4/29.
 */

public class GetinfofromXML {
    private static XmlPullParser pullParser= Xml.newPullParser();
    public GetinfofromXML(InputStream xml) throws Exception {
        pullParser.setInput(xml,"utf-8");
    }
    public List<ActionBean> getinfo()throws Exception{
        int event= pullParser.getEventType();
        List list=null;
        ActionBean actionBean=null;
        while(event!=XmlPullParser.END_DOCUMENT){
            String nodeName=pullParser.getName();
            switch (event){
                case XmlPullParser.START_DOCUMENT:
                    list=new ArrayList();
                    break;
                case XmlPullParser.START_TAG: // 标签开始
                    if ("ActionBean".equals(nodeName))
                    {
                        int id = Integer.valueOf(pullParser.getAttributeValue(0));
                        actionBean=new ActionBean();
                        actionBean.setEmergency(id);
                    }
                    if ("intruducion".equals(nodeName))
                    {
                        String intruducion = pullParser.nextText();
                        actionBean.setIntruducion(intruducion);
                    }
                    if ("cate".equals(nodeName))
                    {
                        String cate = pullParser.nextText();
                        actionBean.setCate(cate);
                    }
                    if ("starttime".equals(nodeName))
                    {
                        String starttime = pullParser.nextText();
                        actionBean.setStarttimeFromString(starttime);
                    }
                    if ("deadlinetime".equals(nodeName))
                    {
                        String deadlinetime = pullParser.nextText();
                        actionBean.setDeadlinetimeFromString(deadlinetime);
                    }
                    break;
                case XmlPullParser.END_TAG: // 标签结束
                    if ("person".equals(nodeName))
                    {
                        list.add(actionBean);
                        actionBean=null;
                    }
                    break;
            }
            event = pullParser.next(); // 下一个标签
        }
        return list;
    }
    public void setinfo(List<ActionBean> list, OutputStream os)throws Exception{
        InputStream i=null;
        XmlSerializer xs = Xml.newSerializer();
        xs.setOutput(os, "UTF-8");
        xs.startDocument("UTF-8", true);
        xs.startTag(null, "people");
        for (ActionBean p : list)
        {
            xs.startTag(null, "ActionBean");
            xs.attribute(null, "Emergency", String.valueOf(p.getEmergency()));

            xs.startTag(null, "intruducion");
            xs.text(p.getIntruducion());
            xs.endTag(null, "intruducion");

            xs.startTag(null, "cate");
            xs.text(p.getCate().toString());
            xs.endTag(null, "cate");

            xs.startTag(null, "starttime");
            xs.text(p.getStarttimeToString().toString());
            xs.endTag(null, "starttime");

            xs.startTag(null, "deadlinetime");
            xs.text(p.getDeadlinetimeToString().toString());
            xs.endTag(null, "deadlinetime");

            xs.endTag(null, "ActionBean");
        }
        xs.endTag(null, "people");
        xs.endDocument();
        os.flush();
        os.close();
    }
}
