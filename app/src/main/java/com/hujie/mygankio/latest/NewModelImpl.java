package com.hujie.mygankio.latest;

import android.content.Context;

import com.hujie.mygankio.javabean.BaseHeaderReslut;
import com.hujie.mygankio.javabean.BaseReslut;
import com.hujie.mygankio.javabean.NewHeaderBean;
import com.hujie.mygankio.net.ExceptionHandle;
import com.hujie.mygankio.net.IApi;
import com.hujie.mygankio.net.IConfig;
import com.hujie.mygankio.net.MySubscribe;
import com.hujie.mygankio.net.NetConfig;
import com.hujie.mygankio.net.NetHelper;
import com.hujie.mygankio.net.NetUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by hujie on 2017/1/17.
 */

public class NewModelImpl implements IConfig.INewModel {
    @Override
    public void loadData(Context context,final NetConfig callback) {
        IApi api = NetUtils.getInstance().getApi();
        Observable<BaseReslut<List<NewHeaderBean>>> headerRx = api.listHeaderRx();
        headerRx.compose(NetHelper.schedulersTransformer())
                .compose(NetHelper.transformer())
                .subscribe(new MySubscribe<List<NewHeaderBean>>(context) {
                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        if(callback!=null){
                            callback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(List<NewHeaderBean> newHeaderBeen) {
                        super.onNext(newHeaderBeen);
                        if (callback!=null){
                            callback.onResponse(newHeaderBeen);
                        }
                    }
                });
    }
}
