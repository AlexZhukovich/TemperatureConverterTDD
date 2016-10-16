package com.alexzh.temperatureconverter.calculation;

import com.alexzh.temperatureconverter.interactor.ConvertTemperature;
import com.alexzh.temperatureconverter.model.InputData;

public interface ConvertTemperatureRepository {

    void convertData(InputData inputData, ConvertTemperature.Callback callback);
}
