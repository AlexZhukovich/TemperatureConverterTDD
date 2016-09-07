package com.alexzh.temperatureconverter.model.event;

import com.alexzh.temperatureconverter.model.ConvertedResult;

public class TemperatureConvertedSuccessful {

    private ConvertedResult mResult;

    public TemperatureConvertedSuccessful(ConvertedResult result) {
        this.mResult = result;
    }

    public ConvertedResult getResult() {
        return mResult;
    }

    @Override
    public String toString() {
        return "TemperatureConvertedSuccessful{" +
                "mResult=" + mResult +
                '}';
    }
}
