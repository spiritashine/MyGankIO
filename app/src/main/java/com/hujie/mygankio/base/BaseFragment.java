package com.hujie.mygankio.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by hujie on 2017/1/16.
 */

public abstract class BaseFragment extends Fragment {
    protected View mRootView;

    @LayoutRes
    protected abstract int getLayoutResourse();

    protected abstract void onInitView(View view, Bundle savedInstanceState);

    protected abstract void onInitData();

    protected void onInitPreData(View view,Bundle savedInstanceState){}

    protected boolean isNeedInitEventBus(){ return false;}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isNeedInitEventBus()){
            EventBus.getDefault().register(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getLayoutResourse()!=0){
            mRootView=inflater.inflate(getLayoutResourse(),container,false);
        }else {
            mRootView=super.onCreateView(inflater,container,savedInstanceState);
        }

        ButterKnife.bind(this,mRootView);

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onInitView(view,savedInstanceState);

        onInitPreData(view,savedInstanceState);

        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onInitData();
                mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
