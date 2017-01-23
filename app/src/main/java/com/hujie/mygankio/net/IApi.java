package com.hujie.mygankio.net;


import com.hujie.mygankio.javabean.BaseResultBean;
import com.hujie.mygankio.javabean.ResultsBean;
import com.hujie.mygankio.javabean.RecommendTabBean;
import com.hujie.mygankio.javabean.RecommendTypeBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Retrofit接口
 * Created by hujie on 2017/1/13.
 */

public interface IApi {

    /**
     * ClassifyPage
     * @param type
     * @param page
     * @return
     */
    @GET("data/{type}/20/{page}")
    Observable<BaseResultBean<List<ResultsBean>>> listAllRx(
            @Path("type") String type,
            @Path("page") int page);

    /**
     * HomePageTitle
     * @param size
     * @return
     */
    @GET("history/content/{size}/1")
    Observable<BaseResultBean<List<RecommendTabBean>>> getTabRx(
            @Path("size") int size);

    /**
     * HomePageContent
     * @param date 2015/08/07
     * @return
     */
    @GET("day/{date}")
    Observable<BaseResultBean<RecommendTypeBean>> getRecommendRx(
            @Path("date") String date);

}
