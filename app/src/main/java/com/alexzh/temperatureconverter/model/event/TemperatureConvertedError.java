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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof TemperatureConvertedError))) return false;

        TemperatureConvertedError that = (TemperatureConvertedError) o;

        return mMessage != null ? mMessage.equals(that.mMessage) : that.mMessage == null;

    }

    @Override
    public int hashCode() {
        return mMessage != null ? mMessage.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TemperatureConvertedError{" +
                "mMessage='" + mMessage + '\'' +
                '}';
    }
}
