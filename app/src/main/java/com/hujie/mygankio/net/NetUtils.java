package com.hujie.mygankio.net;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by hujie on 2017/1/14.
 */

public class NetUtils {

    private IApi MApi;
    private Retrofit retrofit;
    private static NetUtils instance;
    public static final String BASE_URL="http://gank.io/api/";

    public static NetUtils getInstance(){
        if (instance==null){
            synchronized (NetUtils.class){
                if (instance==null){
                    instance=new NetUtils();
                }
            }
        }
        return instance;
    }

    private NetUtils (){
        //设置拦截器
         HttpLoggingInterceptor interceptor =
                 new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
             @Override
             public void log(String message) {
                 Log.i("===HTTP===", message);
             }
         });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        //添加拦截器，设置超时时间
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

         retrofit=new Retrofit.Builder().
                 client(client).
                 baseUrl(BASE_URL).
                 addConverterFactory(GsonConverterFactory.create()).
                 addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                 build();
    }

    public IApi getApi(){
        if (MApi==null){
            MApi=retrofit.create(IApi.class);
        }
        return MApi;
    }
}
