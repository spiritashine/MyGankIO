package com.hujie.mygankio.net;


import com.hujie.mygankio.javabean.BaseReslut;
import com.hujie.mygankio.javabean.HistoryBean;
import com.hujie.mygankio.javabean.NewBean;
import com.hujie.mygankio.javabean.NewItemBean;
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
    Observable<BaseReslut<List<HistoryBean>>> listHeadTitleRx();

    @GET("history/content/{size}/1")
    Call<BaseReslut<List<HistoryBean>>> getTab(@Path("size") int size);

    /**
     * 2015/08/07
     * @param date
     * @return
     */
    @GET("day/{date}")
    Call<BaseReslut<NewBean>> getNew(@Path("date") String date);

}
