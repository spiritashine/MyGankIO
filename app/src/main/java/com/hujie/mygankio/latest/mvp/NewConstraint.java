package com.hujie.mygankio.latest.mvp;

import com.hujie.mygankio.net.NetResponse;

/**
 * 定义首页MVP接口
 * Created by hujie on 2017/1/20.
 */

public class NewConstraint {

    public interface INewView{
        //显示正在加载内容的状态
        void showLoading();

        //加载布局
        void showContent();

        //显示错误信息
        void showError(String msg);

        //填充数据
        void fillData(Object data);

    }

    public interface INewModel{
        //处理大标题数据
        void loadTitle(int size, NetResponse response);

        //处理内容数据
        void loadContent(String data,NetResponse response);
    }

    public interface INewPresenter{
        //处理大标题
        void onLoadTitle(int size);

        //处理内容
        void onloadContent(String data,String title);
    }
}
