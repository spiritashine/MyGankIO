package com.hujie.mygankio.classify;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hujie.mygankio.R;
import com.hujie.mygankio.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by hujie on 2017/1/17.
 */

public class ClassifyFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager_classify)
    ViewPager viewpagerClassify;
    String[] titles={"ALL","休息视频","ANDROID","IOS","拓展资源","前端","瞎推荐"};

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void onInitView(View view, Bundle savedInstanceState) {
        ArrayList<Fragment> fragments=new ArrayList<>();

        for (int i=0;i<7;i++){
            fragments.add(ContentFragment.getInsatance(i));
        }

        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tablayout.setTabTextColors(Color.GRAY,Color.BLACK);

        tablayout.setSelectedTabIndicatorColor(Color.CYAN);

        viewpagerClassify.setOffscreenPageLimit(fragments.size());

        viewpagerClassify.setAdapter(new ClassifyPagerAdapter(getFragmentManager(),getContext(),fragments,titles));

        tablayout.setupWithViewPager(viewpagerClassify);
    }

    @Override
    protected void onInitData() {

    }

    @Override
    protected void onInitPreData(View view, Bundle savedInstanceState) {
        super.onInitPreData(view, savedInstanceState);
    }
}
