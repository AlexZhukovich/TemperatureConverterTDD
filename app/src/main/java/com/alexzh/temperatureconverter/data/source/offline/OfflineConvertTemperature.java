package com.alexzh.temperatureconverter.data.source.offline;

import com.alexzh.temperatureconverter.converter.usecase.ConvertTemperatureUseCase;
import com.alexzh.temperatureconverter.data.ConvertedResult;
import com.alexzh.temperatureconverter.data.InputData;
import com.alexzh.temperatureconverter.data.Temperature;
import com.alexzh.temperatureconverter.data.TemperatureConvertedError;
import com.alexzh.temperatureconverter.data.TemperatureConvertedSuccessful;
import com.alexzh.temperatureconverter.data.source.ConvertTemperatureRepository;

import javax.inject.Inject;

public class OfflineConvertTemperature implements ConvertTemperatureRepository {
    public final static String CALCULATION_ERROR = "Calculation error";

    @Inject
    public OfflineConvertTemperature() {
    }

    @Override
    public void convertData(InputData inputData, ConvertTemperatureUseCase.Callback callback) {
        if (inputData != null && inputData.getFromUnit() != null && inputData.getToUnit() != null) {
            if (inputData.getFromUnit().equals(Temperature.CELSIUS)
                    && inputData.getToUnit().equals(Temperature.FAHRENHEIT)) {

                callback.onResult(createTemperatureSuccessfulValue(
                        inputData, convertFromCelsiusToFahrenheit(inputData.getInputValue())));
            } else if (inputData.getFromUnit().equals(Temperature.CELSIUS)
                    && inputData.getToUnit().equals(Temperature.KELVIN)) {

                callback.onResult(createTemperatureSuccessfulValue(
                        inputData, convertFromCelsiusToKelvin(inputData.getInputValue())));
            } else if (inputData.getFromUnit().equals(Temperature.FAHRENHEIT)
                    && inputData.getToUnit().equals(Temperature.CELSIUS)) {

                callback.onResult(createTemperatureSuccessfulValue(
                        inputData, convertFromFahrenheitToCelsius(inputData.getInputValue())));
            } else if (inputData.getFromUnit().equals(Temperature.FAHRENHEIT)
                    && inputData.getToUnit().equals(Temperature.KELVIN)) {

                callback.onResult(createTemperatureSuccessfulValue(
                        inputData, convertFromFahrenheitToKelvin(inputData.getInputValue())));
            } else if (inputData.getFromUnit().equals(Temperature.KELVIN)
                    && inputData.getToUnit().equals(Temperature.CELSIUS)) {

                callback.onResult(createTemperatureSuccessfulValue(
                        inputData, convertFromKelvinToCelsius(inputData.getInputValue())));
            } else if (inputData.getFromUnit().equals(Temperature.KELVIN)
                    && inputData.getToUnit().equals(Temperature.FAHRENHEIT)) {

                callback.onResult(createTemperatureSuccessfulValue(
                        inputData, convertFromKelvinToFahrenheit(inputData.getInputValue())));
            } else {
                callback.onResult(createTemperatureSuccessfulValue(inputData, inputData.getInputValue()));
            }
        } else {
            callback.onError(createTemperatureErrorValue());
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