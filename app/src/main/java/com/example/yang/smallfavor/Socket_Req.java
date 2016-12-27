package com.example.yang.smallfavor;

import android.widget.Toast;

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
    private String requestCode = "REQ"; //REQ or ADD
    public int returnCode = 0;
    private int commandcode = 0;
    public Myobjects objects;
    public String myaccount;
    public Labor_information labor_information = null;
    public Socket_Req(String requestCode,String command, String myaccount, Labor_information labor_information){
        this.commandcode = command2code(command);
        objects = new Myobjects();
        this.myaccount = myaccount;
        this.requestCode = requestCode;
        this.labor_information = labor_information;
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
                socket.connect(new InetSocketAddress(inetAddress, PORT), 2000);
                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());
                String gson_string;
                String response;
                dos.writeUTF(requestCode);
                response = dis.readUTF();
                //if (response.equals("OK"))
                if(requestCode.equals("REQ") || requestCode.equals("AC")) {
                    dos.writeUTF(myaccount);
                    response = dis.readUTF();
                }
                //if(response.equals("OK"))
                if(requestCode.equals("AC")) {
                    send_accept(dis, dos);
                }else if(requestCode.equals("DEL")){
                    send_deletion(dis, dos);
                }
                else{
                    dos.writeUTF(code2command(commandcode));
                    boolean flag = true;
                    if (commandcode == 0 || commandcode == 5) {
                        if (requestCode.equals("REQ"))
                            flag = request_main(dis, dos);
                        else if (requestCode.equals("ADD")) {
                            flag = send_content(dis, dos);
                        }
                    } else if (commandcode == 1 || commandcode == 2 || commandcode == 6 || commandcode == 7) {
                        if (requestCode.equals("REQ"))
                            flag = request_list(dis, dos);
                        else if (requestCode.equals("ADD")) {
                            flag = send_content(dis, dos);
                        }
                    } else if (commandcode == 4) {
                        if (requestCode.equals("REQ"))
                            flag = request_content(dis, dos);
                        else if (requestCode.equals("ADD")) {
                            flag = send_content(dis, dos);
                        }
                    }
                    if (flag) {
                        returnCode = 1;
                    } else {
                        returnCode = 0;
                    }
                }
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
    private boolean send_accept(DataInputStream dis, DataOutputStream dos) throws IOException {
        dos.writeUTF(Integer.toString(labor_information.post_ID));
        String res = dis.readUTF();
        return true;
    }
    private boolean send_deletion(DataInputStream dis, DataOutputStream dos) throws IOException {
        dos.writeUTF(Integer.toString(labor_information.post_ID));
        dis.readUTF();
        if(labor_information.state == -1){
            dos.writeUTF("giveup");
        }else if(labor_information.state == 1){
            dos.writeUTF("success");
        }else{
            dos.writeUTF("regret");
        }
        String res = dis.readUTF();
        System.out.println(res);
        return true;
    }
    private boolean request_content(DataInputStream dis, DataOutputStream dos) throws IOException {
        String res;
        res = dis.readUTF();
        dos.writeUTF(Integer.toString(labor_information.post_ID));
        res = dis.readUTF();
        gson2object(res);
        return true;
    }
    private boolean send_content(DataInputStream dis, DataOutputStream dos) throws IOException {
        String res;
        res = dis.readUTF();
        Gson gson = new Gson();
        String gson_string = gson.toJson(labor_information);
        dos.writeUTF(gson_string);
        res = dis.readUTF();
        if(res.equals("ADD_FAIL")){
            return false;
        }
        gson = null;
        return true;
    }
    private boolean request_main(DataInputStream dis, DataOutputStream dos) throws IOException {
        String gson_string;
        gson_string = dis.readUTF();
        gson2object(gson_string);
        System.out.println("gson string = "+gson_string);
        return true;
    }
    private boolean request_list(DataInputStream dis, DataOutputStream dos) throws IOException {
        String gson_string;
        gson_string = dis.readUTF();
        gson2object(gson_string);
        System.out.println("gson string = "+gson_string);
        return true;
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
            case 6:
                return "task";
            case 7:
                return "task2do";
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
            case "task":
                return 6;
            case "task2do":
                return 7;
            default:
                return -1;
        }
    }
    public class Myobjects{
        login_information.account account_info = null;
        List<Labor_information> labor_information_list = null;
        Labor_information labor_information = null;
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
                objects.labor_information = gson.fromJson(gson_string, Labor_information.class);
                break;
            case 5:
                objects.account_info = gson.fromJson(gson_string, login_information.account.class);
                break;
            case 6:
                objects.labor_information_list = gson.fromJson(gson_string, new TypeToken<List<Labor_information>>(){}.getType());
                break;
            case 7:
                objects.labor_information_list = gson.fromJson(gson_string, new TypeToken<List<Labor_information>>(){}.getType());
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
                return objects.labor_information;
            case 5:
                return objects.account_info;
            case 6:
                return objects.labor_information_list;
            case 7:
                return objects.labor_information_list;
            default:
                return null;
        }
    }
}
