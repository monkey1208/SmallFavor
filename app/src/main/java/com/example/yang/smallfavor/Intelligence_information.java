package com.example.yang.smallfavor;


import android.media.Image;

public class Intelligence_information {
    public String title;
    public int price;
    public int postID;
    public String ID;
    public Image heart;
    public int popularity;
    public String department;
    public String content;

    public Intelligence_information(String title, int price, String ID, int popularity, String department, int postID, String content){
        this.title = title;
        this.price = price;
        this.ID = ID;
        this.popularity = popularity;
        this.department = department;
        this.postID = postID;
        this.content = content;
    }
}
