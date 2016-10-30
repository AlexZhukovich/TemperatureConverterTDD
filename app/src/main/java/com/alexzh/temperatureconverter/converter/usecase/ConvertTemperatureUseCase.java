package com.alexzh.temperatureconverter.converter.usecase;

import com.alexzh.temperatureconverter.data.InputData;
import com.alexzh.temperatureconverter.data.TemperatureConvertedError;
import com.alexzh.temperatureconverter.data.TemperatureConvertedSuccessful;

public interface ConvertTemperatureUseCase {

    void execute(InputData data);

    interface Callback {

        void onResult(TemperatureConvertedSuccessful convertedSuccessful);

        void onError(TemperatureConvertedError convertedError);
    }
}