package com.hujie.mygankio;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by hujie on 2017/1/13.
 */

public class ContentFragment extends LazyFragment {
    private SwipeRefreshLayout refreshLayout;
    private static ContentFragment instance;

    public static ContentFragment getInsatance(){
        if (instance==null){
            synchronized (ContentFragment.class){
                if (instance==null){
                    instance=new ContentFragment();
                }
            }
        }
        return instance;
    }

    @Override
    protected void init(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_a);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),1));
        ArrayList<String> data=new ArrayList<>();
        for (int i=0;i<100;i++){
            data.add("おねえさん"+i);
        }
        final MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshLayout.isRefreshing()){
                            refreshLayout.setRefreshing(false);
                        }
                    }
                },2000);

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview;
    }
}
