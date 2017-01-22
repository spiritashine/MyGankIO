package com.hujie.mygankio.classify.mvp;

import android.content.Context;

import com.hujie.mygankio.base.BaseResult;
import com.hujie.mygankio.classify.ResultsBean;
import com.hujie.mygankio.net.ExceptionHandle;
import com.hujie.mygankio.net.MySubscribe;
import com.hujie.mygankio.net.NetResponse;
import com.hujie.mygankio.net.NetHelper;
import com.hujie.mygankio.net.IApi;
import com.hujie.mygankio.net.NetUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by hujie on 2017/1/16.
 */

public class ModelImpl implements IClassifyConstraint.IModel{

    @Override
    public void loadData(Context context, String type, int page, final NetResponse callback) {

        IApi api = NetUtils.getInstance().getApi();
        Observable<BaseResult<List<ResultsBean>>> observable = api.listAllRx(type, page);
        observable. compose(NetHelper.schedulersTransformer()).
                    compose(NetHelper.transformer()).
                    subscribe(new MySubscribe<List<ResultsBean>>(context) {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if(callback!=null){
                    callback.onError(e.getMessage());
                }
            }

                        @Override
                        public void onCompleted() {

                        }

                        @Override
            public void onNext(List<ResultsBean> resultsBeen) {
                super.onNext(resultsBeen);
                if(callback!=null){
                    callback.onResponse(resultsBeen);
                }
            }
        });
    }
}
