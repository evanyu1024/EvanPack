package com.evan.demo.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.evan.demo.R;
import com.evan.demo.contract.LoginContract;
import com.evan.demo.presenter.LoginPresenter;
import com.evan.demo.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {

    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_pwd)
    EditText mEtLoginPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_clear)
    Button mBtnClear;

    private LoginContract.Presenter mPresenter;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setPresenter(new LoginPresenter(this));

        mBtnLogin.setOnClickListener(this);
        mBtnClear.setOnClickListener(this);

        LogUtils.i("log...info");
        Log.i("LogUtils", "log...info222", new Throwable("throwable"));
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
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
    public void showLoginProgress() {
       if(mDialog == null) {
           mDialog = new ProgressDialog(this);
           mDialog.setMessage("正在登陆...");
       }
        mDialog.show();
    }

    @Override
    public void hideLoginProgress() {
        if(mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showLoginFailedPrompt() {
        Snackbar.make(mBtnLogin, "账号或密码错误", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void enterInMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void clearUserName() {

    }

    @Override
    public void clearUserPwd() {

    }

    @Override
    public void showEmptyPrompt() {
        Snackbar.make(mBtnLogin, "账号或密码不能为空", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                mPresenter.login();
                break;
            case R.id.btn_clear:
                mPresenter.clear();
                break;
            default:
                break;
        }
    }
}
