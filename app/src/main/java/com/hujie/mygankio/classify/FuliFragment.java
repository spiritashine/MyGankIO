package com.hujie.mygankio.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.hujie.mygankio.base.BaseFuliFragment;
import com.hujie.mygankio.classify.mvp.IClassifyConstraint;
import com.hujie.mygankio.classify.mvp.PresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujie on 2017/1/13.
 */

public class FuliFragment extends BaseFuliFragment implements IClassifyConstraint.IView{

    private ArrayList<ResultsBean> mData=new ArrayList<>();
    private IClassifyConstraint.IPresenter presenter;


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
