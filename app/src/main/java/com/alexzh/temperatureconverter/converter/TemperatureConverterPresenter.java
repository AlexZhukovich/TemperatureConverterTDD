package com.alexzh.temperatureconverter.converter;

import com.alexzh.temperatureconverter.base.MvpPresenter;
import com.alexzh.temperatureconverter.data.Temperature;

public interface TemperatureConverterPresenter extends MvpPresenter<TemperatureConverterView> {

    void convertTemperature(String temperatureValue, Temperature fromUnit, Temperature toUnit);

    void openSettings();
}
