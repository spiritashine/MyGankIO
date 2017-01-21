package com.hujie.mygankio.latest.mvp;

import android.text.TextUtils;

import com.hujie.mygankio.javabean.NewBean;
import com.hujie.mygankio.javabean.NewItemBean;
import com.hujie.mygankio.latest.ItemType;
import com.hujie.mygankio.net.NetResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujie on 2017/1/21.
 */

public class NewPresenterImpl implements NewConstraint.INewPresenter {
    private NewConstraint.INewView view;
    private NewConstraint.INewModel model;

    public NewPresenterImpl(NewConstraint.INewView view) {
        this.view = view;
        model=new NewModelRxImpl();
    }

    @Override
    public void onLoadTitle(int size) {
        view.showLoading();
        model.loadTitle(size, new NetResponse() {
            @Override
            public void onResponse(Object data) {
                view.showContent();
                view.fillData(data);
            }

            @Override
            public void onError(String msg) {
                view.showError(msg);
            }
        });
    }

    @Override
    public void onloadContent(String date, final String title) {
        date = TextUtils.substring(date, 0, 10);
        date = date.replace("-","/");

        view.showLoading();
        model.loadContent(date, new NetResponse() {
            @Override
            public void onResponse(Object data) {
                NewBean newBean= (NewBean) data;
                ArrayList<ItemType> itemTypes=new ArrayList<>();

                //title
                ItemType itemTitle = new ItemType(ItemType.TYPE_TITILE,title);
                itemTypes.add(itemTitle);

                //image
                List<NewItemBean> 福利 = newBean.get福利();
                if (福利.size()>0){
                    for (int i=0;i<福利.size();i++){
                        ItemType itemImage = new ItemType(ItemType.TYPE_IMAGE,福利.get(i));
                        itemTypes.add(itemImage);
                    }
                }

                List<NewItemBean> 休息视频 = newBean.get休息视频();
                handleTypeData(itemTypes, "休息视频", 休息视频);

                List<NewItemBean> android = newBean.getAndroid();
                handleTypeData(itemTypes,"Android",android);

                List<NewItemBean> ios = newBean.getIOS();
                handleTypeData(itemTypes,"IOS",ios);

                List<NewItemBean> 前端 = newBean.get前端();
                handleTypeData(itemTypes,"前端",前端);

                List<NewItemBean> 瞎推荐 = newBean.get瞎推荐();
                handleTypeData(itemTypes,"瞎推荐",瞎推荐);

                List<NewItemBean> 拓展资源 = newBean.get拓展资源();
                handleTypeData(itemTypes,"拓展资源",拓展资源);

                view.showContent();
                view.fillData(itemTypes);
            }

            @Override
            public void onError(String msg) {
                view.showError(msg);
            }
        });
    }

    private void handleTypeData(ArrayList<ItemType> data,String subTitle,List<NewItemBean> beans){
        if (beans!=null&&beans.size()>0){
            ItemType st=new ItemType(ItemType.TYPE_SUBTITLE,subTitle);
            data.add(st);
            for (int i=0;i<beans.size();i++){
                ItemType content = new ItemType(ItemType.TYPE_CONTENT,beans.get(i));
                data.add(content);
            }
        }
    }
}
