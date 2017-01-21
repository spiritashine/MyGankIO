package com.hujie.mygankio.latest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujie.mygankio.R;
import com.hujie.mygankio.adapter.NewPagerAdapter;
import com.hujie.mygankio.base.BaseFragment;
import com.hujie.mygankio.base.StatusViewLayout;
import com.hujie.mygankio.javabean.HistoryBean;
import com.hujie.mygankio.latest.mvp.NewConstraint;
import com.hujie.mygankio.latest.mvp.NewPresenterImpl;
import com.hujie.mygankio.ui.RotateTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hujie on 2017/1/19.
 */

public class NewFragment extends BaseFragment implements NewConstraint.INewView {


    @BindView(R.id.tablayout_new)
    TabLayout tablayoutNew;
    @BindView(R.id.viewpager_new)
    ViewPager viewpagerNew;
    @BindView(R.id.statusview)
    StatusViewLayout statusView;

    public NewFragment() {
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_new;
    }

    @Override
    protected void onInitView(View view, Bundle savedInstanceState) {
        //添加viewPager翻页旋转动画
        viewpagerNew.setPageTransformer(true, new RotateTransformer());
    }

    @Override
    protected void onInitData() {
        NewConstraint.INewPresenter presenter = new NewPresenterImpl(this);
        presenter.onLoadTitle(5);
    }

    @Override
    public void showLoading() {
        statusView.showLoading();
    }

    @Override
    public void showContent() {
        statusView.showContent();
    }

    @Override
    public void showError(String msg) {
        statusView.showError(msg);
    }

    @Override
    public void fillData(Object data) {
        List<HistoryBean> historyBeans = (List<HistoryBean>) data;
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < historyBeans.size(); i++) {
            HistoryBean historyBean = historyBeans.get(i);
            fragments.add(NewContentFragment.getInstance
                    (historyBean.getTitle(), historyBean.getPublishedAt()));
        }
        NewPagerAdapter adapter = new NewPagerAdapter(getChildFragmentManager(), fragments);
        viewpagerNew.setAdapter(adapter);
        viewpagerNew.setOffscreenPageLimit(historyBeans.size());
        tablayoutNew.setupWithViewPager(viewpagerNew);

    }

}
