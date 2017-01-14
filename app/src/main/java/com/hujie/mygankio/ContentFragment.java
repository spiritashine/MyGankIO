package com.hujie.mygankio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hujie on 2017/1/13.
 */

public class ContentFragment extends LazyFragment {
    private IApi mIApi = NetUtils.GetInstance().GetApi();
    private Gson mGson=new Gson();
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private ArrayList<ResultsBean> data=new ArrayList<>();
    private static int typeIndex;
    static String[] types={"all","休息视频","Android" ,"iOS" ,"拓展资源" ,"前端","瞎推荐"};

    public static Fragment getInsatance(int i){
        ContentFragment fragment = new ContentFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("type",i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init(View view) {
        typeIndex=getArguments().getInt("type");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_a);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),1));
        mAdapter = new MyRecyclerViewAdapter(getContext(), data);
        mRecyclerView.setAdapter(mAdapter);
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        //通过观察者自动加载
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRefreshLayout.setRefreshing(true);
                loadData();
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
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&
                        lastVisibleItem==mAdapter.getItemCount()){
                    if (!mRefreshLayout.isRefreshing()){
                        mRefreshLayout.setRefreshing(true);
                        addData();
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
    }

    private void loadData(){
        Call<BaseBean<List<ResultsBean>>> call = mIApi.listAll(types[typeIndex], 10, 1);
        call.enqueue(new Callback<BaseBean<List<ResultsBean>>>() {
            @Override
            public void onResponse(Call<BaseBean<List<ResultsBean>>> call, Response<BaseBean<List<ResultsBean>>> response) {
                mRefreshLayout.setRefreshing(false);

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){
                        String string = jsonArray.getString(i);
                        data.add(mGson.fromJson(string,ResultsBean.class));
                    }

                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BaseBean<List<ResultsBean>>> call, Throwable t) {
                mRefreshLayout.setRefreshing(false);

            }
        });

    }

    private void addData(){
        int page=1;
        Call<BaseBean<List<ResultsBean>>> call = mIApi.listAll(types[typeIndex], 10, ++page);
        call.enqueue(new Callback<BaseBean<List<ResultsBean>>>() {
            @Override
            public void onResponse(Call<BaseBean<List<ResultsBean>>> call, Response<BaseBean<List<ResultsBean>>> response) {
                mRefreshLayout.setRefreshing(false);

                if (response==null){
                    Toast.makeText(getContext(),"no more data",Toast.LENGTH_LONG).show();
                }else {

                    try {
                        JSONArray jsonArray = new JSONArray(response);


                        for (int i = 0; i < jsonArray.length(); i++) {
                            String string = jsonArray.getString(i);
                            data.add(mGson.fromJson(string, ResultsBean.class));
                        }

                        mAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseBean<List<ResultsBean>>> call, Throwable t) {
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview;
    }
}
