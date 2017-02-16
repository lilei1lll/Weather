package com.list.asus.weather2;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.list.asus.weather2.Adapter.ChooseActivityRecyclerViewAdapter;
import com.list.asus.weather2.MyCache.ACache;
import com.list.asus.weather2.db.ChooseActivityDatail;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivity extends AppCompatActivity {

    private List<ChooseActivityDatail> ChoosedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        TextView addSlipText = (TextView) findViewById(R.id.add_slip_textview);

        /*
        *从内存中初始化ChoosedList
        *键值：
        *   所在地：location
        *   其他地方：
        *          cityId0
        *          cityId1
        *          cityId2
        *
         */
        ACache mCache = ACache.get(this);
        String LocationCity = mCache.getAsString("location");
        for ( int i = 0;i < 1 ; i++){

        }

        RecyclerView ChooseRecyclerVew = (RecyclerView)
                findViewById(R.id.choose_activity_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ChooseRecyclerVew.setLayoutManager(layoutManager);
        ChooseActivityRecyclerViewAdapter adapter = new ChooseActivityRecyclerViewAdapter(ChoosedList);
        ChooseRecyclerVew.setAdapter(adapter);

        //侧滑提示
        addSlipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChooseActivity.class);
        context.startActivity(intent);
    }


}
