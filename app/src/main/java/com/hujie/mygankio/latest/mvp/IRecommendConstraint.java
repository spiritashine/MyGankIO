package com.hujie.mygankio.latest.mvp;


import com.hujie.mygankio.net.NetResponse;

/**
 * Created by hujie on 2017/1/21.
 */

public class IRecommendConstraint {

    public interface RecommendView {
        void showLoading();

        void showContent();

        void showError(String msg);

        void fillData(Object data);
    }

    public interface RecommendPresenter {
        void loadTab(int size);

        void loadContent(String date, String title);
    }

    public interface RecommendModel {
        void loadTab(int size, NetResponse response);

        void loadContent(String date, NetResponse response);
    }

}
