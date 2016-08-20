package com.alexzh.temperatureconverter;

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}