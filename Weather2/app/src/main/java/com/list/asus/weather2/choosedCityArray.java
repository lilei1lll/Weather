package com.list.asus.weather2;

import android.app.Activity;
import android.content.SharedPreferences;
import org.json.JSONArray;
import java.util.ArrayList;

public class choosedCityArray extends Activity{

    //存储数据
    public  void saveArray(ArrayList<String> StringArray) {
        SharedPreferences prefs = getSharedPreferences("choosedCityArray", MODE_PRIVATE);
        JSONArray jsonArray = new JSONArray();
        for (String b : StringArray) {
            jsonArray.put(b);
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("choosedCityArray",jsonArray.toString());
        editor.commit();
    }

    //读取数据
    public  ArrayList<String> getArray() {
        SharedPreferences prefs = getSharedPreferences("choosedCityArray", MODE_PRIVATE);
        try {
            JSONArray jsonArray = new JSONArray(prefs.getString("choosedCityArray", "[]"));
            ArrayList<String> resArray = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                resArray.add(jsonArray.getString(i));
            }
            return resArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
