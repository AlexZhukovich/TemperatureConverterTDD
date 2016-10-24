package com.alexzh.temperatureconverter.interactor;

import com.alexzh.temperatureconverter.model.InputData;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedError;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedSuccessful;

public interface ConvertTemperatureUseCase {

    void execute(InputData data);

    interface Callback {

        void onResult(TemperatureConvertedSuccessful convertedSuccessful);

        void onError(TemperatureConvertedError convertedError);
    }
}