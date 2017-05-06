package com.nevil.meizi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Tangkun on 2017/5/4.
 */

public class MainFragmentPageAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragmentList;
    String[] titles;

    public MainFragmentPageAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles) {
        super(fm);
        this.mFragmentList = fragmentList;
        this.titles = titles;
    }

    public void setNewData(List<Fragment> fragments, String[] titles) {
        mFragmentList.clear();
        mFragmentList.addAll(fragments);
        this.titles = titles;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
