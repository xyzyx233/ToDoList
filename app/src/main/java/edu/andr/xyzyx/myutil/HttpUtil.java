package edu.andr.xyzyx.myutil;

import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import edu.andr.xyzyx.Bean.ActionBean;
import edu.andr.xyzyx.activities.MainActivity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by asus on 2018/4/29.
 */

public class HttpUtil {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;
    private Gson gson=new Gson();
    public  HttpUtil(){
        client = new OkHttpClient();
    }
    public void sendObjPost(String methods,ActionBean actionBean){
        String json=gson.toJson(actionBean);
        Inbackgroud inbackgroud=new Inbackgroud();
        inbackgroud.execute(methods,json);
    }
    private String Post(String url,String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
    private String Get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
    class Inbackgroud extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute () {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute (String s){
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate (String...values){
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground (String...strings){
            if(strings[0].equals("GET")){

            }
            if(strings[0].equals("POST")){

            }
            return null;
        }
    }

}
