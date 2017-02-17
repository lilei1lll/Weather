package com.list.asus.weather2;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.list.asus.weather2.Adapter.FragAdapter;
import com.list.asus.weather2.fragment.Fragments;
import com.list.asus.weather2.util.HttpUtil;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MainActivity extends FragmentActivity {


    private ViewPager viewPager;
    private ImageView backgroundPicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //对API进行判断
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);

        initViewPager();

        //背景图片初始化
        backgroundPicImg = (ImageView) findViewById(R.id.background_pic);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String backgroundPic = prefs.getString("background_pic", null);
        if (backgroundPic != null){
            Glide.with(this).load(backgroundPic).into(backgroundPicImg);
        } else {
            loadBackgroundPic();
        }

        //FloatingActionButton的点击监听事件
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.add_floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseActivity.actionStart(MainActivity.this);
            }
        });

    }

    private void initViewPager() {

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        List<Fragment> fragList = new ArrayList<Fragment>();
        for (int i=1; i<4;i++) {
            Fragments frag = new Fragments();
            fragList.add(frag);
        }
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(),
                fragList);
        viewPager.setAdapter(adapter);
    }

    //加载背景图片
    public void loadBackgroundPic() {
        String requestBackgroundPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBackgroundPic, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String backgroundPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(MainActivity.this).edit();
                editor.putString("backgroundPic", backgroundPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(MainActivity.this).load(backgroundPic).into(backgroundPicImg);
                    }
                });
            }
        });
    }


//------------------------------读取，保存选择过的城市---------------------------------------
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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        try {
            JSONArray jsonArray = new JSONArray(prefs.getString("choosedCityArray", "[]"));
            if (jsonArray != null){
                ArrayList<String> resArray = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    resArray.add(jsonArray.getString(i));
                }
                return resArray;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
