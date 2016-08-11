package com.evan.demo.ui.activity;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.evan.demo.R;
import com.evan.demo.presenter.LoginPresenter;
import com.evan.demo.ui.ILoginView;
import com.evan.demo.manager.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_pwd)
    EditText mEtLoginPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.cbox_remember_pwd)
    CheckBox mCboxPwd;

    private LoginPresenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mPresenter = new LoginPresenter(this);
        mPresenter.start();
        initView();
    }

    private void initView() {
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                mPresenter.login();
                break;
            default:
                break;
        }
    }

    @Override
    public String getUserName() {
        return mEtAccount.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return mEtLoginPwd.getText().toString();
    }

    @Override
    public void setUserName(String name) {
        mEtAccount.setText(name);
    }

    @Override
    public void setUserPwd(String pwd) {
        mEtLoginPwd.setText(pwd);
    }

    @Override
    public void setCboxPwdChecked(boolean checked) {
        mCboxPwd.setChecked(checked);
    }

    @Override
    public boolean isRememberAccAndPwd() {
        return mCboxPwd.isChecked();
    }

    @Override
    public void showEmptyError() {
        ToastUtils.showToast("账号或密码不能为空");
    }

    @Override
    public void onLoginSuccess() {
        ToastUtils.showToast("登陆成功");
        // 登陆成功后,根据情况记录密码
        mPresenter.rememberAccAndPwd(mCboxPwd.isChecked());
    }

    @Override
    public void onLoginFailed() {
        ToastUtils.showToast("登录失败:帐号或密码错误");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onViewDestroy();
    }

    @Override
    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("正在登陆...");
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
