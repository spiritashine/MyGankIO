package com.hujie.mygankio.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hujie on 2017/1/14.
 */

public interface IApi {
    //  http://gank.io/api/data/Android/10/1

    @GET("data/{type}/{size}/{page}")
    Call<ResponseBody> listAll(@Path("type") String type,
                               @Path("size") int size,
                               @Path("page") int page);
}
