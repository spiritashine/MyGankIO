package com.hujie.mygankio.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.google.gson.Gson;
import com.hujie.mygankio.R;
import com.hujie.mygankio.adapter.MyRecyclerViewAdapter;
import com.hujie.mygankio.base.LazyFragment;
import com.hujie.mygankio.net.IConfig;
import com.hujie.mygankio.javabean.ResultsBean;
import com.hujie.mygankio.net.IApi;
import com.hujie.mygankio.net.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujie on 2017/1/13.
 */

public class ContentFragment extends LazyFragment implements IConfig.IView{

    private IApi mIApi = NetUtils.getInstance().getApi();
    private Gson mGson=new Gson();
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private ArrayList<ResultsBean> mData=new ArrayList<>();
    private int typeIndex;
    private int page=1;
    static String[] types={"all","休息视频","Android" ,"iOS" ,"拓展资源" ,"前端","瞎推荐"};
    ListFragment listFragment;
    ListActivity listActivity;
    public static Fragment getInsatance(int i){
        ContentFragment fragment = new ContentFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("type",i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview;
    }

    @Override
    protected void init(View view) {
        typeIndex=getArguments().getInt("type");
        final IConfig.IPresenter presenter=new PresenterImpl(this,getContext(),types[typeIndex]);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_a);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),1));
        mAdapter = new MyRecyclerViewAdapter(getContext(), mData);
        mRecyclerView.setAdapter(mAdapter);
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.pull();
            }
        });

        //通过观察者自动加载
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //在布局加载完成后，再加载数据
                mRefreshLayout.setRefreshing(true);
                presenter.pull();
                //取消观察者
                root.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        //上拉加载
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //滑动到底部
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        lastVisibleItem+1 == mAdapter.getItemCount()){
                    if (!mRefreshLayout.isRefreshing()){
                        mRefreshLayout.setRefreshing(true);
                        presenter.drag();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager){
                    int last=((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
                    if (last!=-1){
                        lastVisibleItem=last;
                    }
                }
            }
        });

        //recyclerView item的点击事件
        mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                //得到点击条目的位置
                int position = mRecyclerView.getChildAdapterPosition(view);
                //通过位置得到url
                String url = mData.get(position).getUrl();
                //跳转加载WebView
                Intent intent = new Intent(getContext(),ContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url",url);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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
        loadFinish();
    }
}
