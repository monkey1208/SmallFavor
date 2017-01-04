package com.example.yang.smallfavor;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2016/12/22.
 */
public class Socket_LR {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private login_information.login login_package;
    private login_information.register register_package;
    int flag = -1;
    public int returnCode = -2;
    //public final String IP = "140.112.30.36";
    //public final String IP = "10.5.5.29";
    public final String IP = "10.5.5.36";
    //public final String IP = "140.112.73.35";
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
    public int runSocket(){
        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
        threadExecutor.execute(new Connection());
        System.out.println("runsocket");
        threadExecutor.shutdown();
        try {
            threadExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnCode;

    }
    class Connection implements Runnable{
        @Override
        public void run() {
            // TODO Auto-generated method stub
                try {
                    InetAddress serverIp = InetAddress.getByName(IP);
                    int serverPort = PORT;
                    System.out.println("before socket");
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(serverIp,serverPort), 5500);
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
                    if(res_msg.equals("REG_FAIL")){
                        returnCode = 0;
                    }else if(res_msg.equals("REG_SUCCESS")||res_msg.equals("LOGIN_SUCCESS")) {
                        returnCode = 1;
                    }else if(res_msg.equals("LOGIN_NO_ACCOUNT")){
                        returnCode = 0;
                    }else if(res_msg.equals("LOGIN_WRONG_PWD")){
                        returnCode = 2;
                    }
                    dos.writeUTF("END");
                    dos.flush();
                } catch (IOException e) {
                    returnCode = -1;
                    e.printStackTrace();
                } catch (Exception e) {
                    returnCode = -1;
                    e.printStackTrace();
                } finally {
                    try {
                        if(socket!=null) {
                            socket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
