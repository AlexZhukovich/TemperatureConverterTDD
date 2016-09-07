package com.alexzh.temperatureconverter.presentation.converter;

import com.alexzh.temperatureconverter.model.Temperature;
import com.alexzh.temperatureconverter.presentation.base.MvpView;

public interface TemperatureConverterView extends MvpView {

    String getInputValue();

    Temperature getFromTemperatureUnit();

    Temperature getToTemperatureUnit();

    void setOutputValue(double value);

    void launchSettingsActivity();

    void displayErrorMessage(String message);
}
