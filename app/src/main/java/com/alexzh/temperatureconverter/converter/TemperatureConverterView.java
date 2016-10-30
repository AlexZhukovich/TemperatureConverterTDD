package com.alexzh.temperatureconverter.converter;

import com.alexzh.temperatureconverter.base.MvpView;
import com.alexzh.temperatureconverter.data.Temperature;

public interface TemperatureConverterView extends MvpView {

    String getInputValue();

    Temperature getFromTemperatureUnit();

    Temperature getToTemperatureUnit();

    void displayResult(double value);

    void displayProgress();

    void hideProgress();

    void launchSettingsActivity();

    void displayErrorMessage(String message);
}
