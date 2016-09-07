package com.alexzh.temperatureconverter.calculation;

import com.alexzh.temperatureconverter.model.ConvertedResult;
import com.alexzh.temperatureconverter.model.InputData;

public interface ConvertTemperatureRepository {

    ConvertedResult convertData(InputData inputData);
}
