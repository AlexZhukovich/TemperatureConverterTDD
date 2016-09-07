package com.alexzh.temperatureconverter.calculation.offline;

import com.alexzh.temperatureconverter.calculation.ConvertTemperatureRepository;
import com.alexzh.temperatureconverter.model.ConvertedResult;
import com.alexzh.temperatureconverter.model.InputData;
import com.alexzh.temperatureconverter.model.Temperature;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedError;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedSuccessful;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public class OfflineConvertTemperature implements ConvertTemperatureRepository {
    public final static String CALCULATION_ERROR = "Calculation error";

    private EventBus mEventBus;

    @Inject
    public OfflineConvertTemperature(EventBus eventBus) {
        this.mEventBus = eventBus;
    }

    @Override
    public void convertData(InputData inputData) {
        if (inputData != null && inputData.getFromUnit() != null && inputData.getToUnit() != null) {
            if (inputData.getFromUnit().equals(Temperature.CELSIUS) && inputData.getToUnit().equals(Temperature.FAHRENHEIT)) {
                mEventBus.post(createTemperatureSuccessfulValue(inputData, convertFromCelsiusToFahrenheit(inputData.getInputValue())));
            } else if (inputData.getFromUnit().equals(Temperature.CELSIUS) && inputData.getToUnit().equals(Temperature.KELVIN)) {
                mEventBus.post(createTemperatureSuccessfulValue(inputData, convertFromCelsiusToKelvin(inputData.getInputValue())));
            } else if (inputData.getFromUnit().equals(Temperature.FAHRENHEIT) && inputData.getToUnit().equals(Temperature.CELSIUS)) {
                mEventBus.post(createTemperatureSuccessfulValue(inputData, convertFromFahrenheitToCelsius(inputData.getInputValue())));
            } else if (inputData.getFromUnit().equals(Temperature.FAHRENHEIT) && inputData.getToUnit().equals(Temperature.KELVIN)) {
                mEventBus.post(createTemperatureSuccessfulValue(inputData, convertFromFahrenheitToKelvin(inputData.getInputValue())));
            } else if (inputData.getFromUnit().equals(Temperature.KELVIN) && inputData.getToUnit().equals(Temperature.CELSIUS)) {
                mEventBus.post(createTemperatureSuccessfulValue(inputData, convertFromKelvinToCelsius(inputData.getInputValue())));
            } else if (inputData.getFromUnit().equals(Temperature.KELVIN) && inputData.getToUnit().equals(Temperature.FAHRENHEIT)) {
                mEventBus.post(createTemperatureSuccessfulValue(inputData, convertFromKelvinToFahrenheit(inputData.getInputValue())));
            } else {
                mEventBus.post(createTemperatureSuccessfulValue(inputData, inputData.getInputValue()));
            }
        } else {
            mEventBus.post(createTemperatureErrorValue());
        }
    }

    protected double convertFromKelvinToFahrenheit(double temperature) {
        return round((temperature * 9 / 5) - 459.67);
    }

    protected double convertFromKelvinToCelsius(double temperature) {
        return round(temperature - 273.15d);
    }

    protected double convertFromFahrenheitToKelvin(double temperature) {
        return round((temperature + 459.67) * 5 / 9);
    }

    protected double convertFromFahrenheitToCelsius(double temperature) {
        return round((temperature - 32) / 1.8d);
    }

    protected double convertFromCelsiusToKelvin(double temperature) {
        return round(temperature + 273.15d);
    }

    protected double convertFromCelsiusToFahrenheit(double temperature) {
        return round((temperature * 1.8d) + 32);
    }

    protected double round(double value) {
        return (value * 100) / 100;
    }

    protected TemperatureConvertedSuccessful createTemperatureSuccessfulValue(InputData inputData, double value) {
        return new TemperatureConvertedSuccessful(
                    new ConvertedResult(
                        value,
                        inputData.getToUnit(),
                        inputData));
    }

    protected TemperatureConvertedError createTemperatureErrorValue() {
        return new TemperatureConvertedError(CALCULATION_ERROR);
    }
}