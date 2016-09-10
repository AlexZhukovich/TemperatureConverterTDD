package com.alexzh.temperatureconverter.presentation.converter;

import com.alexzh.temperatureconverter.calculation.ConverterTemperatureFactory;
import com.alexzh.temperatureconverter.model.InputData;
import com.alexzh.temperatureconverter.model.Temperature;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedError;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedSuccessful;
import com.alexzh.temperatureconverter.presentation.base.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class TemperatureConverterPresenter implements MvpPresenter<TemperatureConverterView> {
    private TemperatureConverterView mView;
    private EventBus mEventBus;
    private ConverterTemperatureFactory mConverterTemperatureFactory;

    @Inject
    public TemperatureConverterPresenter(ConverterTemperatureFactory converterTemperatureFactory, EventBus eventBus) {
        this.mConverterTemperatureFactory = converterTemperatureFactory;
        this.mEventBus = eventBus;
    }

    @Override
    public void attachView(TemperatureConverterView mvpView) {
        this.mView = mvpView;
        this.mEventBus.register(this);
    }

    public void convertTemperature() {
        if (mView != null) {
            try {
                double inputValue = Double.valueOf(mView.getInputValue());
                Temperature from = mView.getFromTemperatureUnit();
                Temperature to = mView.getToTemperatureUnit();
                mConverterTemperatureFactory.getTemperatureConverter().convertData(new InputData(inputValue, from, to));
            } catch (NumberFormatException ex) {
                mEventBus.post(new TemperatureConvertedError("ERROR"));
            }
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
