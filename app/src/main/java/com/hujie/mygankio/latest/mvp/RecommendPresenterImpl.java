package com.hujie.mygankio.latest.mvp;

import android.text.TextUtils;
import com.hujie.mygankio.latest.ItemType;
import com.hujie.mygankio.latest.RecommendTypeBean;
import com.hujie.mygankio.latest.RecommmendBean;
import com.hujie.mygankio.net.NetResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujie on 2017/1/21.
 */

public class RecommendPresenterImpl implements RecommendConstraint.RecommendPresenter {


    private RecommendConstraint.RecommendView view;
    private RecommendConstraint.RecommendModel model;

    public RecommendPresenterImpl(RecommendConstraint.RecommendView view) {
        this.view = view;
        this.model = new RecommendRxModelImpl();
    }

    @Override
    public void loadTab(int size) {
        view.showLoading();
        model.loadTab(size, new NetResponse() {
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
    public void loadContent(String date, final String title) {

        date = TextUtils.substring(date, 0, 10);
        date = date.replace("-", "/");

        view.showLoading();
        model.loadContent(date, new NetResponse() {
            @Override
            public void onResponse(Object reslut) {

                RecommendTypeBean typeBean = (RecommendTypeBean) reslut;
                ArrayList<ItemType> data = new ArrayList<>();

                //title
                ItemType titleItem = new ItemType(ItemType.TYPE_TITLE, title);
                data.add(titleItem);

                //image
                List<RecommmendBean> fl = typeBean.get福利();
                if (fl.size() > 0) {
                    for (int i = 0; i < fl.size(); i++) {
                        ItemType imageType = new ItemType(ItemType.TYPE_IMAGE, fl.get(i));
                        data.add(imageType);
                    }
                }

                //视频
                List<RecommmendBean> spxx = typeBean.get休息视频();
                handleTypeData(data, "休息视频", spxx);

                //android
                List<RecommmendBean> android = typeBean.getAndroid();
                handleTypeData(data, "Android", android);

                //ios
                List<RecommmendBean> ios = typeBean.getIOS();
                handleTypeData(data, "iOS", ios);

                //qiandan
                List<RecommmendBean> qd = typeBean.get前端();
                handleTypeData(data, "前端", qd);

                //瞎推荐
                List<RecommmendBean> xtj = typeBean.get瞎推荐();
                handleTypeData(data, "瞎推荐", xtj);

                //拓展资源
                List<RecommmendBean> tzzy = typeBean.get拓展资源();
                handleTypeData(data, "拓展资源", tzzy);

                view.showContent();
                view.fillData(data);
            }

            @Override
            public void onError(String msg) {
                view.showError(msg);
            }
        });
    }

    private void handleTypeData(ArrayList<ItemType> data,
                                String subtitle,
                                List<RecommmendBean> beans) {
        if (beans != null && beans.size() > 0) {
            ItemType subTitle = new ItemType(ItemType.TYPE_SUB_TITLE, subtitle);
            data.add(subTitle);
            for (int i = 0; i < beans.size(); i++) {
                ItemType content = new ItemType(ItemType.TYPE_CONTENT, beans.get(i));
                data.add(content);
            }
        }
    }
}
