package com.example.yang.smallfavor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2016/12/26.
 */
public class Socket_Req {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    //public final String IP = "140.112.30.36";
    //public final String IP = "10.5.5.29";
    public final String IP = "10.5.5.36";
    public final int PORT = 5120;
    private final String requestCode = "REQ";
    public int returnCode = 0;
    private int commandcode = 0;
    public Myobjects objects;
    public String myaccount;
    public Socket_Req(String command, String myaccount){
        this.commandcode = command2code(command);
        objects = new Myobjects();
        this.myaccount = myaccount;
    }
    public int runSocket(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Connection());
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnCode;
    }
    class Connection implements Runnable{

        @Override
        public void run() {
            try {
                InetAddress inetAddress = InetAddress.getByName(IP);
                socket = new Socket();
                socket.connect(new InetSocketAddress(inetAddress, PORT), 5500);
                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());
                String gson_string;
                String response;
                dos.writeUTF(requestCode);
                response = dis.readUTF();
                //if (response.equals("OK"))
                dos.writeUTF(myaccount);
                response = dis.readUTF();
                //if(response.equals("OK"))
                dos.writeUTF(code2command(commandcode));
                if(commandcode == 0 || commandcode == 1 || commandcode == 2 ||commandcode == 5){
                    main_and_list(dis, dos);
                }
                returnCode = 1;
                dos.writeUTF("END");
            } catch (UnknownHostException e) {
                returnCode = -1;
                System.out.println("unknown host");
                e.printStackTrace();
            } catch (IOException e) {
                returnCode = -1;
                System.out.println("timeout");
                e.printStackTrace();
            }
        }
    }
    private void main_and_list(DataInputStream dis, DataOutputStream dos) throws IOException {
        String gson_string;
        gson_string = dis.readUTF();
        gson2object(gson_string);
        System.out.println(gson_string);
    }
    public String code2command(int code){
        switch(code){
            case 0:
                return "main";
            case 1:
                return "intelligence";
            case 2:
                return "labor";
            case 3:
                return "intelligence_content";
            case 4:
                return "labor_content";
            case 5:
                return "account";
            default:
                return "none";
        }
    }
    private int command2code(String command){
        switch(command){
            case "main":
                return 0;
            case "intelligence":
                return 1;
            case "labor":
                return 2;
            case "intelligence_content":
                return 3;
            case "labor_content":
                return 4;
            case "account":
                return 5;
            default:
                return -1;
        }
    }
    public class Myobjects{
        login_information.account account_info = null;
        List<Labor_information> labor_information_list = null;
    }
    private void gson2object(String gson_string){
        Gson gson = new Gson();
        switch(commandcode){
            case 0:
                objects.account_info = gson.fromJson(gson_string, login_information.account.class);
                System.out.println("account: "+objects.account_info.account);
                break;
            case 1:
                break;
            case 2:
                objects.labor_information_list = gson.fromJson(gson_string, new TypeToken<List<Labor_information>>(){}.getType());
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                objects.account_info = gson.fromJson(gson_string, login_information.account.class);
                break;
            default:
                break;
        }
    }
    public Object getobject(){
        switch (commandcode){
            case 0:
                return objects.account_info;
            case 1:
                return null;
            case 2:
                return objects.labor_information_list;
            case 3:
                return null;
            case 4:
                return null;
            case 5:
                return objects.account_info;
            default:
                return null;
        }
    }
}
