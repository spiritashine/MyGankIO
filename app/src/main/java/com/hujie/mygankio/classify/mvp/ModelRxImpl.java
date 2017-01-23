package com.hujie.mygankio.classify.mvp;

import android.content.Context;

import com.hujie.mygankio.javabean.BaseResultBean;
import com.hujie.mygankio.javabean.ResultsBean;
import com.hujie.mygankio.net.NetResponse;
import com.hujie.mygankio.net.NetUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hujie on 2017/1/16.
 */

public class ModelRxImpl implements IClassifyConstraint.IModel{

    @Override
    public void loadData(Context context, String type, int page, final NetResponse response) {

        Observable<BaseResultBean<List<ResultsBean>>> observable = NetUtils.getInstance().getApi().listAllRx(type, page);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResultBean<List<ResultsBean>>>() {
                    @Override
                    public void onCompleted(){}

                    @Override
                    public void onError(Throwable e) {
                        if(response!=null){
                            response.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(BaseResultBean<List<ResultsBean>> listBaseResultBean) {
                        if (!listBaseResultBean.isError()) {
                            if (response != null) {
                                response.onResponse(listBaseResultBean.getResults());
                            }
                        } else {
                            if (response != null) {
                                response.onError(listBaseResultBean.getMsg());
                            }
                        }
                    }
                });
    }
}
