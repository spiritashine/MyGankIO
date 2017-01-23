package com.hujie.mygankio.latest.mvp;


import com.hujie.mygankio.javabean.BaseResultBean;
import com.hujie.mygankio.javabean.RecommendTabBean;
import com.hujie.mygankio.javabean.RecommendTypeBean;
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

public class RecommendRxModelImpl implements IRecommendConstraint.RecommendModel {

    @Override
    public void loadTab(int size, final NetResponse response) {
        Observable<BaseResultBean<List<RecommendTabBean>>> observable =
                NetUtils.getInstance().getApi().getTabRx(size);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResultBean<List<RecommendTabBean>>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        if (response != null) {
                            response.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(BaseResultBean<List<RecommendTabBean>> result) {
                        if (!result.isError()) {
                            if (response != null) {
                                response.onResponse(result.getResults());
                            }
                        } else {
                            if (response != null) {
                                response.onError(result.getMsg());
                            }
                        }
                    }
                });
    }


    @Override
    public void loadContent(String date, final NetResponse response) {
        Observable<BaseResultBean<RecommendTypeBean>> observable =
                NetUtils.getInstance().getApi().getRecommendRx(date);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResultBean<RecommendTypeBean>>() {
                    @Override
                    public void onCompleted(){}

                    @Override
                    public void onError(Throwable e) {
                        if (response != null) {
                            response.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(BaseResultBean<RecommendTypeBean> result) {
                        if (response!=null){
                            if (result.isError()){
                                response.onError(result.getMsg());
                            }else {
                                response.onResponse(result.getResults());
                            }
                        }
                    }
                });
    }
}
