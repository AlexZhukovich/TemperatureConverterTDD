package com.alexzh.temperatureconverter.model.event;

import com.alexzh.temperatureconverter.model.ConvertedResult;
import com.alexzh.temperatureconverter.model.InputData;
import com.alexzh.temperatureconverter.model.Temperature;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TemperatureConvertedSuccessfulTest {
    private final static double INPUT_VALUE = 36.0d;
    private final static double RESULT_VALUE = 309.15d;
    private final static Temperature FROM_UNIT = Temperature.CELSIUS;
    private final static Temperature TO_UNIT = Temperature.KELVIN;
    private final static InputData INPUT_DATA = new InputData(INPUT_VALUE, FROM_UNIT, TO_UNIT);
    private final static InputData INPUT_DATA_INVERSE = new InputData(RESULT_VALUE, TO_UNIT, FROM_UNIT);
    private final static ConvertedResult CONVERTED_RESULT = new ConvertedResult(RESULT_VALUE, TO_UNIT, INPUT_DATA);
    private final static ConvertedResult CONVERTED_RESULT_INVERSE =
            new ConvertedResult(INPUT_VALUE, FROM_UNIT, INPUT_DATA_INVERSE);

    private TemperatureConvertedSuccessful mSuccess;

    @Before
    public void setup() {
        mSuccess = new TemperatureConvertedSuccessful(CONVERTED_RESULT);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(mSuccess);
    }

    @Test
    public void shouldVerifyGetResult() {
        assertEquals(CONVERTED_RESULT, mSuccess.getResult());

        mSuccess = new TemperatureConvertedSuccessful(CONVERTED_RESULT_INVERSE);
        assertEquals(CONVERTED_RESULT_INVERSE, mSuccess.getResult());
    }

    @Test
    public void shouldVerifyEquals() {
        TemperatureConvertedSuccessful newSuccess = new TemperatureConvertedSuccessful(CONVERTED_RESULT);
        TemperatureConvertedSuccessful customSuccess = new TemperatureConvertedSuccessful(CONVERTED_RESULT_INVERSE);

        assertTrue(mSuccess.equals(mSuccess));

        assertFalse(mSuccess.equals(null));
        assertFalse(mSuccess.equals(new String()));

        assertTrue(mSuccess.equals(newSuccess));

        assertFalse(customSuccess.equals(mSuccess));
        assertFalse(customSuccess.hashCode() == mSuccess.hashCode());

        customSuccess = new TemperatureConvertedSuccessful(null);
        assertFalse(customSuccess.equals(mSuccess));
        assertFalse(customSuccess.hashCode() == mSuccess.hashCode());

        newSuccess = new TemperatureConvertedSuccessful(null);
        assertTrue(customSuccess.equals(newSuccess));
    }

    @Test
    public void shouldVerifyToString() {
        final String toString = String.format(
                Locale.getDefault(), "%s{mResult=%s}",
                TemperatureConvertedSuccessful.class.getSimpleName(),
                CONVERTED_RESULT);
        assertEquals(toString, mSuccess.toString());
    }
}
