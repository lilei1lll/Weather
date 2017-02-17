package com.list.asus.weather2.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by HP on 2017/2/16.
 */

public class FragAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> mFragmentList;
    public FragAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int arg0) {
        return mFragmentList.get(arg0);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}
