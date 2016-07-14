/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.webCrawler.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author kaushik
 */
public class Grabber {
    
    public static HttpURLConnection getConnection(String link) throws IOException{
        URL url = new URL(link);
        return (HttpURLConnection) url.openConnection();
    }
    
    public static String getContent(InputStream iStream) throws IOException{
        BufferedReader buffer = new BufferedReader(new InputStreamReader(iStream));
        String line = "";
        StringBuilder builder = new StringBuilder();
        while((line = buffer.readLine()) != null){
            builder.append(line);
        }
        buffer.close();
        return builder.toString();
    }
    public static String post(String link, String params) throws IOException{
        HttpURLConnection conn = getConnection(link);
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(params.getBytes());
        
        return getContent(conn.getInputStream());
    }
    
    public static String get(String link) throws IOException{
        HttpURLConnection conn = getConnection(link);
        String finalContent = getContent(conn.getInputStream());
        return finalContent;
    }
    
    public static int getImage(String link, String name, int counter) throws IOException{
        HttpURLConnection conn = getConnection(link);
        InputStream iStream = conn.getInputStream();
        
        
        File dir = new File("/Users/kaushik/test/models/"+name);
        if(!dir.exists()){
            dir.mkdirs();
        }
        byte[] data = new byte[1024];
        int i = 0;
        try{
            FileOutputStream os = new FileOutputStream("/Users/kaushik/test/models/"+name+"/name"+counter+".png");
            while((i = iStream.read(data)) != -1){
                os.write(data, 0, i);
            }
            os.close();
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
        
        return 1;
    }
}
