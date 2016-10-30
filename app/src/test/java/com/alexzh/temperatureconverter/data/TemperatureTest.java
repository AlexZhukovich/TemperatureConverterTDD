package com.alexzh.temperatureconverter.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class TemperatureTest {
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
        assertEquals(mCelsius, Temperature.valueOf(CELSIUS_STR.toUpperCase()));
    }

    @Test
    public void shouldVerifyFahrenheitObject() {
        assertNotNull(mFahrenheit);
        assertEquals(FAHRENHEIT_STR, mFahrenheit.toString());
        assertEquals(mFahrenheit, Temperature.valueOf(FAHRENHEIT_STR.toUpperCase()));
    }

    @Test
    public void shouldVerifyKelvinObject() {
        assertNotNull(mKelvin);
        assertEquals(KELVIN_STR, mKelvin.toString());
        assertEquals(mKelvin, Temperature.valueOf(KELVIN_STR.toUpperCase()));
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

    @Test
    public void shouldVerifyValuesMethod() {
        final Temperature[] values = new Temperature[]{
                Temperature.CELSIUS,
                Temperature.FAHRENHEIT,
                Temperature.KELVIN
        };
        assertArrayEquals(values, Temperature.values());
    }

}
