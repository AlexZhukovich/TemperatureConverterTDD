package com.alexzh.temperatureconverter.calculation;

import com.alexzh.temperatureconverter.interactor.ConvertTemperatureUseCase;
import com.alexzh.temperatureconverter.model.InputData;

public interface ConvertTemperatureRepository {

    void convertData(InputData inputData, ConvertTemperatureUseCase.Callback callback);
}
