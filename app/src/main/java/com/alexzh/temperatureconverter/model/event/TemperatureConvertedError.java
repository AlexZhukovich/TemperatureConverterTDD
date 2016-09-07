package com.alexzh.temperatureconverter.model.event;

public class TemperatureConvertedError {
    private String mMessage;

    public TemperatureConvertedError(String message) {
        this.mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }

    @Override
    public String toString() {
        return "TemperatureConvertedError{" +
                "mMessage='" + mMessage + '\'' +
                '}';
    }
}
