package com.hujie.mygankio.latest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujie.mygankio.R;
import com.hujie.mygankio.adapter.NewPagerAdapter;
import com.hujie.mygankio.javabean.BaseReslut;
import com.hujie.mygankio.javabean.HistoryBean;
import com.hujie.mygankio.net.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hujie on 2017/1/19.
 */

public class NewFragment extends Fragment {
    @BindView(R.id.tablayout_new)
    TabLayout tablayoutNew;
    @BindView(R.id.viewpager_new)
    ViewPager viewpagerNew;
    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        dialog = new ProgressDialog(getContext());
        dialog.show();

        Call<BaseReslut<List<HistoryBean>>> call = NetUtils.getInstance().getApi().getTab(5);
        call.enqueue(new Callback<BaseReslut<List<HistoryBean>>>() {
            @Override
            public void onResponse(Call<BaseReslut<List<HistoryBean>>> call, Response<BaseReslut<List<HistoryBean>>> response) {
                dialog.dismiss();
                BaseReslut<List<HistoryBean>> reslut = response.body();
                if (!reslut.isError()){
                    List<HistoryBean> results = reslut.getResults();
                    ArrayList<Fragment> fragments=new ArrayList<>();
                    for (int i=0;i<5;i++){
                        HistoryBean historyBean = results.get(i);
                        fragments.add(NewContentFragment.getInsatance(historyBean.getTitle(),historyBean.getPublishedAt()));
                    }
                    NewPagerAdapter newPagerAdapter = new NewPagerAdapter(getChildFragmentManager(),fragments);
                    viewpagerNew.setAdapter(newPagerAdapter);
                    tablayoutNew.setupWithViewPager(viewpagerNew);
                }
            }

            @Override
            public void onFailure(Call<BaseReslut<List<HistoryBean>>> call, Throwable t) {

            }
        });

    }

}
