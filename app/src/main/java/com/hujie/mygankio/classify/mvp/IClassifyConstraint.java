package com.hujie.mygankio.classify.mvp;

import android.content.Context;

import com.hujie.mygankio.classify.ResultsBean;
import com.hujie.mygankio.net.NetResponse;

import java.util.List;

/**
 * Created by hujie on 2017/1/16.
 */

public class IClassifyConstraint {

    public interface IView{
        void onPull(List<ResultsBean> data);
        void onDrag(List<ResultsBean> data);
        void loadFinish();
    }

    public interface IPresenter{
        void pull();
        void drag();
    }

    public interface IModel{
        void loadData(Context context,String type,int page,NetResponse callBack);
    }


}
