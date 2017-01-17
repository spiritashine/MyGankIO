package com.hujie.mygankio.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;


import com.hujie.mygankio.R;
import com.hujie.mygankio.adapter.MyRecyclerViewAdapter;
import com.hujie.mygankio.ui.ContentActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by hujie on 2017/1/16.
 */

public abstract class BaseListFragment extends LazyFragment {

    @BindView(R.id.recycle_a)
    RecyclerView mRecycleView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.emptyView)
    ViewStub emptyView;

    protected RecyclerView.Adapter mAdapter;

    /**
     * 设置显示的Adapter
     *
     * @return RecycleView 显示的Adapter
     */
    protected abstract RecyclerView.Adapter getAdapter(RecyclerView mRecycleView);

    /**
     * 下拉刷新，加载数据
     */
    protected abstract void loadData();

    /**
     * 上拉加载，加载数据
     */
    protected abstract void addData();

    /**
     * 加载完成，取消加载状态
     */
    public void loadfinish() {
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onInitView(View v) {
        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_red_light),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_blue_light)
        );

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecycleView.addItemDecoration(decoration);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = getAdapter(mRecycleView);
        if (mAdapter != null)
            mRecycleView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });


        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    //滑动到底部，加载新的数据
                    if (!mSwipeRefreshLayout.isRefreshing()) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        addData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    int last = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    if (last != -1) {
                        lastVisibleItem = last;
                    }
                }


            }
        });
        mSwipeRefreshLayout.setRefreshing(true);
        loadData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.recyclerview;
    }

    @Override
    protected void onInitData() {

    }
}
