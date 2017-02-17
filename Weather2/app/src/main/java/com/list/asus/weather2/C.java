package com.list.asus.weather2;

import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class C {

    public static ArrayList<String> cityNameArry = new ArrayList<>();

    public static void add(ArrayList<String> arrayList, String string){
        int i=0;
        for (String a: arrayList){
            if (! a.equals(string)){
                i++;
            }
            Log.d(TAG, "add: "+string+a);
        }
        if (arrayList.size() == i){
            arrayList.add(string);
        }
    }
}
