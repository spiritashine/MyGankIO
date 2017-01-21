package com.hujie.mygankio.ui;

import android.content.Context;

import com.hujie.mygankio.net.NetResponse;
import com.hujie.mygankio.javabean.ResultsBean;

import java.util.List;

/**
 * Created by hujie on 2017/1/16.
 */

public class PresenterImpl implements IClassifyConstraint.IPresenter {
    private IClassifyConstraint.IView view;
    private IClassifyConstraint.IModel model;
    private String type;
    private Context context;
    private int page=1;

    public PresenterImpl(IClassifyConstraint.IView view, Context context, String type) {
        this.view = view;
        this.context = context;
        this.type = type;
        model=new ModelImpl();
    }

    @Override
    public void pull() {
        model.loadData(context, type, page, new NetResponse() {
            @Override
            public void onResponse(Object data) {
                List<ResultsBean> list= (List<ResultsBean>) data;
                view.onPull(list);
                view.loadFinish();
            }

            @Override
            public void onError(String msg) {

            }
        });

    }

    @Override
    public void drag() {
        model.loadData(context, type, ++page, new NetResponse() {
            @Override
            public void onResponse(Object data) {
                List<ResultsBean> list= (List<ResultsBean>) data;
                view.onPull(list);
                view.loadFinish();
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
