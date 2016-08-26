package com.alexzh.temperatureconverter.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class InputDataTest {
    private final static double INPUT_VALUE = 234.2;
    private final static double CUSTOM_INPUT_VALUE = 112.2;
    private final static double DELTA = 0.0000001;

    private final static Temperature CELSIUS_UNIT = Temperature.CELSIUS;
    private final static Temperature KELVIN_UNIT = Temperature.KELVIN;

    private InputData mInputData;

    @Before
    public void setup() {
        mInputData = new InputData(INPUT_VALUE, CELSIUS_UNIT, KELVIN_UNIT);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(mInputData);
    }

    @Test
    public void shouldVerifyGetters() {
        assertEquals(INPUT_VALUE, mInputData.getInputValue(), DELTA);
        assertEquals(CELSIUS_UNIT, mInputData.getFromUnit());
        assertEquals(KELVIN_UNIT, mInputData.getToUnit());
    }

    @Test
    public void shouldEqualObjects() {
        InputData newDefaultInputData = new InputData(INPUT_VALUE, CELSIUS_UNIT, KELVIN_UNIT);
        InputData customInputData = new InputData(CUSTOM_INPUT_VALUE, KELVIN_UNIT, CELSIUS_UNIT);

        assertTrue(mInputData.equals(mInputData));

        assertTrue(mInputData.equals(newDefaultInputData));

        assertFalse(customInputData.equals(mInputData));
        assertFalse(customInputData.hashCode() == mInputData.hashCode());
    }

    @Test
    public void shouldVerifyToString() {
        String toString = String.format("%s{value=%.1f, fromUnit=%s, toUnit=%s}",
                InputData.class.getSimpleName(),
                INPUT_VALUE,
                CELSIUS_UNIT,
                KELVIN_UNIT);

        assertEquals(toString, mInputData.toString());
    }
}
