package com.example.daywanandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daywanandroid.activity.About;
import com.example.daywanandroid.activity.LoginActivity;
import com.example.daywanandroid.activity.Smater;
import com.example.daywanandroid.activity.link;
import com.example.daywanandroid.app.MyApplication;
import com.example.daywanandroid.base.BaseActivity;
import com.example.daywanandroid.fragment.DhFragment;
import com.example.daywanandroid.fragment.GzhFragment;
import com.example.daywanandroid.fragment.SyFragment;
import com.example.daywanandroid.fragment.XmFragment;
import com.example.daywanandroid.fragment.ZsFragment;
import com.example.daywanandroid.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.ta)
    TabLayout ta;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.fl)
    FrameLayout fl;

    private FragmentManager manager;
    private ArrayList<Fragment> fragments;
    private TextView tv_top;



    @Override
    protected void initData() {
//        ((MyPresenter) presenter).getIndex();
    }

    @Override
    protected MyPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        tb.setTitle("玩Android");
        setSupportActionBar(tb);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tb, R.string.app_name, R.string.app_name);
        toggle.syncState();

        fragments = new ArrayList<>();
        fragments.add(new SyFragment());
        fragments.add(new ZsFragment());
        fragments.add(new GzhFragment());
        fragments.add(new DhFragment());
        fragments.add(new XmFragment());

        ta.addTab(ta.newTab().setText("首页").setIcon(R.drawable.tab_select1));
        ta.addTab(ta.newTab().setText("知识体系").setIcon(R.drawable.tab_select2));
        ta.addTab(ta.newTab().setText("公众号").setIcon(R.drawable.tab_select3));
        ta.addTab(ta.newTab().setText("导航").setIcon(R.drawable.tab_select4));
        ta.addTab(ta.newTab().setText("项目").setIcon(R.drawable.tab_select5));

        initfra(fragments.get(0));

        View headerView = nv.getHeaderView(0);
        ImageView im_top = headerView.findViewById(R.id.im_top);
        //头像
        tv_top = headerView.findViewById(R.id.navigation_top);


        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        boolean b = login.getBoolean("statu", false);
      if (b) {
          String name = login.getString("name", "登陆");
            tv_top.setText(name);
       }


        nv.setItemIconTintList(null);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.navigation1) {
                    //收藏

                    startActivity(new Intent(context, link.class));
                }
                if (menuItem.getItemId() == R.id.navigation2) {
                    //YODO
                }
                if (menuItem.getItemId() == R.id.navigation3) {
                    //夜间模式
                    int current = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                    if (current == Configuration.UI_MODE_NIGHT_YES) {
                        ((AppCompatActivity) context).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);//设置为日间模式
                    } else {
                        ((AppCompatActivity) context).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);//设置为夜间模式
                    }
                    recreate();
                }
                if (menuItem.getItemId() == R.id.navigation4) {
                    //设置
                }
                if (menuItem.getItemId() == R.id.navigation5) {
                    //关于我们
                    Intent intent = new Intent(context, About.class);
                    startActivity(intent);
                }
                if (menuItem.getItemId() == R.id.navigation6) {
                    //退出登陆。

                }
                return false;
            }
        });



        if (tv_top.getText().toString().equals("登陆")) {

            //用户名，登陆
            tv_top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到登陆页面。
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }
            });

        }
//登陆成功后将用户名传过来，显示。
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if(name!=null){
            tv_top.setText(name);
        }

//        if (tv_top.getText().toString().equals(name)) {
//            tv_top.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
//                    boolean b = sharedPreferences.getBoolean("statu", false);
//                    if (b) {
//                        Toast.makeText(context, "已登陆", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Intent intent1 = new Intent(context, LoginActivity.class);
//                        startActivity(intent1);
//                    }
//                }
//            });
//        }

        ta.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        initfra(fragments.get(0));
                        tb.setTitle("玩Android");
                        break;

                    case 1:
                        initfra(fragments.get(1));
                        tb.setTitle("知识体系");
                        break;

                    case 2:
                        initfra(fragments.get(2));
                        tb.setTitle("公众号");
                        break;
                    case 3:
                        initfra(fragments.get(3));
                        tb.setTitle("导航");
                        break;
                    case 4:
                        initfra(fragments.get(4));
                        tb.setTitle("项目");
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initfra(Fragment fragments) {
        if (manager == null) {
            manager = getSupportFragmentManager();
        }
        FragmentTransaction transaction = manager.beginTransaction();

        if (!fragments.isAdded()) {
            List<Fragment> fgs = manager.getFragments();
            transaction.addToBackStack(null);
            if (fgs.size() > 0) {
                for (Fragment ff : fgs) {
                    transaction.hide(ff);
                }

            }
            transaction.add(R.id.fl, fragments);

        } else {
            List<Fragment> fgs = manager.getFragments();
            if (fgs.size() > 0) {
                for (Fragment fra : fgs) {
                    transaction.hide(fra);
                }
            }
            transaction.show(fragments);
        }
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ml) {
            startActivity(new Intent(context, Smater.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


}
