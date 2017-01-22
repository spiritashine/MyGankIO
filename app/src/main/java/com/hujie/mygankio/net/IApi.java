package com.hujie.mygankio.net;


import com.hujie.mygankio.base.BaseResult;
import com.hujie.mygankio.classify.ResultsBean;
import com.hujie.mygankio.latest.RecommendTabBean;
import com.hujie.mygankio.latest.RecommendTypeBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hujie on 2017/1/13.
 */

public interface IApi {


    @GET("data/{type}/20/{page}")
    Observable<BaseResult<List<ResultsBean>>> listAllRx(
            @Path("type") String type,
            @Path("page") int page);


    @GET("history/content/{size}/1")
    Observable<BaseResult<List<RecommendTabBean>>> getTabRx(@Path("size") int size);

    /**
     * @param date 2015/08/07
     * @return
     */
    @GET("day/{date}")
    Observable<BaseResult<RecommendTypeBean>> getRecommendRx(@Path("date") String date);

}
