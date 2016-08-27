package com.alexzh.temperatureconverter.presentation.base;

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}