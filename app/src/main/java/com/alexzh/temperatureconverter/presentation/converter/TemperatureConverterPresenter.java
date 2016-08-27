package com.alexzh.temperatureconverter.presentation.converter;

import com.alexzh.temperatureconverter.model.Temperature;
import com.alexzh.temperatureconverter.presentation.base.MvpPresenter;
import com.alexzh.temperatureconverter.utils.TemperatureConverterUtils;

public class TemperatureConverterPresenter implements MvpPresenter<TemperatureConverterView> {
    private TemperatureConverterView mView;

    @Override
    public void attachView(TemperatureConverterView mvpView) {
        this.mView = mvpView;
    }

    public void convertTemperature() {
        if (mView != null) {
            double inputValue = Double.valueOf(mView.getInputValue());
            Temperature from = mView.getFromTemperatureUnit();
            Temperature to = mView.getToTemperatureUnit();

            double result = TemperatureConverterUtils.convert(inputValue, from, to);
            mView.setOutputValue(result);
        }
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
