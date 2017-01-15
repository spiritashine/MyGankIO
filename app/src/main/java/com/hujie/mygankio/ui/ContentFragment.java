package com.hujie.mygankio.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.google.gson.Gson;
import com.hujie.mygankio.R;
import com.hujie.mygankio.adapter.MyRecyclerViewAdapter;
import com.hujie.mygankio.javabean.ResultsBean;
import com.hujie.mygankio.utils.IApi;
import com.hujie.mygankio.utils.NetUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
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
    private int typeIndex;
    private int page=1;
    static String[] types={"all","休息视频","Android" ,"iOS" ,"拓展资源" ,"前端","瞎推荐"};

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
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        lastVisibleItem+1 == mAdapter.getItemCount()){
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

        //recyclerView item的点击事件
        mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                //得到点击条目的位置
                int position = mRecyclerView.getChildAdapterPosition(view);
                //通过位置得到url
                String url = data.get(position).getUrl();
                //跳转加载WebView
                Intent intent = new Intent(getContext(),ContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url",url);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //下拉刷新
    private void loadData(){
        Call<ResponseBody> call = mIApi.listAll(types[typeIndex], 10, 1);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mRefreshLayout.setRefreshing(false);

                try {
                    JSONArray jsonArray=new JSONObject(response.body().string()).getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        String string = jsonArray.getString(i);
                        data.add(mGson.fromJson(string, ResultsBean.class));
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    //上拉加载
    private void addData(){
        Call<ResponseBody> call = mIApi.listAll(types[typeIndex], 10, ++page);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mRefreshLayout.setRefreshing(false);

                try {
                    JSONArray jsonArray=new JSONObject(response.body().string()).getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        String string = jsonArray.getString(i);
                        data.add(mGson.fromJson(string, ResultsBean.class));
                    }

                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
