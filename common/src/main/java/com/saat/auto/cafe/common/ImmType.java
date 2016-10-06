package com.saat.auto.cafe.common;

/**
 * Created by micahcoletti on 10/6/16.
 */
public enum  ImmType {
    Header("header"),
    Gallery("gallery");

    String name;

    ImmType(String name){
        this.name = name;
    }

   public String getName(){
        return this.name;
    }
}
