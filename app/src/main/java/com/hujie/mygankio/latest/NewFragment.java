package com.hujie.mygankio.latest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujie.mygankio.R;
import com.hujie.mygankio.adapter.MyPagerAdapter;
import com.hujie.mygankio.adapter.NewPagerAdapter;
import com.hujie.mygankio.base.BaseFragment;
import com.hujie.mygankio.ui.RotateTransformer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hujie on 2017/1/17.
 */

public class NewFragment extends BaseFragment {

    @BindView(R.id.new_viewpager)
    ViewPager newViewpager;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_viewpager;
    }

    @Override
    protected void onInitView(View view, Bundle savedInstanceState) {
        ArrayList<Fragment> fragments=new ArrayList<>();

        for (int i=0;i<5;i++){
            fragments.add(NewContentFragment.getInsatance(i));
        }

        newViewpager.setOffscreenPageLimit(fragments.size());

        newViewpager.setHorizontalScrollBarEnabled(true);

        newViewpager.setPageTransformer(true,new RotateTransformer());

        newViewpager.setAdapter(new NewPagerAdapter(getFragmentManager(),getContext(),fragments));
    }

    @Override
    protected void onInitData() {

    }

    @Override
    protected void onInitPreData(View view, Bundle savedInstanceState) {
        super.onInitPreData(view, savedInstanceState);
    }
}
