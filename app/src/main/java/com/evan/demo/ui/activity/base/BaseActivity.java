package com.evan.demo.ui.activity.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.evan.demo.R;
import com.evan.demo.manager.ActivityCollector;
import com.evan.demo.presenter.BasePresenter;
import com.evan.demo.ui.iview.IBaseView;
import com.evan.demo.utils.ToastUtils;

/**
 * Created by evanyu on 16/6/8.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, IBaseView {

    protected Context mContext = this;
    protected Bundle mSavedInstanceState;
    private BasePresenter mPresenter;
    protected FrameLayout mBaseContainer;
    private View mLoadingView;
    private boolean isLoading = false; // 记录当前页面是否正在显示加载提示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 如果是继承自Activity,则需要执行以下代码
        // requestWindowFeature(Window.FEATURE_NO_TITLE);

        mSavedInstanceState = savedInstanceState;
        mPresenter = initPresenter();
        ActivityCollector.addActivity(this);
        onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityCollector.setForefroundActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ActivityCollector.setForefroundActivity(null);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.base_container, null);
        mBaseContainer = (FrameLayout) view.findViewById(R.id.fl_base_container);
        if (mBaseContainer != null) {
            inflater.inflate(layoutResId, mBaseContainer, true);
            super.setContentView(view);
        } else {
            super.setContentView(layoutResId);
        }
    }

    /**
     * 初始化Presenter
     * 默认返回null,创建Presenter时子类需要改写此方法并返回Presenter对象
     */
    protected BasePresenter initPresenter() {
        return null;
    }

    protected abstract void onCreate();

    @Override
    public void onClick(View v) {
        // empty
    }

    @Override
    public Context getContext() {
        return this;
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
        if (mLoadingView == null) {
            mLoadingView = View.inflate(mContext, R.layout.loading_view, null);
        }
        if (mBaseContainer != null && mLoadingView != null && !isLoading()) {
            mBaseContainer.addView(mLoadingView);
            isLoading = true;
        }
    }

    @Override
    public void hideLoading() {
        if (mBaseContainer != null && mLoadingView != null && isLoading()) {
            mBaseContainer.removeView(mLoadingView);
            isLoading = false;
        }
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(mContext, cls));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mPresenter != null) {
            mPresenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void finishActivity() {
        if (!isFinishing()) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        if (mPresenter != null) {
            mPresenter.onViewDestory();
        }
    }

}
