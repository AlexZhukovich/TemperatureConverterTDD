package com.alexzh.temperatureconverter.data;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ConvertedResultTest {
    private final static double RESULT_VALUE = 112.3;
    private final static double CUSTOM_RESULT_VALUE = 172.2;
    private final static double INPUT_VALUE = 234.2;
    private final static double CUSTOM_INPUT_VALUE = 112.2;
    private final static double DELTA = 0.0000001;

    private final static Temperature CELSIUS_UNIT = Temperature.CELSIUS;
    private final static Temperature KELVIN_UNIT = Temperature.KELVIN;

    private InputData mInputData;

    private ConvertedResult mResult;

    @Before
    public void setup() {
        mInputData = new InputData(INPUT_VALUE, KELVIN_UNIT, CELSIUS_UNIT);
        mResult = new ConvertedResult(RESULT_VALUE, CELSIUS_UNIT, mInputData);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(mInputData);
        assertNotNull(mResult);
    }

    @Test
    public void shouldVerifyGetters() {
        assertEquals(RESULT_VALUE, mResult.getResult(), DELTA);
        assertEquals(CELSIUS_UNIT, mResult.getResultUnit());
        assertEquals(mInputData, mResult.getInputData());
    }

    @Test
    public void shouldVerifyEquals() {
        ConvertedResult newDefaultResult = new ConvertedResult(RESULT_VALUE, CELSIUS_UNIT, mInputData);
        ConvertedResult customResult = new ConvertedResult(CUSTOM_RESULT_VALUE, KELVIN_UNIT, mInputData);

        assertTrue(mResult.equals(mResult));

        assertFalse(mResult.equals(null));
        assertFalse(mResult.equals(new String()));

        assertTrue(mResult.equals(newDefaultResult));

        assertFalse(customResult.equals(mResult));
        assertFalse(customResult.hashCode() == mResult.hashCode());

        customResult = new ConvertedResult(RESULT_VALUE, KELVIN_UNIT, mInputData);
        assertFalse(customResult.equals(mResult));
        assertFalse(customResult.hashCode() == mResult.hashCode());

        customResult = new ConvertedResult(RESULT_VALUE, CELSIUS_UNIT, mInputData);
        assertTrue(customResult.equals(mResult));
        assertTrue(customResult.hashCode() == mResult.hashCode());

        customResult = new ConvertedResult(RESULT_VALUE, CELSIUS_UNIT, null);
        assertFalse(customResult.equals(mResult));
        assertFalse(customResult.hashCode() == mResult.hashCode());

        mInputData = new InputData(CUSTOM_INPUT_VALUE, KELVIN_UNIT, CELSIUS_UNIT);
        customResult = new ConvertedResult(RESULT_VALUE, CELSIUS_UNIT, mInputData);
        assertFalse(customResult.equals(mResult));
        assertFalse(customResult.hashCode() == mResult.hashCode());

        customResult = new ConvertedResult(INPUT_VALUE, null, mInputData);
        assertFalse(customResult.equals(mResult));
        assertFalse(customResult.hashCode() == mInputData.hashCode());

        mResult = new ConvertedResult(RESULT_VALUE, CELSIUS_UNIT, null);
        customResult = new ConvertedResult(RESULT_VALUE, CELSIUS_UNIT, null);
        assertTrue(customResult.equals(mResult));
        assertTrue(customResult.hashCode() == mResult.hashCode());
    }

    @Test
    public void shouldVerifyToString() {
        String toString = String.format(Locale.getDefault(),
                "%s{result=%.1f, resultUnit=%s, inputData=%s{value=%.1f, fromUnit=%s, toUnit=%s}}",
                ConvertedResult.class.getSimpleName(),
                RESULT_VALUE,
                CELSIUS_UNIT,
                InputData.class.getSimpleName(),
                INPUT_VALUE,
                KELVIN_UNIT,
                CELSIUS_UNIT);

        assertEquals(toString, mResult.toString());
    }
}
