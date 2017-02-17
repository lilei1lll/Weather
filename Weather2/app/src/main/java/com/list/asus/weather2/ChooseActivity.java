package com.list.asus.weather2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.list.asus.weather2.Adapter.ChooseActivityRecyclerViewAdapter;

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
            }
        });

        /*
        *从C中初始化ChoosedList
        */
        ChoosedList = C.cityNameArry;

        Log.d("TAG", "ChoosedActivity: "+ChoosedList);

        RecyclerView ChooseRecyclerVew = (RecyclerView)
                findViewById(R.id.choose_activity_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ChooseRecyclerVew.setLayoutManager(layoutManager);
        ChooseActivityRecyclerViewAdapter adapter = new ChooseActivityRecyclerViewAdapter(ChoosedList);
        ChooseRecyclerVew.setAdapter(adapter);

        /*Button delete = (Button) findViewById(R.id.choose_activity_delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C.cityNameArry.remove(v);
            }
        });*/

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChooseActivity.class);
        context.startActivity(intent);
    }

}
