package com.hujie.mygankio.latest;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.hujie.mygankio.base.BaseListFragment;
import com.hujie.mygankio.latest.mvp.NewConstraint;
import com.hujie.mygankio.latest.mvp.NewPresenterImpl;

import java.util.ArrayList;

/**
 * Created by hujie on 2017/1/19.
 */

public class NewContentFragment extends BaseListFragment implements NewConstraint.INewView{
    private ArrayList<ItemType> data=new ArrayList<>();

    public static NewContentFragment getInstance(String title,String data){
        Bundle bundle=new Bundle();
        bundle.putString("title",title);
        bundle.putString("data",data);
        NewContentFragment fragment = new NewContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected RecyclerView.Adapter getAdapter(RecyclerView recycler) {
        // TODO: 2017/1/21
        return null;
    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();
        String title = arguments.getString("title");
        String data = arguments.getString("data");

        NewConstraint.INewPresenter presenter=new NewPresenterImpl(this);
        presenter.onloadContent(data,title);
    }

    @Override
    protected void addData() {

    }

    @Override
    public void fillData(Object data) {
        // TODO: 2017/1/21
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }

    @Override
    protected boolean isAddItemDecoration() {
        return false;
    }
}
