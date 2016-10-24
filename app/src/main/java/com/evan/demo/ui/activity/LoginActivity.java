package com.evan.demo.ui.activity;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.EditText;

import com.evan.demo.R;
import com.evan.demo.presenter.BasePresenter;
import com.evan.demo.presenter.LoginPresenter;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.ui.iview.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MVP示例
 */
public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.et_account)
    EditText mEtName;
    @BindView(R.id.et_pwd)
    EditText mEtLoginPwd;

    private LoginPresenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected BasePresenter initPresenter() {
        return mPresenter = new LoginPresenter(this);
    }

    @OnClick(R.id.btn_login)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String name = mEtName.getText().toString().trim();
                String pwd = mEtLoginPwd.getText().toString().trim();
                mPresenter.login(name, pwd);
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoginSuccess() {
        showToast("登陆成功");
        // startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onLoginFailed() {
        showToast("登录失败:帐号或密码错误");
    }
}
