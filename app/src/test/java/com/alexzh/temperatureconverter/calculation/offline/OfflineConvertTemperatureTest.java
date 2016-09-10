package com.alexzh.temperatureconverter.calculation.offline;

import com.alexzh.temperatureconverter.model.InputData;
import com.alexzh.temperatureconverter.model.Temperature;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedError;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedSuccessful;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OfflineConvertTemperatureTest {
    private final static double CELSIUS_TEMPERATURE = 36.0d;
    private final static double FAHRENHEIT_TEMPERATURE = 96.8d;
    private final static double KELVIN_TEMPERATURE = 309.15d;

    private OfflineConvertTemperature mConverter;
    private InputData mInputData;
    private TemperatureConvertedSuccessful mSuccessful;
    private TemperatureConvertedError mError;
    private EventBus mEventBus;


    @Before
    public void setup() {
        mEventBus = mock(EventBus.class);
        mConverter = new OfflineConvertTemperature(mEventBus);
        mError = mConverter.createTemperatureErrorValue();
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(mConverter);
    }

    @Test
    public void shouldConvertFromCelsiusToFahrenheit() {
        double result = mConverter.convertFromCelsiusToFahrenheit(CELSIUS_TEMPERATURE);

        mInputData = new InputData(CELSIUS_TEMPERATURE, Temperature.CELSIUS, Temperature.FAHRENHEIT);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, result);

        mConverter.convertData(mInputData);
        assertEquals(FAHRENHEIT_TEMPERATURE, result, 0.0001);
        verify(mEventBus).post(mSuccessful);
    }

    @Test
    public void shouldConvertFromFahrenheitToCelsius() {
        double result = mConverter.convertFromFahrenheitToCelsius(FAHRENHEIT_TEMPERATURE);

        mInputData = new InputData(FAHRENHEIT_TEMPERATURE, Temperature.FAHRENHEIT, Temperature.CELSIUS);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, result);

        mConverter.convertData(mInputData);
        assertEquals(CELSIUS_TEMPERATURE, result, 0.0001);
        verify(mEventBus).post(mSuccessful);
    }

    @Test
    public void shouldConvertFromCelsiusToKelvin() {
        double result = mConverter.convertFromCelsiusToKelvin(CELSIUS_TEMPERATURE);

        mInputData = new InputData(CELSIUS_TEMPERATURE, Temperature.CELSIUS, Temperature.KELVIN);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, result);

        mConverter.convertData(mInputData);
        assertEquals(KELVIN_TEMPERATURE, result, 0.0001);
        verify(mEventBus).post(mSuccessful);
    }

    @Test
    public void shouldConvertFromKelvinToCelsius() {
        double result = mConverter.convertFromKelvinToCelsius(KELVIN_TEMPERATURE);

        mInputData = new InputData(KELVIN_TEMPERATURE, Temperature.KELVIN, Temperature.CELSIUS);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, result);

        mConverter.convertData(mInputData);
        assertEquals(CELSIUS_TEMPERATURE, result, 0.0001);
        verify(mEventBus).post(mSuccessful);
    }

    @Test
    public void shouldConvertFromFahrenheitToKelvin() {
        double result = mConverter.convertFromFahrenheitToKelvin(FAHRENHEIT_TEMPERATURE);

        mInputData = new InputData(FAHRENHEIT_TEMPERATURE, Temperature.FAHRENHEIT, Temperature.KELVIN);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, result);

        mConverter.convertData(mInputData);
        assertEquals(KELVIN_TEMPERATURE, result, 0.0001);
        verify(mEventBus).post(mSuccessful);
    }

    @Test
    public void shouldConvertFromKelvinToFahrenheit() {
        double result = mConverter.convertFromKelvinToFahrenheit(KELVIN_TEMPERATURE);

        mInputData = new InputData(KELVIN_TEMPERATURE, Temperature.KELVIN, Temperature.FAHRENHEIT);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, result);

        mConverter.convertData(mInputData);
        assertEquals(FAHRENHEIT_TEMPERATURE, result, 0.0001);
        verify(mEventBus).post(mSuccessful);
    }

    @Test
    public void shouldConvertIncorrectData() {
        mConverter.convertData(null);
        verify(mEventBus, times(1)).post(mError);

        mInputData = new InputData(CELSIUS_TEMPERATURE, null, Temperature.KELVIN);
        mConverter.convertData(mInputData);
        verify(mEventBus, times(2)).post(mError);

        mInputData = new InputData(CELSIUS_TEMPERATURE, Temperature.CELSIUS, null);
        mConverter.convertData(mInputData);
        verify(mEventBus, times(3)).post(mError);

        mInputData = new InputData(CELSIUS_TEMPERATURE, null, null);
        mConverter.convertData(mInputData);
        verify(mEventBus, times(4)).post(mError);
    }

    @Test
    public void shouldConvertToTheSameTemperature() {
        mInputData = new InputData(CELSIUS_TEMPERATURE, Temperature.CELSIUS, Temperature.CELSIUS);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, CELSIUS_TEMPERATURE);
        mConverter.convertData(mInputData);
        verify(mEventBus).post(mSuccessful);

        mInputData = new InputData(FAHRENHEIT_TEMPERATURE, Temperature.FAHRENHEIT, Temperature.FAHRENHEIT);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, FAHRENHEIT_TEMPERATURE);
        mConverter.convertData(mInputData);
        verify(mEventBus).post(mSuccessful);

        mInputData = new InputData(KELVIN_TEMPERATURE, Temperature.KELVIN, Temperature.KELVIN);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, KELVIN_TEMPERATURE);
        mConverter.convertData(mInputData);
        verify(mEventBus).post(mSuccessful);
    }
}