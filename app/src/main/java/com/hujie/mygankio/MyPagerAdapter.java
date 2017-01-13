package com.hujie.mygankio;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by hujie on 2017/1/13.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private ArrayList<ContentFragment> fragments;
    private String[] titles;

    public MyPagerAdapter(FragmentManager fm, Context context, ArrayList<ContentFragment> fragments, String[] titles) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
