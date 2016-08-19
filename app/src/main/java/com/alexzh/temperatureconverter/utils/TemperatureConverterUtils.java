package com.alexzh.temperatureconverter.utils;

import com.alexzh.temperatureconverter.model.Temperature;

public class TemperatureConverterUtils {

    public static double convert(double temperature, Temperature from, Temperature to) {
        if (from.equals(Temperature.CELSIUS) && to.equals(Temperature.FAHRENHEIT)) {
            return convertFromCelsiusToFahrenheit(temperature);
        } else if (from.equals(Temperature.CELSIUS) && to.equals(Temperature.KELVIN)) {
            return convertFromCelsiusToKelvin(temperature);
        } else if (from.equals(Temperature.FAHRENHEIT) && to.equals(Temperature.CELSIUS)) {
            return convertFromFahrenheitToCelsius(temperature);
        } else if (from.equals(Temperature.FAHRENHEIT) && to.equals(Temperature.KELVIN)) {
            return convertFromFahrenheitToKelvin(temperature);
        } else if (from.equals(Temperature.KELVIN) && to.equals(Temperature.CELSIUS)) {
            return convertFromKelvinToCelsius(temperature);
        } else if (from.equals(Temperature.KELVIN) && to.equals(Temperature.FAHRENHEIT)) {
            return convertFromKelvinToFahrenheit(temperature);
        }
        return temperature;
    }

    private static double convertFromKelvinToFahrenheit(double temperature) {
        return round((temperature * 9 / 5) - 459.67);
    }

    private static double convertFromKelvinToCelsius(double temperature) {
        return round(temperature - 273.15d);
    }

    private static double convertFromFahrenheitToKelvin(double temperature) {
        return round((temperature + 459.67) * 5 / 9);
    }

    private static double convertFromFahrenheitToCelsius(double temperature) {
        return round((temperature - 32) / 1.8d);
    }

    private static double convertFromCelsiusToKelvin(double temperature) {
        return round(temperature + 273.15d);
    }

    private static double convertFromCelsiusToFahrenheit(double temperature) {
        return round((temperature * 1.8d) + 32);
    }

    private static double round(double value) {
        return (value * 100) / 100;
    }
}