package com.hujie.mygankio.latest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujie.mygankio.R;
import com.hujie.mygankio.javabean.BaseReslut;
import com.hujie.mygankio.javabean.NewBean;
import com.hujie.mygankio.javabean.NewItemBean;
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

public class NewContentFragment extends Fragment {

    @BindView(R.id.recycle_a_new)
    RecyclerView recycleANew;

    public static Fragment getInsatance(String title, String date) {
        NewContentFragment fragment = new NewContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("date", date);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_content, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        final String title = arguments.getString("title");
        String date = arguments.getString("date");

        date= TextUtils.substring(date, 0, 10);
        date=date.replace("-","/");

        Call<BaseReslut<NewBean>> call = NetUtils.getInstance().getApi().getNew(date);
        call.enqueue(new Callback<BaseReslut<NewBean>>() {
            @Override
            public void onResponse(Call<BaseReslut<NewBean>> call, Response<BaseReslut<NewBean>> response) {
                ArrayList<ItemType> data=new ArrayList<>();
                ItemType titleType = new ItemType(ItemType.TYPE_TITILE,title);
                data.add(titleType);

                BaseReslut<NewBean> results = response.body();
                NewBean newBean = results.getResults();
                List<NewBean.福利Bean> 福利 = newBean.get福利();
                ItemType imageType = new ItemType(ItemType.TYPE_IMAGE,福利.get(0));
                data.add(imageType);

                List<NewBean.AndroidBean> androidBeen = newBean.getAndroid();


            }

            @Override
            public void onFailure(Call<BaseReslut<NewBean>> call, Throwable t) {

            }
        });
    }
}
