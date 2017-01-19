package com.hujie.mygankio.net;


import com.hujie.mygankio.javabean.BaseReslut;
import com.hujie.mygankio.javabean.HistoryBean;
import com.hujie.mygankio.javabean.ResultsBean;
import java.util.List;
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

}
