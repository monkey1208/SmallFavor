package com.example.yang.smallfavor;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2016/12/22.
 */
public class Socket_LR {
    private Thread thread;
    private Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;
    private DataInputStream dis;
    private DataOutputStream dos;
    private login_information.login login_package;
    private login_information.register register_package;
    int flag = -1;
    String returnCode = "ncode";
    //public final String IP = "140.112.30.33";
    public final String IP = "10.5.5.29";
    public final int PORT = 5120;
    public Socket_LR(login_information.login login_package, login_information.register register_package){
        if(login_package != null){
            this.login_package = login_package;
            flag = 0;
        }else if(register_package != null){
            this.register_package = register_package;
            flag = 1;
        }else{

        }
    }
    public void runSocket(){
        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
        //thread = new Thread(Connection);
        threadExecutor.execute(new Connection());
        System.out.println("runsocket");
        //thread.start();
        threadExecutor.shutdown();
        try {
            threadExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    class Connection implements Runnable{
        @Override
        public void run() {
            // TODO Auto-generated method stub
                try {
                    InetAddress serverIp = InetAddress.getByName(IP);
                    int serverPort = PORT;
                    System.out.println("before socket");
                    socket = new Socket(serverIp, serverPort);
                    dos = new DataOutputStream(socket.getOutputStream());
                    dis = new DataInputStream(socket.getInputStream());
                    Gson gson = new Gson();
                    String info = null;
                    if(flag == 1) {
                        info = gson.toJson(register_package);
                        System.out.println("before write");
                        dos.writeUTF("REG");
                    }else if(flag == 0){
                        info = gson.toJson(login_package);
                        dos.writeUTF("LOG");
                    }
                    String res_msg = null;
                    res_msg = dis.readUTF();
                    dos.writeUTF(info);
                    dos.flush();
                    res_msg = dis.readUTF();
                    dos.writeUTF("END");
                    dos.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
