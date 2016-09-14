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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof TemperatureConvertedSuccessful))) return false;

        TemperatureConvertedSuccessful that = (TemperatureConvertedSuccessful) o;

        return mResult != null ? mResult.equals(that.mResult) : that.mResult == null;

    }

    @Override
    public int hashCode() {
        return mResult != null ? mResult.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TemperatureConvertedSuccessful{" +
                "mResult=" + mResult +
                '}';
    }
}
