package com.example.yang.smallfavor;

import android.graphics.Color;
import android.widget.TextView;

/**
 * Created by Yang on 2016/12/29.
 */

public class ColorDefiner {
    public void SetColor(double rate, TextView state){
        if (rate == 1) {
            state.setTextColor(Color.rgb(255, 215, 0));
        } else if (rate >= 0.9 && rate < 1) {
            state.setTextColor(Color.rgb(255, 255, 0));
        } else if (rate >= 0.7 && rate < 0.9) {
            state.setTextColor(Color.rgb(135, 206, 250)); //blue
        } else if (rate >= 0.5 && rate < 0.7) {
            state.setTextColor(Color.rgb(135, 206, 250));
        } else if (rate >= 0.25 && rate < 0.5) {
            state.setTextColor(Color.rgb(190, 190, 190));
        } else if (rate < 0.25  && rate >= 0) {
            state.setTextColor(Color.rgb(255, 0, 0));
        } else {
            state.setTextColor(Color.rgb(255, 255, 255));
        }
    }
    public String AddText(double rate){
        if (rate == 1) {
            return " 金色!";
        } else if (rate >= 0.9 && rate < 1) {
            return " 黃色!";
        } else if (rate >= 0.7 && rate < 0.9) {
            return " 藍色!";
        } else if (rate >= 0.5 && rate < 0.7) {
            return " 綠色!";
        } else if (rate >= 0.25 && rate < 0.5) {
            return " 灰色!";
        } else if (rate < 0.25  && rate >= 0) {
            return " 紅色!你個混蛋";
        } else {
            return " 新手白!";
        }
    }
}
