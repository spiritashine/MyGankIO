package com.hujie.mygankio.latest.mvp;

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

    }

    @Override
    public void onloadContent(String data, String title) {

    }
}
