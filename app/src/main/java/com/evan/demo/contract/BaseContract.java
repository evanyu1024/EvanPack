package com.evan.demo.contract;

/**
 * 协议基类
 * Created by evanyu on 16/7/11.
 */
public interface BaseContract {

    /**
     * View层顶层接口
     * @param <T>
     */
    interface IBaseView<T> {
        void setPresenter(T presenter);
    }

    /**
     * Presenter层顶层接口
     */
    interface IBasePresenter {
    }

}
