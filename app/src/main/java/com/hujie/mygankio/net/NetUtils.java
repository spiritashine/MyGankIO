package com.hujie.mygankio.net;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络工具类
 * Created by hujie on 2017/1/13.
 */

public class NetUtils {
    public static final String BASE_URL = "http://gank.io/api/";
    private static final String TAG = "====HTTP====";
    private Retrofit retrofit;
    private IApi mApi;
    private static NetUtils instance;

    public static NetUtils getInstance() {
        if (instance == null) {
            synchronized (NetUtils.class) {
                if (instance == null) {
                    instance = new NetUtils();
                }
            }

        }
        return instance;
    }

    //初始化Retrofit
    private NetUtils() {
        //设置拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        /**
         * 创建OkHttp3 Client
         * 1.添加拦截器
         * 2.设置超时时间
         */
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        /**
         * 创建Retrofit
         * 1.添加baseUrl
         * 2.添加OkHttp3 Client
         * 3.设置ReJava适配器
         * 4.Gson解析工厂
         */
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public IApi getApi() {
        if (mApi == null)
            mApi = retrofit.create(IApi.class);
        return mApi;
    }

}
