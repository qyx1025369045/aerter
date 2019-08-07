package com.example.daye5.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daye5.R;
import com.example.daye5.base.BaseActivity;
import com.example.daye5.fragment.CollectFragment;
import com.example.daye5.modles.login.bean.LoginData;
import com.example.daye5.modles.login.contrat.LoginContract;
import com.example.daye5.modles.login.presenter.LoginPresenters;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContract.LoginView, LoginPresenters<LoginContract.LoginView>> implements LoginContract.LoginView {

    @BindView(R.id.logtb)
    Toolbar logtb;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;
    private String name;
    private String passw;
    private CollectFragment.BroadReceive broadReceive;

    @Override
    protected void initView() {
        logtb.setTitle("登录");
        setSupportActionBar(logtb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(false);
        }

        logtb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        broadReceive = new CollectFragment.BroadReceive();
        IntentFilter aifgl = new IntentFilter("aifgl");
        LocalBroadcastManager.getInstance(LoginActivity.this).registerReceiver(broadReceive,aifgl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(LoginActivity.this).unregisterReceiver(broadReceive);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess(LoginData loginData) {
        SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = login.edit();
        edit.putString("usname",loginData.getUsername());
        edit.putString("uspassword",loginData.getPassword());
        edit.putString("ciod",loginData.getToken());
        edit.commit();

        if (loginData.getUsername().equals(name)) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("names", loginData.getUsername());
            startActivity(intent);
            finish();
        }

        Intent intent = new Intent();
        List<Integer> collectIds = loginData.getCollectIds();
        for (int i = 0; i < collectIds.size(); i++) {
            intent.putExtra("id",collectIds.get(i));
            LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(intent);
        }

    }



    @Override
    public void loginFiler(String error) {
        Toast.makeText(LoginActivity.this, "账户密码错误", Toast.LENGTH_LONG).show();
    }

    @Override
    protected LoginPresenters<LoginContract.LoginView> createPresenter() {
        return new LoginPresenters<>();
    }

    @OnClick({R.id.btn_login, R.id.tv_sign_up})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                name = etUsername.getText().toString();
                passw = etPassword.getText().toString();
                if (name.isEmpty() && passw.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "请输入用户名与密码，若无账号请注册", Toast.LENGTH_LONG).show();
                } else {
                    mPresenter.getLogein(name, passw);
                }
                break;
            case R.id.tv_sign_up:
                Intent intent = new Intent(LoginActivity.this, RegirActivity.class);
                startActivity(intent);
                break;
        }
    }


}
