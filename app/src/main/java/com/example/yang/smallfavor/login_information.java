package com.example.yang.smallfavor;

/**
 * Created by Yang on 2016/12/20.
 */
public class login_information {
    String information;
    public static class login{
        String account = "";
        String password = "";
        public boolean isfull(){
            if(account.equals("") || password.equals(""))
                return false;
            return true;
        }
    }
    public static class register{
        String account = "";
        String password = "";
        String phone = "";
        String email = "";
        String nickname = "";
        int sex = -1;
        public boolean isfull(){
            if(account.equals("") || password.equals("") || phone.equals("") || email.equals("") || nickname.equals("") || (sex==-1))
                return false;
            return true;
        }
        public boolean isValid(){
            if(account.length()<5 || password.length()<5){
                return false;
            }
            return true;
        }
    }
    public static class account{
        String account = "";
        String phone = "";
        String email = "";
        String nickname = "";
        int sex = -1;
        int money = 0;
        int intelligence_task = 0;
        int labor_task = 0;
        double rate = 0;
    }

}
