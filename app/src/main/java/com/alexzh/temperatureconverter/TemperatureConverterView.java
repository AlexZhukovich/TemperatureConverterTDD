package com.alexzh.temperatureconverter;

import com.alexzh.temperatureconverter.model.Temperature;

public interface TemperatureConverterView extends MvpView {

    String getInputValue();

    Temperature getFromTemperatureUnit();

    Temperature getToTemperatureUnit();

    void setOutputValue(double value);
}
