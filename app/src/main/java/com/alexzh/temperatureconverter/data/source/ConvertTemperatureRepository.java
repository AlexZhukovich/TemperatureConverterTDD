package com.alexzh.temperatureconverter.data.source;

import com.alexzh.temperatureconverter.converter.usecase.ConvertTemperatureUseCase;
import com.alexzh.temperatureconverter.data.InputData;

public interface ConvertTemperatureRepository {

    void convertData(InputData inputData, ConvertTemperatureUseCase.Callback callback);
}
