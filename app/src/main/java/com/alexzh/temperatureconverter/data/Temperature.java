package com.alexzh.temperatureconverter.data;

public enum  Temperature {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit"),
    KELVIN("Kelvin");

    private final String mTitle;

    Temperature(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
