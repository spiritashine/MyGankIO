package com.hujie.mygankio.latest;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.hujie.mygankio.base.BaseListFragment;
import com.hujie.mygankio.latest.mvp.RecommendConstraint;
import com.hujie.mygankio.latest.mvp.RecommendPresenterImpl;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by hujie on 2017/1/21.
 */

public class RecommendItemFragment extends BaseListFragment implements RecommendConstraint.RecommendView {

    private ArrayList<ItemType> data = new ArrayList<>();


    public RecommendItemFragment() {
    }


    public static RecommendItemFragment getInstance(String title, String data) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("date", data);
        RecommendItemFragment fragment = new RecommendItemFragment();
        fragment.setArguments(args);
        return fragment;
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

    @Override
    public void fillData(Object reeult) {
        data.addAll((Collection<? extends ItemType>) reeult);
        loadfinish();
    }

    @Override
    protected RecyclerView.Adapter getAdapter(RecyclerView mRecycleView) {
        return new RecommmandAdapter(getContext(), data);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }

    @Override
    protected boolean isAddItemDecoration() {
        return false;
    }

    @Override
    protected void loadData() {

        Bundle args = getArguments();
        String title = args.getString("title");
        String date = args.getString("date");

        RecommendConstraint.RecommendPresenter presenter = new
                RecommendPresenterImpl(this);
        presenter.loadContent(date, title);
    }

    @Override
    protected void addData() {

    }
}
