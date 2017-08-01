package com.list.asus.weather2;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.list.asus.weather2.Adapter.FragAdapter;
import com.list.asus.weather2.fragment.ViewPagersFragments;
import com.list.asus.weather2.util.HttpUtil;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


public class MainActivity extends FragmentActivity {


    private ViewPager viewPager;
    private ImageView backgroundPicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArray();
        //防止第一次没有数据，设置一个默认数据
        if (C.cityNameArray.isEmpty()) {
            C.add(C.cityNameArray,"北京");
        }
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
        SharedPreferences prefs = getDefaultSharedPreferences(this);
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
                finish();
            }
        });

    }
    //初始化ViewPager，动态加载Fragment
    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(4);  //设置ViewPager的预加载数量为4，防止快速滑动造成的bug
        List<Fragment> fragList = new ArrayList<Fragment>();
        for (String arrayList : C.cityNameArray) {
            ViewPagersFragments frag = new ViewPagersFragments();
            frag.setWeatherID(arrayList);
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
                        .getDefaultSharedPreferences(MainActivity.this)
                        .edit();
                editor.putString("background_pic",backgroundPic);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //------------------------------读取选择过的城市---------------------------------------
    //读取数据
    public  void getArray() {
        SharedPreferences prefs = getSharedPreferences("choosedCityArray",MODE_PRIVATE);
        try {
            JSONArray jsonArray = new JSONArray(prefs.getString("choosedCityArray", null));
            if (jsonArray != null){
                for (int i = 0; i < jsonArray.length(); i++) {
                    C.add(C.cityNameArray,jsonArray.getString(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
