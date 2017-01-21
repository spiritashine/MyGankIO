package com.hujie.mygankio.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;


import rx.Subscriber;

/**
 * Created by hujie on 2017/1/16.
 */

public abstract class MySubscribe<T> extends Subscriber<T> {


    public MySubscribe(Context context) {
        this.context = context;
    }

    private Context context;
    private ProgressDialog dialog;

    @Override
    public void onStart() {
        super.onStart();
         dialog=new ProgressDialog(context);
         dialog.show();
    }

    @Override
    public void onError(Throwable e) {
        //统一处理异常
        if (e instanceof ExceptionHandle.ResponeThrowable) {
            //逻辑异常  回调给Activity
            onError((ExceptionHandle.ResponeThrowable) e);
        } else {
            //网路异常  其他异常
            onError(ExceptionHandle.handleException(e));
        }
    }

    @Override
    public void onNext(T t) {
        dialog.dismiss();
    }

    //统一处理异常  出现错误直接调用 自定义的 onError
    public abstract void onError(ExceptionHandle.ResponeThrowable e);

}
