package com.evan.demo.ui.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.evan.demo.R;
import com.evan.demo.presenter.BasePresenter;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.ui.iview.IBaseView;
import com.evan.demo.utils.ToastUtils;

/**
 * Fragment基类
 * Created by evanyu on 16/9/8.
 */
public abstract class BaseFragment extends Fragment implements IBaseView, View.OnClickListener {

    protected Context mContext;
    protected Bundle mSavedInstanceState;
    private BasePresenter mPresenter;
    protected FrameLayout mBaseContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutResId = getLayoutResId();
        if (layoutResId < 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View view = inflater.inflate(R.layout.base_container, null);
            mBaseContainer = (FrameLayout) view.findViewById(R.id.fl_base_container);
            if (mBaseContainer != null) {
                inflater.inflate(layoutResId, mBaseContainer, true);
                return view;
            } else {
                return inflater.inflate(layoutResId, container, false);
            }
        }
    }

    /**
     * 获取布局资源id
     * 返回{@link View#NO_ID}表示没有指定布局
     */
    protected abstract int getLayoutResId();

    protected abstract void onViewCreated(View view);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSavedInstanceState = savedInstanceState;
        mPresenter = initPresenter();
        onViewCreated(view);
    }

    /**
     * 初始化Presenter
     * 默认返回null,创建Presenter时子类需要改写此方法并返回Presenter对象
     */
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public Intent getIntent() {
        return getActivity().getIntent();
    }

    @Override
    public void showToast(String text) {
        ToastUtils.showToastShort(text);
    }

    @Override
    public void showToastLong(String text) {
        ToastUtils.showToastLong(text);
    }

    @Override
    public void showLoading() {
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).hideLoading();
        }
    }

    @Override
    public boolean isLoading() {
        if (mContext instanceof BaseActivity) {
            return ((BaseActivity) mContext).isLoading();
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        // empty
    }

    @Override
    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(mContext, cls));
    }

    @Override
    public void finishActivity() {
        if (!getActivity().isFinishing()) {
            getActivity().finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onViewDestory();
        }
    }
}
