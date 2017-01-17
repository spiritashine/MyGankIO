package com.hujie.mygankio.net;

import com.hujie.mygankio.javabean.BaseReslut;
import com.hujie.mygankio.javabean.ResultsBean;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hujie on 2017/1/14.
 */

public interface IApi {
    //  http://gank.io/api/data/Android/10/1

    @GET("data/{type}/20/{page}")
    Call<ResponseBody> listAll(@Path("type") String type,
                               @Path("page") int page);

    @GET("data/{type}/20/{page}")
    Observable<BaseReslut<List<ResultsBean>>> listAllRx(@Path("type") String type,
                                                        @Path("page") int page);
}
