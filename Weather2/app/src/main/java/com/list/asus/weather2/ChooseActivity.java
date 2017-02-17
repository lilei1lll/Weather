package com.list.asus.weather2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

public class ChooseActivity extends AppCompatActivity {

    private ArrayList<String> ChoosedList = new ArrayList<>();
    private  DrawerLayout drawerLayout;
    private TextView addSlipText;
    private TextView backText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        addSlipText = (TextView) findViewById(R.id.add_slip_textview);
        backText = (TextView) findViewById(R.id.back_text);
        //侧滑提示
        addSlipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });

        //返回键
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /*
        *从C中初始化ChoosedList
        */
        ChoosedList = C.cityNameArry;
        saveArray(C.cityNameArry);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ChooseActivity.this, android.R.layout.simple_list_item_1, ChoosedList);
        ListView listView =(ListView) findViewById(R.id.choose_activity_liet_view);
        listView.setAdapter(adapter);

        //listview长按监听
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view,
                                           final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChooseActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确定删除");
                builder.setNegativeButton("确认",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                C.cityNameArry.remove(position);
                                Log.d("remove","arry"+C.cityNameArry);
                                ChooseActivity.actionStart(ChooseActivity.this);
                            }
                        });
                builder.setPositiveButton("取消", null);
                builder.create().show();
                return true;
            }
        });


    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChooseActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public  void saveArray(ArrayList<String> StringArray) {
        JSONArray jsonArray = new JSONArray();
        for (String b : StringArray) {
            jsonArray.put(b);
        }
        SharedPreferences.Editor editor =
                getSharedPreferences("choosedCityArray",MODE_PRIVATE).edit();
        editor.putString("choosedCityArray",jsonArray.toString());
        editor.apply();
    }
}
