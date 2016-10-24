package com.evan.demo.manager.subscriber;


import com.evan.demo.ui.iview.IBaseView;

/**
 * 带自动进度提示的Subscriber
 * Created by evanyu on 16/9/19.
 */
public abstract class ProgressSubscriber<T> extends DefaultSubscriber<T> {

    private IBaseView mView;

    public ProgressSubscriber(IBaseView view) {
        mView = view;
    }

    @Override
    public void onStart() {
        super.onStart();
        showLoading();
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        hideLoading();
        recycler();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        hideLoading(); // 发生错误时,默认隐藏进度提示
        recycler();
    }

    /**
     * 显示进度提示
     */
    private void showLoading() {
        if (mView != null) {
            mView.showLoading();
        }
    }

    /**
     * 隐藏进度提示
     */
    private void hideLoading() {
        if (mView != null) {
            mView.hideLoading();
        }
    }

    private void recycler() {
        // 将mView置空,以防内存泄漏
        mView = null;
    }
}
