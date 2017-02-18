package com.list.asus.weather2;

import java.util.ArrayList;


public class C {

    public static ArrayList<String> cityNameArry = new ArrayList<>();

    public static void add(ArrayList<String> arrayList, String string){
        int i=0;
        for (String a: arrayList){
            if (! a.equals(string)){
                i++;
            }
        }
        if (arrayList.size() == i){
            arrayList.add(string);
        }
    }
    //用于判断是否存在
    public static boolean Judge(ArrayList<String> arrayList, String string) {
        int i=0;
        for (String a: arrayList){
            if ( a.equals(string)){
                return true;
            }
        }
        return false;
    }
}
