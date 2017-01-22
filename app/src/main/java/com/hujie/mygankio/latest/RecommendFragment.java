package com.hujie.mygankio.latest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hujie.mygankio.R;
import com.hujie.mygankio.base.BaseFragment;
import com.hujie.mygankio.base.StatusViewLayout;
import com.hujie.mygankio.latest.mvp.RecommendConstraint;
import com.hujie.mygankio.latest.mvp.RecommendPresenterImpl;
import com.hujie.mygankio.classify.RotateTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class RecommendFragment extends BaseFragment implements RecommendConstraint.RecommendView {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.statusView)
    StatusViewLayout statusView;

    public RecommendFragment() {
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void onInitView(View view, Bundle savedInstanceState) {

        viewPager.setPageTransformer(true, new RotateTransformer());
    }

    @Override
    protected void onInitData() {
        RecommendConstraint.RecommendPresenter presenter =
                new RecommendPresenterImpl(this);
        presenter.loadTab(5);
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
        List<RecommendTabBean> tabBeans = (List<RecommendTabBean>) data;
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        for (int i = 0; i < tabBeans.size(); i++) {
            RecommendTabBean tabBean = tabBeans.get(i);
            fragments.add(RecommendItemFragment
                    .getInstance(tabBean.getTitle(), tabBean.getPublishedAt()));
        }
        RecommendFragmentAdapter adapter = new RecommendFragmentAdapter(
                getChildFragmentManager(), fragments);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());
        tabLayout.setupWithViewPager(viewPager);
    }

}
