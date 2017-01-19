package com.hujie.mygankio.latest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.hujie.mygankio.base.LazyFragment;

/**
 * Created by hujie on 2017/1/19.
 */

public class NewContentFragment extends LazyFragment{

    public static Fragment getInsatance(int i){
        NewContentFragment fragment = new NewContentFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("index",i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onInitView(View v) {

    }

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    protected void onInitData() {

    }
}
