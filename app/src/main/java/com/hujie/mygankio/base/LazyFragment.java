package com.hujie.mygankio.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * 懒加载
 * Created by hujie on 2017/1/16.
 */

public abstract class LazyFragment extends BaseFragment {

    protected boolean isCreated; //是否创建
    protected boolean isInited;  //是否初始化过
    protected boolean needInit;  //是否需要初始化


    //在onCreateView()之前执行
    //判断当前Fragment是否显示
    //isVisibleToUser  true  显示   false  不显示
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser && !isInited){
            if(isCreated){
                init();
            }else {
                needInit=true;
            }
        }
    }


    @Override
    protected void onInitView(View view,Bundle savedInstanceState) {
        if(needInit){
            init();
        }
        isCreated=true;
    }


    private void init(){
        onInitView(mRootView);
        isInited=true;
    }

    protected abstract void onInitView(View v);


}
