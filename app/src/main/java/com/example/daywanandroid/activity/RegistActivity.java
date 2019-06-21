package com.example.daywanandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daywanandroid.R;
import com.example.daywanandroid.base.BaseActivity;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.login.RegistContract;
import com.example.daywanandroid.model.bean.loginbean.RegistBean;
import com.example.daywanandroid.presenter.login.RegistPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity implements RegistContract.View {
    @BindView(R.id.regis_username)
    EditText regisUsername;
    @BindView(R.id.regist_password)
    EditText registPassword;
    @BindView(R.id.regist_repassword)
    EditText registRepassword;
    @BindView(R.id.btn_regist)
    Button btnRegist;

    @Override
    protected void initData() {

    }

    @Override
    protected IPrestenter createPresenter() {
        return new RegistPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_regist;
    }

    @Override
    public void registerReturn(RegistBean registerBean) {
        if (registerBean.getErrorCode() == 0) {
            //去登陆
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            //提示用户注册。
            Toast.makeText(context, registerBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_regist)
    public void onViewClicked() {
        //注册
        String regist_name = regisUsername.getText().toString();
        String regist_password = registPassword.getText().toString();
        String regist_repassword = registRepassword.getText().toString();
        if (!TextUtils.isEmpty(regist_name)&&!TextUtils.isEmpty(regist_password)&&!TextUtils.isEmpty(regist_repassword)
        &&regist_password.equals(regist_repassword)){
            //回调结果。
            ((RegistPresenter)presenter).register(regist_name,regist_password,regist_repassword);
            Toast.makeText(context, "注册成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,LoginActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(context, "用户名或密码格式不正确！", Toast.LENGTH_SHORT).show();
        }
    }
}
