package com.hujie.mygankio.latest.mvp;


import com.hujie.mygankio.base.BaseResult;
import com.hujie.mygankio.latest.RecommendTabBean;
import com.hujie.mygankio.latest.RecommendTypeBean;
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

public class RecommendRxModelImpl implements RecommendConstraint.RecommendModel {

    @Override
    public void loadTab(int size, final NetResponse response) {
        Observable<BaseResult<List<RecommendTabBean>>> observable =
                NetUtils.getInstance().getApi().getTabRx(size);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResult<List<RecommendTabBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (response != null) {
                            response.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(BaseResult<List<RecommendTabBean>> reslut) {
                        if (!reslut.isError()) {
                            if (response != null) {
                                response.onResponse(reslut.getResults());
                            }
                        } else {
                            if (response != null) {
                                response.onError(reslut.getMsg());
                            }
                        }
                    }
                });


    }


    @Override
    public void loadContent(String date, final NetResponse response) {
        Observable<BaseResult<RecommendTypeBean>> observable =
                NetUtils.getInstance().getApi().getRecommendRx(date);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResult<RecommendTypeBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (response != null) {
                            response.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(BaseResult<RecommendTypeBean> reslut) {
                        if (!reslut.isError()) {
                            if (response != null) {
                                response.onResponse(reslut.getResults());
                            }
                        } else {
                            if (response != null) {
                                response.onError(reslut.getMsg());
                            }
                        }
                    }
                });
    }
}
