package com.example.daywanandroid.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daywanandroid.MainActivity;
import com.example.daywanandroid.R;
import com.example.daywanandroid.base.BaseActivity;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.login.LoginContract;
import com.example.daywanandroid.model.bean.loginbean.LoginBean;
import com.example.daywanandroid.presenter.login.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.Go_zc)
    TextView GoZc;
    @BindView(R.id.ch_login)
    CheckBox chLogin;
    @BindView(R.id.fh)
    TextView fh;

    @Override
    protected void initData() {

    }

    @Override
    protected IPrestenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void loginReturn(LoginBean loginBean) {
        if (loginBean.getErrorCode() == 0) {
            //跳转到主页面
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("name", loginBean.getData().getUsername());
            startActivity(intent);
        } else {
            //提示用户登陆。
            Toast.makeText(context, loginBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    //登陆
    @OnClick({R.id.btn_login, R.id.Go_zc,R.id.fh})
    public void onViewClicked() {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            ((LoginPresenter) presenter).login(username, password);
            if (chLogin.isChecked()) {
                SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name", username);
                editor.putString("pws", password);
                editor.putBoolean("statu", true);
                editor.commit();
//            }else {
//                SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putString("name", username);
//                editor.putBoolean("statu", true);
//                editor.commit();
            }
            Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "用户名或密码不正确，请重新登陆！", Toast.LENGTH_SHORT).show();
        }
        //跳转到注册
        GoZc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegistActivity.class);
                startActivity(intent);
            }
        });
        //返回侧滑
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
