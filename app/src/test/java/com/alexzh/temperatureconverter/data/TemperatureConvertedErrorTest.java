package com.alexzh.temperatureconverter.data;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
    public void shouldVerifyEquals() {
        TemperatureConvertedError newError = new TemperatureConvertedError(ERROR_MESSAGE);
        TemperatureConvertedError customError = new TemperatureConvertedError(CONNECT_ERROR_MESSAGE);

        assertTrue(mError.equals(mError));

        assertFalse(mError.equals(null));
        assertFalse(mError.equals(new String()));

        assertTrue(mError.equals(newError));

        assertFalse(customError.equals(mError));
        assertFalse(customError.hashCode() == mError.hashCode());

        customError = new TemperatureConvertedError(null);
        assertFalse(customError.equals(mError));
        assertFalse(customError.hashCode() == mError.hashCode());

        newError = new TemperatureConvertedError(null);
        assertTrue(newError.equals(customError));
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
