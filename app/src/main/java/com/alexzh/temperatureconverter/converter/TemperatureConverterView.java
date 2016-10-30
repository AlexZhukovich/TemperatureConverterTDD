package com.alexzh.temperatureconverter.converter;

import com.alexzh.temperatureconverter.base.MvpView;

public interface TemperatureConverterView extends MvpView {

    void displayResult(double value);

    void displayProgress();

    void hideProgress();

    void launchSettingsActivity();

    void displayErrorMessage(String message);
}
