package com.alexzh.temperatureconverter;

import com.alexzh.temperatureconverter.model.Temperature;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class TemperatureEnumTest {
    private final static String CELSIUS_STR = "Celsius";
    private final static String FAHRENHEIT_STR = "Fahrenheit";
    private final static String KELVIN_STR = "Kelvin";
    private Temperature mCelsius;
    private Temperature mFahrenheit;
    private Temperature mKelvin;

    @Before
    public void setup() {
        mCelsius = Temperature.CELSIUS;
        mFahrenheit = Temperature.FAHRENHEIT;
        mKelvin = Temperature.KELVIN;
    }

    @Test
    public void shouldVerifyCelsiusObject() {
        assertNotNull(mCelsius);
        assertEquals(CELSIUS_STR, mCelsius.toString());
    }

    @Test
    public void shouldVerifyFahrenheitObject() {
        assertNotNull(mFahrenheit);
        assertEquals(FAHRENHEIT_STR, mFahrenheit.toString());
    }

    @Test
    public void shouldVerifyKelvinObject() {
        assertNotNull(mKelvin);
        assertEquals(KELVIN_STR, mKelvin.toString());
    }

    @Test
    public void shouldVerifyComparingObjects() {
        assertNotEquals(mCelsius, mFahrenheit);
        assertNotEquals(mCelsius, mKelvin);

        assertNotEquals(mFahrenheit, mCelsius);
        assertNotEquals(mFahrenheit, mKelvin);

        assertNotEquals(mKelvin, mCelsius);
        assertNotEquals(mKelvin, mFahrenheit);
    }

}
