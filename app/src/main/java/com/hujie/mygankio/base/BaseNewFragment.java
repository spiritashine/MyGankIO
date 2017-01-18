package com.hujie.mygankio.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hujie.mygankio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hujie on 2017/1/18.
 */

public abstract class BaseNewFragment extends LazyFragment {
    @BindView(R.id.largetitle_new)
    TextView largetitleNew;
    @BindView(R.id.image_new)
    ImageView imageNew;
    @BindView(R.id.container_new)
    LinearLayout containerNew;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    protected RecyclerView.Adapter mAdapter;


    protected abstract RecyclerView.Adapter getAdapter();

    protected abstract String getLargeTitle();

    protected abstract String getImageUrl();

    protected abstract void loadData();

    @Override
    protected void onInitView(View v) {
        largetitleNew.setText(getLargeTitle());
        Glide.with(getContext()).load(getImageUrl()).into(imageNew);
        loadData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_new;
    }

    @Override
    protected void onInitData() {

    }

}
