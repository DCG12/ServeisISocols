package com.company;

import jdk.nashorn.internal.runtime.Debug;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.StringTokenizer;

public class SocketServidor {

    public static void main(String[] args) {

        try{
            System.out.println("creando servidor");

            ServerSocket serverSocket = new ServerSocket();

            System.out.println("realizando el bind (.3vinculo)");

            InetSocketAddress addr = new InetSocketAddress("localhost", 9090);
            serverSocket.bind(addr);

            System.out.println("aceptando conexiones");

            while(true) {

                Socket newsocket = serverSocket.accept();

                HiloPeticion hp = new HiloPeticion(newsocket);
                hp.start();
                System.out.println("Conexion recibida");
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        newsocket.getInputStream()));
                PrintWriter out = new PrintWriter(newsocket.getOutputStream());

                String str = ".";
                while (!str.equals("")) {
                    str = in.readLine();

                    if(str.contains("GET")){

                    out.println("HTTP/1.0 200 OK");
                    out.println("Content-Type: text/html");
                    out.println("Server: Bot");

                    out.println("");
                    InetAddress id = newsocket.getInetAddress();
                    int aux = newsocket.getSoLinger();
                    System.out.println("LOG: " + str);
                    out.println("<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "<meta charset=\"UTF-8\">\n" +
                            "<title>Servidor David Casas</title>\n" +
                            "<h2>IP: " + id + " </h2>\n" +
                            "<b>Esto es un servidor WEB!</b>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "</body>\n" +
                            "</html>");
                    out.flush();
                    newsocket.close();}
                    else {
                        out.println("HTTP/1.0 200 OK");
                        out.println("Content-Type: text/html");
                        out.println("Server: Bot");

                        out.println("");
                        InetAddress id = newsocket.getInetAddress();
                        int aux = newsocket.getSoLinger();
                        System.out.println("Aux: " + str);
                        out.println("<!DOCTYPE html>\n" +
                                "<html lang=\"en\">\n" +
                                "<head>\n" +
                                "<meta charset=\"UTF-8\">\n" +
                                "<title>Servidor David Casas</title>\n" +
                                "<b>La petición no es GET, porfavor vuelva a intentarlo.</b>\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "</body>\n" +
                                "</html>");
                        out.flush();
                        newsocket.close();
                    }
                }
            }
            } catch (IOException e1) {
          e1.printStackTrace();
        }


        }

        }




