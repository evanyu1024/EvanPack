package com.evan.demo.presenter;

import com.evan.demo.ui.IBaseView;

/**
 * Created by evanyu on 16/7/14.
 */
public interface IPresenter<T extends IBaseView> {

    void start();

    void attachView(T view);

    void detatchView();

    T getView();

    boolean isViewAttached();

    void showToast(String msg);

    void showLoading();

    void hideLoading();

    void onViewDestroy();

}
