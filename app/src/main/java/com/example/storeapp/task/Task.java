package com.example.storeapp.task;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Task extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;

    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/api/login");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");


            //url post에 json 형식으로 id, pw 같이 보냄
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            sendMsg = "name="+strings[0]+"&pw="+strings[1];
            osw.write(sendMsg);
            osw.flush();


            if (conn.getResponseCode() == conn.HTTP_OK) {
//                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
//                    BufferedReader reader = new BufferedReader(tmp);
//                    StringBuffer buffer = new StringBuffer();
//                    while((str = reader.readLine())  != null) {
//                        buffer.append(str);
//                    }
                //receiveMsg = buffer.toString();
                receiveMsg = "OK";
            } else {
                Log.i("login", conn.getResponseCode()+"에러");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}