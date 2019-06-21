package com.example.daywanandroid.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.daywanandroid.FlowLayout;
import com.example.daywanandroid.R;
import com.example.daywanandroid.adapter.SmartAdapter;
import com.example.daywanandroid.base.BaseActivity;
import com.example.daywanandroid.interfaces.IPrestenter;
import com.example.daywanandroid.interfaces.smartContract.SmartContract;
import com.example.daywanandroid.model.bean.smartbean.SmartBean;
import com.example.daywanandroid.model.bean.smartbean.SmartRecrdBean;
import com.example.daywanandroid.presenter.smartpresenter.SamrtPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Smater extends BaseActivity implements SmartContract.View {

    @BindView(R.id.master_tb)
    Toolbar masterTb;
    @BindView(R.id.Smater_fl)
    FlowLayout SmaterFl;
    @BindView(R.id.rv_Smart)
    RecyclerView rvSmart;
    @BindView(R.id.et_smart)
    EditText etSmart;
    @BindView(R.id.im_smart)
    ImageView imSmart;
    private String[] color = {"#CADFFC", "#CAFCD2", "#EEF419", "#E93623", "#8094F0", "#F80BE4", "#F80B7A"};
    private SmartAdapter adapter;
    private SmartRecrdBean arrayList;
    private String name;

    @Override
    protected void initData() {
        ((SamrtPresenter) presenter).getSmartP();
    }

    @Override
    protected IPrestenter createPresenter() {
        return new SamrtPresenter();
    }


    @Override
    protected void initView() {
        masterTb.setTitle("发现更多");
        setSupportActionBar(masterTb);
        arrayList = new SmartRecrdBean(new ArrayList<String>());
        rvSmart.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SmartAdapter(this, arrayList.getStringss());
        rvSmart.setAdapter(adapter);


    }


    @Override
    protected int getLayout() {
        return R.layout.activity_smater;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void getSmart(SmartBean smartBean) {
        final List<SmartBean.DataBean> data = smartBean.getData();
        for (int i = 0; i < data.size(); i++) {
            name = data.get(i).getName();
            TextView textView = new TextView(context);
            textView.setText(name);
            LinearLayout.LayoutParams layoutParams = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                    .LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5, 5, 5, 5);
            textView.setLayoutParams(layoutParams);
            textView.setPadding(15, 10, 15, 10);
            textView.setBackground(getResources().getDrawable(R.drawable.resou));
            textView.setTextColor(Color.parseColor(color[new Random().nextInt(5)]));
            SmaterFl.addView(textView);

            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SmartFragmnet.class);
                    intent.putExtra("k", data.get(finalI).getName());
                    startActivity(intent);

                    if (arrayList.getStringss().contains(data.get(finalI).getName())) {
                        arrayList.getStringss().remove(data.get(finalI).getName());
                    } else {
                        arrayList.getStringss().add(data.get(finalI).getName());
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }


    }

    @OnClick(R.id.im_smart)
    public void onViewClicked() {
        String s = etSmart.getText().toString();
        arrayList.getStringss().add(s);
        adapter.notifyDataSetChanged();
        Intent intent = new Intent(context, SmartFragmnet.class);
        intent.putExtra("k", s);
        startActivity(intent);
    }
}
