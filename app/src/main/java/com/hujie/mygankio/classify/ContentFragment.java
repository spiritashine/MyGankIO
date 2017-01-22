package com.hujie.mygankio.classify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hujie.mygankio.base.BaseListFragment;
import com.hujie.mygankio.classify.mvp.IClassifyConstraint;
import com.hujie.mygankio.classify.mvp.PresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujie on 2017/1/13.
 */

public class ContentFragment extends BaseListFragment implements IClassifyConstraint.IView{

    private ArrayList<ResultsBean> mData=new ArrayList<>();
    private int typeIndex;
    static String[] types={"all","休息视频","Android" ,"iOS" ,"拓展资源" ,"前端","瞎推荐"};
    private IClassifyConstraint.IPresenter presenter;

    public static Fragment getInstance(int i){
        ContentFragment fragment = new ContentFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("type",i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeIndex=getArguments().getInt("type");
        presenter = new PresenterImpl(this,getContext(),types[typeIndex]);
    }




    @Override
    protected RecyclerView.Adapter getAdapter(final RecyclerView mRecycleView) {
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getContext(), mData);
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                int position = mRecycleView.getChildAdapterPosition(view);
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


}
