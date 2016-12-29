package com.example.yang.smallfavor;

/**
 * Created by Yang on 2016/12/25.
 */
public class Labor_information {
    public String title;
    public int price;
    public String ID;
    public int post_ID;
    public int state;
    public String content;
    public String accepter;
    public double rate;
    public Labor_information(String title, int price, String ID, int post_ID, int state, String content){
        this.title = title;
        this.price = price;
        this.ID = ID;
        this.post_ID = post_ID;
        this.state = state;
        this.content = content;
        this.accepter = "";
        this.rate = 0;
    }
}
