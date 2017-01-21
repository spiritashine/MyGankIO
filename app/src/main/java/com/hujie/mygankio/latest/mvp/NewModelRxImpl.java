package com.hujie.mygankio.latest.mvp;

import android.util.Log;

import com.hujie.mygankio.javabean.BaseResult;
import com.hujie.mygankio.javabean.HistoryBean;
import com.hujie.mygankio.javabean.NewBean;
import com.hujie.mygankio.net.NetResponse;
import com.hujie.mygankio.net.NetUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hujie on 2017/1/21.
 */

public class NewModelRxImpl implements NewConstraint.INewModel {
    @Override
    public void loadTitle(int size, final NetResponse response) {
        Observable<BaseResult<List<HistoryBean>>> observable =
                NetUtils.getInstance().getApi().getNewTitleRx(size);

        observable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<BaseResult<List<HistoryBean>>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("======loadTitle=====", "onCompleted: loadTitle");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (response!=null){
                            response.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(BaseResult<List<HistoryBean>> listBaseResult) {
                        if (!listBaseResult.isError()) {
                            if (response != null) {
                                response.onResponse(listBaseResult.getResults());
                            }
                        } else {
                            if (response != null) {
                                response.onError(listBaseResult.getMsg());
                            }
                        }
                    }
                });

    }

    @Override
    public void loadContent(String data, final NetResponse response) {
        Observable<BaseResult<NewBean>> observable =
                NetUtils.getInstance().getApi().getNewContentRx(data);
        observable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<BaseResult<NewBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("====loadContent=====", " loadContent");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (response!=null){
                            response.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(BaseResult<NewBean> newBeanBaseResult) {
                        if (!newBeanBaseResult.isError()) {
                            if (response != null) {
                                response.onResponse(newBeanBaseResult.getResults());
                            }
                        } else {
                            if (response != null) {
                                response.onError(newBeanBaseResult.getMsg());
                            }
                        }
                    }
                });
    }
}
