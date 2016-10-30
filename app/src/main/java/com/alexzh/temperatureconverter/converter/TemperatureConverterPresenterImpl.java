package com.alexzh.temperatureconverter.converter;

import com.alexzh.temperatureconverter.converter.usecase.ConvertTemperatureUseCase;
import com.alexzh.temperatureconverter.data.InputData;
import com.alexzh.temperatureconverter.data.Temperature;
import com.alexzh.temperatureconverter.data.TemperatureConvertedError;
import com.alexzh.temperatureconverter.data.TemperatureConvertedSuccessful;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class TemperatureConverterPresenterImpl implements TemperatureConverterPresenter {
    private TemperatureConverterView mView;
    private EventBus mEventBus;
    private ConvertTemperatureUseCase mConvertTemperatureUseCase;

    public TemperatureConverterPresenterImpl(ConvertTemperatureUseCase convertTemperature, EventBus eventBus) {
        this.mConvertTemperatureUseCase = convertTemperature;
        this.mEventBus = eventBus;
    }

    @Override
    public void attachView(TemperatureConverterView mvpView) {
        this.mView = mvpView;
        this.mEventBus.register(this);
    }

    @Override
    public void convertTemperature(String temperatureValue, Temperature fromUnit, Temperature toUnit) {
        if (mView != null) {
            mView.displayProgress();
            try {
                double inputValue = Double.valueOf(temperatureValue);
                mConvertTemperatureUseCase.execute(new InputData(inputValue, fromUnit, toUnit));
            } catch (NumberFormatException ex) {
                mEventBus.post(new TemperatureConvertedError("ERROR"));
            }
        }
    }

    @Override
    public void openSettings() {
        if (mView != null) {
            mView.launchSettingsActivity();
        }
    }

    @Subscribe
    public void onEvent(TemperatureConvertedSuccessful event) {
        mView.displayResult(event.getResult().getResult());
        mView.hideProgress();
    }

    @Subscribe
    public void onEvent(TemperatureConvertedError event) {
        mView.displayErrorMessage(event.getMessage());
        mView.displayResult(0.0);
        mView.hideProgress();
    }

    @Override
    public void detachView() {
        this.mEventBus.unregister(this);
        this.mView = null;
    }
}