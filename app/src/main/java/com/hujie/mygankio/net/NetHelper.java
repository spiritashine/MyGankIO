package com.hujie.mygankio.net;

import com.hujie.mygankio.javabean.BaseBean;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.schedulers.HandlerScheduler;
import rx.annotations.Experimental;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.util.ExceptionsUtils;
import rx.schedulers.Schedulers;

/**
 * Created by hujie on 2017/1/16.
 */

public class NetHelper {

    public static Observable.Transformer schedulersTransform(){
        return new Observable.Transformer(){
            @Override
            public Object call(Object observable) {
                return ((Observable)observable).
                        subscribeOn(Schedulers.io()).
                        unsubscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    public static <T> Observable.Transformer transformer(){
        return new Observable.Transformer(){
            @Override
            public Object call(Object o) {
                return ((Observable)o).
                        map(new HandleFunc<T>()).
                        onErrorResumeNext(new HttpResponseFunc<T>());
            }
        };
    }

    public static class HttpResponseFunc<T> implements Func1<Throwable,Observable<T>>{
        @Override
        public Observable<T> call(Throwable throwable) {
            return Observable.error(throwable);
        }
    }

    public static class HandleFunc<T> implements Func1<BaseBean<T>,T>{

        @Override
        public T call(BaseBean<T> tBaseBean) {
            if (tBaseBean.isError()){
                throw new RuntimeException(tBaseBean.getMsg());
            }
                return tBaseBean.getResult();
        }
    }
}
