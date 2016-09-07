package com.alexzh.temperatureconverter.model.event;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TemperatureConvertedErrorTest {
    private final static String ERROR_MESSAGE = "Error message";
    private final static String CONNECT_ERROR_MESSAGE = "Connect error message";

    private TemperatureConvertedError mError;

    @Before
    public void setup() {
        mError = new TemperatureConvertedError(ERROR_MESSAGE);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(mError);
    }

    @Test
    public void shouldVerifyGetMessage() {
        assertEquals(ERROR_MESSAGE, mError.getMessage());

        mError = new TemperatureConvertedError(CONNECT_ERROR_MESSAGE);
        assertEquals(CONNECT_ERROR_MESSAGE, mError.getMessage());
    }

    @Test
    public void shouldVerityToString() {
        final String toString = String.format(
                Locale.getDefault(), "%s{mMessage='%s'}",
                TemperatureConvertedError.class.getSimpleName(),
                ERROR_MESSAGE);
        assertEquals(toString, mError.toString());
    }

}
