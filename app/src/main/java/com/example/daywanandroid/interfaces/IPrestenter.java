package com.example.daywanandroid.interfaces;

public interface IPrestenter<v extends IBaseView> {
    void attchView(v view);
    void detathView();
}
