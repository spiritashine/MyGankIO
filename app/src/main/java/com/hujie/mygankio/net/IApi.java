package com.hujie.mygankio.net;

import com.hujie.mygankio.javabean.BaseHeaderReslut;
import com.hujie.mygankio.javabean.BaseReslut;
import com.hujie.mygankio.javabean.NewHeaderBean;
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

    @GET("data/{type}/20/{page}")
    Observable<BaseReslut<List<ResultsBean>>> listAllRx(@Path("type") String type,
                                                        @Path("page") int page);

    @GET("history/content/5/1")
    Observable<BaseHeaderReslut<List<NewHeaderBean>>> listHeaderRx();

    @GET("day/history")
    Observable<BaseReslut<String>> listHistoryRx();

    @GET("day/2017/01/{day}")
    Observable<BaseReslut<List<ResultsBean>>> listFooterRx(@Path("day") String day);


}
