package com.hujie.mygankio;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hujie on 2017/1/13.
 */

public abstract class LazyFragment extends Fragment {

    private boolean isCreated; //是否创建
    private boolean isInited;  //是否初始化过
    private boolean needInit;  //是否需要初始化

    protected View root;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root=  inflater.inflate(getLayoutId(),container,false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(needInit){
            init();
        }
        isCreated=true;
    }

    private void init(){
        init(root);
        isInited=true;
    }



    protected abstract void init(View view);
    @LayoutRes
    protected abstract int getLayoutId();
}
