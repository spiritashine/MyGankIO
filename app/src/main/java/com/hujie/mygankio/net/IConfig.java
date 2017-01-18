package com.hujie.mygankio.net;

import android.content.Context;

import com.hujie.mygankio.javabean.NewHeaderBean;
import com.hujie.mygankio.javabean.ResultsBean;

import java.util.List;

/**
 * Created by hujie on 2017/1/16.
 */

public class IConfig {

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
        void loadData(Context context,String type,int page,NetConfig callBack);
    }

    public interface INewView{
        void onPull(List<NewHeaderBean> data);
        String getLargeTitle();
        String getImageUrl();
        void addView();
        void loadFinish();
    }

    public interface INewPresenter{
        void pull();
    }

    public interface INewModel{
        void loadData(Context context,NetConfig callback);
    }
}
