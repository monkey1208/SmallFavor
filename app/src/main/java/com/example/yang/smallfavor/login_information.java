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
    }


}
