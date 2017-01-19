package com.hujie.mygankio.latest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujie.mygankio.R;
import com.hujie.mygankio.adapter.ClassifyPagerAdapter;
import com.hujie.mygankio.adapter.NewPagerAdapter;
import com.hujie.mygankio.base.BaseFragment;
import com.hujie.mygankio.ui.ContentFragment;
import com.hujie.mygankio.ui.RotateTransformer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hujie on 2017/1/19.
 */

public class NewFragment extends BaseFragment {
    @BindView(R.id.viewpager_new)
    ViewPager viewpagerNew;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_new;
    }

    @Override
    protected void onInitView(View view, Bundle savedInstanceState) {
        ArrayList<Fragment> fragments=new ArrayList<>();

        for (int i=0;i<7;i++){
            fragments.add(NewContentFragment.getInsatance(i));
        }

        viewpagerNew.setOffscreenPageLimit(fragments.size());

        viewpagerNew.setPageTransformer(true,new RotateTransformer());

        viewpagerNew.setAdapter(new NewPagerAdapter(getFragmentManager(),getContext(),fragments));
    }

    @Override
    protected void onInitData() {

    }

}
