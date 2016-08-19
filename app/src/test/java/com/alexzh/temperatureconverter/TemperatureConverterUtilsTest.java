package com.alexzh.temperatureconverter;

import com.alexzh.temperatureconverter.model.Temperature;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class TemperatureConverterUtilsTest {
    private final static double CELSIUS_TEMPERATURE = 36.0d;
    private final static double FAHRENHEIT_TEMPERATURE = 96.8d;
    private final static double KELVIN_TEMPERATURE = 309.15d;

    private TemperatureConverterUtils mConverter;
    private Temperature mFrom;
    private Temperature mTo;


    @Before
    public void setup() {
        mConverter = new TemperatureConverterUtils();
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(mConverter);
    }

    @Test
    public void shouldConvertFromCelsiusToFahrenheit() {
        mFrom = Temperature.CELSIUS;
        mTo = Temperature.FAHRENHEIT;

        assertEquals(FAHRENHEIT_TEMPERATURE, mConverter.convert(CELSIUS_TEMPERATURE, mFrom, mTo), 0.0001);
    }

    @Test
    public void shouldConvertFromFahrenheitToCelsius() {
        mFrom = Temperature.FAHRENHEIT;
        mTo = Temperature.CELSIUS;

        assertEquals(CELSIUS_TEMPERATURE, mConverter.convert(FAHRENHEIT_TEMPERATURE, mFrom, mTo), 0.0001);
    }

    @Test
    public void shouldConvertFromCelsiusToKelvin() {
        mFrom = Temperature.CELSIUS;
        mTo = Temperature.KELVIN;

        assertEquals(KELVIN_TEMPERATURE, mConverter.convert(CELSIUS_TEMPERATURE, mFrom, mTo), 0.0001);
    }

    @Test
    public void shouldConvertFromKelvinToCelsius() {
        mFrom = Temperature.KELVIN;
        mTo = Temperature.CELSIUS;

        assertEquals(CELSIUS_TEMPERATURE, mConverter.convert(KELVIN_TEMPERATURE, mFrom, mTo), 0.0001);
    }

    @Test
    public void shouldConvertFromFahrenheitToKelvin() {
        mFrom = Temperature.FAHRENHEIT;
        mTo = Temperature.KELVIN;

        assertEquals(KELVIN_TEMPERATURE, mConverter.convert(FAHRENHEIT_TEMPERATURE, mFrom, mTo), 0.0001);
    }

    @Test
    public void shouldConvertFromKelvinToFahrenheit() {
        mFrom = Temperature.KELVIN;
        mTo = Temperature.FAHRENHEIT;

        assertEquals(FAHRENHEIT_TEMPERATURE, mConverter.convert(KELVIN_TEMPERATURE, mFrom, mTo), 0.0001);
    }

    @Test
    public void shouldConvertToTheSameTemperature() {
        assertEquals(CELSIUS_TEMPERATURE, mConverter.convert(CELSIUS_TEMPERATURE, Temperature.CELSIUS, Temperature.CELSIUS), 0.0001);
        assertEquals(FAHRENHEIT_TEMPERATURE, mConverter.convert(FAHRENHEIT_TEMPERATURE, Temperature.FAHRENHEIT, Temperature.FAHRENHEIT), 0.0001);
        assertEquals(KELVIN_TEMPERATURE, mConverter.convert(KELVIN_TEMPERATURE, Temperature.KELVIN, Temperature.KELVIN), 0.0001);
    }
}
