package com.example.daye5.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daye5.R;
import com.example.daye5.base.BaseActivity;
import com.example.daye5.modles.login.bean.LoginData;
import com.example.daye5.modles.regir.contrat.RegirContract;
import com.example.daye5.modles.regir.presenter.RegirPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class RegirActivity extends BaseActivity<RegirContract.RegirView, RegirPresenter<RegirContract.RegirView>> implements RegirContract.RegirView {


    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password2)
    EditText etPassword2;
    @BindView(R.id.btn_register)
    TextView btnRegister;
    @BindView(R.id.tv_sign_in)
    TextView tvSignIn;
    private String name;
    private String pwssed1;
    private String passed2;

    @OnClick({R.id.btn_register,R.id.tv_sign_in})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_register:
                btn_register();
                break;
            case R.id.tv_sign_in:
                Intent intent = new Intent(RegirActivity.this, LoginActivity.class);
//                intent.putExtra("name",name);
                startActivity(intent);
                break;
        }
    }

    private void btn_register() {
        name = etUsername.getText().toString();
        pwssed1 = etPassword.getText().toString();
        passed2 = etPassword2.getText().toString();
        if (name.isEmpty() && pwssed1.isEmpty() && passed2.isEmpty()) {
            Toast.makeText(RegirActivity.this, "请输入用户名与密码", Toast.LENGTH_LONG).show();
        } else {
            if (etPassword.equals(etPassword2)) {
                Toast.makeText(RegirActivity.this, "密码不一致，请重新输入", Toast.LENGTH_LONG).show();
            } else {
                mPresenter.getRegir(name, pwssed1, passed2);

            }
        }
    }

    @Override
    protected void initView() {




    }

    @Override
    protected int createLayout() {
        return R.layout.activity_regir;
    }

    @Override
    public void loginSuccess(LoginData loginData) {
        Intent intent = new Intent(RegirActivity.this, LoginActivity.class);
//        intent.putExtra("name", username);
        startActivity(intent);
        Toast.makeText(RegirActivity.this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginFiler(String error) {
        Toast.makeText(RegirActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    protected RegirPresenter<RegirContract.RegirView> createPresenter() {
        return new RegirPresenter<>();
    }
}
