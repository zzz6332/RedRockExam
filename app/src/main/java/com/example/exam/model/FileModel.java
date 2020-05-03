package com.example.exam.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileModel {
    private File mFile ;
    public void downloadFile(File file,String url1){
        mFile = new File(file,url1);
        HttpURLConnection connection;
        try {
            URL url = new URL(url1);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.connect();
            InputStream inputStream = new FileInputStream(mFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
