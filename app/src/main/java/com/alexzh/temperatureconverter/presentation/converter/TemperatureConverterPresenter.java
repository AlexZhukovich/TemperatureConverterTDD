package com.alexzh.temperatureconverter.presentation.converter;

import com.alexzh.temperatureconverter.calculation.offline.OfflineConvertTemperature;
import com.alexzh.temperatureconverter.model.InputData;
import com.alexzh.temperatureconverter.model.Temperature;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedError;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedSuccessful;
import com.alexzh.temperatureconverter.presentation.base.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class TemperatureConverterPresenter implements MvpPresenter<TemperatureConverterView> {
    private TemperatureConverterView mView;
    private EventBus mEventBus;

    public TemperatureConverterPresenter(EventBus eventBus) {
        this.mEventBus = eventBus;
    }

    @Override
    public void attachView(TemperatureConverterView mvpView) {
        this.mView = mvpView;
        this.mEventBus.register(this);
    }

    public void convertTemperature() {
        if (mView != null) {
            OfflineConvertTemperature converter = new OfflineConvertTemperature(mEventBus);

            double inputValue = Double.valueOf(mView.getInputValue());
            Temperature from = mView.getFromTemperatureUnit();
            Temperature to = mView.getToTemperatureUnit();
            converter.convertData(new InputData(inputValue, from, to));
        }
    }

    public void openSettings() {
        if (mView != null) {
            mView.launchSettingsActivity();
        }
    }

    @Subscribe
    public void onEvent(TemperatureConvertedSuccessful event) {
        mView.setOutputValue(event.getResult().getResult());
    }

    @Subscribe
    public void onEvent(TemperatureConvertedError event) {
        mView.displayErrorMessage(event.getMessage());
    }

    @Override
    public void detachView() {
        this.mEventBus.unregister(this);
        this.mView = null;
    }
}
