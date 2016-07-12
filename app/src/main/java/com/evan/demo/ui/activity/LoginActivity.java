package com.evan.demo.ui.activity;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.evan.demo.R;
import com.evan.demo.contract.LoginContract;
import com.evan.demo.presenter.LoginPresenter;
import com.evan.demo.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginContract.View
{

    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_pwd)
    EditText mEtLoginPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.cbox_remember_pwd)
    CheckBox mCboxPwd;

    private LoginContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mPresenter = new LoginPresenter(this);
        mPresenter.onCreate(); // 初始化Presenter

        initViews();
    }

    private void initViews() {
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
        if (!TextUtils.isEmpty(name)) {
            mEtAccount.setText(name);
        }
    }

    @Override
    public void setUserPwd(String pwd) {
        if (!TextUtils.isEmpty(pwd)) {
            mEtLoginPwd.setText(pwd);
        }
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
    public void showLoginProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("正在登陆...");
        }
        mProgressDialog.show();
    }

    @Override
    public void hideLoginProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void promptLoginFailed() {
        // Snackbar.make(mBtnLogin, "账号或密码错误", Snackbar.LENGTH_SHORT).show();
        ToastUtils.showToastShort("帐号或密码错误");
    }

    @Override
    public void showEmptyPrompt() {
        // Snackbar.make(mBtnLogin, "账号或密码不能为空", Snackbar.LENGTH_SHORT).show();
        ToastUtils.showToastShort("账号或密码不能为空");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
