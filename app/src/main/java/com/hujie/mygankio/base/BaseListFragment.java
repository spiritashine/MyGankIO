package com.hujie.mygankio.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujie.mygankio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hujie on 2017/1/16.
 */

public abstract class BaseListFragment extends LazyFragment {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.statusView)
    StatusViewLayout statusView;

    private RecyclerView.Adapter mAdapter;

    /**
     * 设置显示的Adapter
     * 将RecyclerView传过去，用于点击事件获取childItemPosition
     *
     * @return RecycleView 显示的Adapter
     */
    protected abstract RecyclerView.Adapter getAdapter(RecyclerView recycler);

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
        refresh.setRefreshing(false);
    }

    protected boolean enableRefresh(){
        return true;
    }

    protected boolean isAddItemDecoration(){
        return true;
    }

    @Override
    protected void onInitView(View v) {
        refresh.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_red_light),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_blue_light)
        );
        if (isAddItemDecoration()){
            DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
            recycler.addItemDecoration(decoration);
        }
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = getAdapter(recycler);
        if (mAdapter != null)
            recycler.setAdapter(mAdapter);

        //如果不能刷新，那么关闭刷新，加载数据
        if (!enableRefresh()){
            refresh.setEnabled(false);
            loadData();
            return;
        }

            refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });


        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    //滑动到底部，加载新的数据
                    if (!refresh.isRefreshing()) {
                        refresh.setRefreshing(true);
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

        //预加载
        refresh.setRefreshing(true);
        loadData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.recyclerview;
    }

    @Override
    protected void onInitData() {

    }

    public void showLoading(){
        statusView.showLoading();
    }

    public void showContent(){
        statusView.showContent();
    }

    public void showError(String msg){
        statusView.showError();
    }

}
