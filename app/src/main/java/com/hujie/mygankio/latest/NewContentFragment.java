package com.hujie.mygankio.latest;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hujie.mygankio.R;
import com.hujie.mygankio.base.LazyFragment;
import com.hujie.mygankio.javabean.ResultsBean;
import com.hujie.mygankio.net.IConfig;

import java.util.List;

import butterknife.BindView;

/**
 * Created by hujie on 2017/1/17.
 */

public class NewContentFragment extends LazyFragment implements IConfig.INewView{


    @BindView(R.id.largetitle_new)
    TextView largetitleNew;
    @BindView(R.id.image_new)
    ImageView imageNew;
    @BindView(R.id.container_new)
    LinearLayout containerNew;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    @Override
    protected void onInitView(View v) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_new;
    }

    @Override
    protected void onInitData() {

    }

    @Override
    public void onPull(List<ResultsBean> data) {

    }

    @Override
    public void loadFinish() {

    }
}
