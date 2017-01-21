package com.hujie.mygankio.net;


import com.hujie.mygankio.javabean.BaseResult;
import com.hujie.mygankio.javabean.HistoryBean;
import com.hujie.mygankio.javabean.NewBean;
import com.hujie.mygankio.javabean.ResultsBean;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hujie on 2017/1/14.
 */

public interface IApi {

    /**
     * 分类页
     * @param type
     * @param page
     * @return
     */
    @GET("data/{type}/20/{page}")
    Observable<BaseResult<List<ResultsBean>>> listAllRx(@Path("type") String type,
                                                        @Path("page") int page);

    /**
     * 首页大标题
     * @param size
     * @return
     */
    @GET("history/content/{size}/1")
    Observable<BaseResult<List<HistoryBean>>> getNewTitleRx(@Path("size") int size);


    /**
     * 首页大标题下的内容
     * @param date
     * @return
     */
    @GET("day/{date}")
    Observable<BaseResult<NewBean>> getNewContentRx(@Path("date") String date);

}
