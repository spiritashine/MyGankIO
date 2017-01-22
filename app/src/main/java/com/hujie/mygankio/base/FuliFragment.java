package com.hujie.mygankio.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.hujie.mygankio.classify.ResultsBean;
import com.hujie.mygankio.classify.mvp.IClassifyConstraint;
import com.hujie.mygankio.classify.mvp.PresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujie on 2017/1/13.
 */

public class FuliFragment extends FuliListFragment implements IClassifyConstraint.IView{

    private ArrayList<ResultsBean> mData=new ArrayList<>();
    private IClassifyConstraint.IPresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PresenterImpl(this,getContext(),"福利");
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
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
        mStatusView.showContent();
    }

    @Override
    public void loadError(String msg) {
        mStatusView.showError(msg);
    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void showContent() {
        super.showContent();
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
    }
}
