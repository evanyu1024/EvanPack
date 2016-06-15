package com.evan.databindingdemo;

/**
 * Created by evanyu on 16/6/13.
 */
public class MyStringUtils {

    public static String getText(int num) {
        switch (num) {
            case 1:
                return "1111111";
            case 2:
                return "2222222";
            case 3:
                return "3333333";
            case 18:
                return "1888888";
            default:
                return "default";
        }
    }

}
