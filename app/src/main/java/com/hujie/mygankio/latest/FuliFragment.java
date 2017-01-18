package com.hujie.mygankio.latest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hujie.mygankio.adapter.FuliAdapter;
import com.hujie.mygankio.adapter.MyRecyclerViewAdapter;
import com.hujie.mygankio.base.BaseFuliFragment;
import com.hujie.mygankio.base.BaseListFragment;
import com.hujie.mygankio.javabean.ResultsBean;
import com.hujie.mygankio.net.IConfig;
import com.hujie.mygankio.ui.ContentActivity;
import com.hujie.mygankio.ui.PresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujie on 2017/1/13.
 */

public class FuliFragment extends BaseFuliFragment implements IConfig.IView{

    private ArrayList<ResultsBean> mData=new ArrayList<>();
    private IConfig.IPresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PresenterImpl(this,getContext(),"福利");
    }


    @Override
    public void onPull(List<ResultsBean> data) {
        mData.clear();
        mData.addAll(data);
    }

    @Override
    public void onDrag(List<ResultsBean> data) {
        mData.addAll(data);
    }

    @Override
    public void loadFinish() {
        loadfinish();
    }

    @Override
    protected RecyclerView.Adapter getAdapter(final RecyclerView mRecycleView) {
        FuliAdapter adapter = new FuliAdapter(getContext(), mData);

        return adapter;
    }

    @Override
    protected void loadData() {
        presenter.pull();
    }

    @Override
    protected void addData() {
        presenter.drag();
    }
}
