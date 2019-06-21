package com.example.daywanandroid.model.bean.smartbean;

import java.util.ArrayList;

public class SmartRecrdBean {
    private ArrayList<String> Stringss;

    public ArrayList<String> getStringss() {
        return Stringss;
    }

    public void setStringss(ArrayList<String> stringss) {
        Stringss = stringss;
    }

    public SmartRecrdBean(ArrayList<String> stringss) {
        Stringss = stringss;
    }

    @Override
    public String toString() {
        return "SmartRecrdBean{" +
                "Stringss=" + Stringss +
                '}';
    }
}
