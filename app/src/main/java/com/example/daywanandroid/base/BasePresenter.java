package com.example.daywanandroid.base;


import com.example.daywanandroid.interfaces.IBaseView;
import com.example.daywanandroid.interfaces.IPrestenter;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<v extends IBaseView> implements IPrestenter<v> {
    //弱引用 view
protected v myview;
    //弱引用 view
private WeakReference<v> weakReference;
    //rxjava2 数据加载的时候，界面回收一起的数据内存泄漏
private CompositeDisposable compositeDisposable;
    @Override
    public void attchView(v View) {
     weakReference=new WeakReference<>(View);
     this.myview=weakReference.get();
    }
    //解绑view的关联，同时解绑数据加载的关闭，避免出现内存泄漏
    @Override
    public void detathView() {
this.myview=null;
        unSubscribe();
    }
    //解绑观察者和被观察者
    private void unSubscribe() {
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }
    //添加观察者和被观察者的操作类
    protected void addSubscribe(Disposable disposable){
        if (compositeDisposable==null)
            compositeDisposable=new CompositeDisposable();
            compositeDisposable.add(disposable);

    }
}
