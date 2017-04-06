package com.company;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InterfaceAddress;
import java.net.Socket;

public class HiloPeticion extends Thread {

    private Socket newsocket;

    public HiloPeticion(Socket e){
        this.newsocket = e;
    }

    @Override
    public void run() {

        InputStream is = null;

        try {

            is = newsocket.getInputStream();
            OutputStream os = newsocket.getOutputStream();

            byte[] n1 = new byte[is.available()];
            is.read(n1);

            String mes = new String(n1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

