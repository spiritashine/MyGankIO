package com.hujie.mygankio.latest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.hujie.mygankio.base.BaseNewFragment;
import com.hujie.mygankio.javabean.NewHeaderBean;
import com.hujie.mygankio.net.IConfig;
import com.hujie.mygankio.ui.ContentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujie on 2017/1/17.
 */

public class NewContentFragment extends BaseNewFragment implements IConfig.INewView{
    List<NewHeaderBean> header=new ArrayList<>();
    private int index;
    private IConfig.INewPresenter presenter;

    public static Fragment getInsatance(int i){
        NewContentFragment fragment = new NewContentFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("index",i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewPresenterImpl(this,getContext());
        index=getArguments().getInt("index");
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return null;
    }

    @Override
    public String getLargeTitle() {
        String title = header.get(index).getTitle();
        return title;
    }

    @Override
    public String getImageUrl() {
        String url = (header.get(index).getContent().substring(20).split("jpg"))[0];
        return url;
    }

    @Override
    protected void loadData() {
        presenter.pull();
    }

    @Override
    public void onPull(List<NewHeaderBean> data) {
        header.clear();
        header.addAll(data);
    }


    @Override
    public void addView() {

    }

    @Override
    public void loadFinish() {

    }


}
