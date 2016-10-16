package com.alexzh.temperatureconverter.presentation.converter;

import com.alexzh.temperatureconverter.interactor.ConvertTemperature;
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
    private ConvertTemperature mConvertTemperature;

    @Inject
    public TemperatureConverterPresenter(ConvertTemperature convertTemperature, EventBus eventBus) {
        this.mConvertTemperature = convertTemperature;
        this.mEventBus = eventBus;
    }

    @Override
    public void attachView(TemperatureConverterView mvpView) {
        this.mView = mvpView;
        this.mEventBus.register(this);
    }

    public void convertTemperature() {
        if (mView != null) {
            mView.displayProgress();
            try {
                double inputValue = Double.valueOf(mView.getInputValue());
                Temperature from = mView.getFromTemperatureUnit();
                Temperature to = mView.getToTemperatureUnit();
                mConvertTemperature.execute(new InputData(inputValue, from, to));
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
        mView.hideProgress();
    }

    @Subscribe
    public void onEvent(TemperatureConvertedError event) {
        mView.displayErrorMessage(event.getMessage());
        mView.setOutputValue(0.0);
        mView.hideProgress();
    }

    @Override
    public void detachView() {
        this.mEventBus.unregister(this);
        this.mView = null;
    }
}
