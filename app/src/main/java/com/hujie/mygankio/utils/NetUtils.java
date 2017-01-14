package com.hujie.mygankio.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hujie on 2017/1/14.
 */

public class NetUtils {

    private IApi MApi;
    private Retrofit retrofit;
    private static NetUtils instance;
    public static final String BASE_URL="http://gank.io/api/";

    public static NetUtils GetInstance(){
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
         retrofit=new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(ScalarsConverterFactory.create()).
                build();
    }

    public IApi GetApi(){
        if (MApi==null){
            MApi=retrofit.create(IApi.class);
        }
        return MApi;
    }
}
