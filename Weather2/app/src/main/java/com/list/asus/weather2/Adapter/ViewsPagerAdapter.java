package com.list.asus.weather2.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by HP on 2017/2/16.
 */

public class ViewsPagerAdapter extends PagerAdapter{

    private List<View> pageList;

    public ViewsPagerAdapter(List<View> pageList) {
        this.pageList = pageList;
    }

    @Override  //初始化指定位置的页面，并且返回当前页面的本身
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pageList.get(position));  //获取指定位置的控件
        return pageList.get(position);
    }

    @Override  //返回要展示的页面数量
    public int getCount() {
        return pageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override  //移除指定位置的页面
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pageList.get(position));
    }

}
