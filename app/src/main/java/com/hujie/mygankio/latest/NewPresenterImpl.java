package com.hujie.mygankio.latest;

import android.content.Context;

import com.hujie.mygankio.javabean.NewHeaderBean;
import com.hujie.mygankio.net.IConfig;
import com.hujie.mygankio.net.NetConfig;

import java.util.List;

/**
 * Created by hujie on 2017/1/17.
 */

public class NewPresenterImpl implements IConfig.INewPresenter {
    private IConfig.INewView view;
    private IConfig.INewModel model;
    private Context context;

    public NewPresenterImpl(IConfig.INewView view, Context context) {
        this.view = view;
        this.context = context;
        model=new NewModelImpl();
    }



    @Override
    public void pull() {
        model.loadData(context, new NetConfig() {
            @Override
            public void onResponse(Object data) {
                List<NewHeaderBean> list= (List<NewHeaderBean>) data;
                view.onPull(list);
                view.loadFinish();
            }

            @Override
            public void onError(String msg) {

            }
        });

    }
}
