package com.alexzh.temperatureconverter.data.source.offline;

import com.alexzh.temperatureconverter.converter.usecase.ConvertTemperatureUseCase;
import com.alexzh.temperatureconverter.data.InputData;
import com.alexzh.temperatureconverter.data.Temperature;
import com.alexzh.temperatureconverter.data.TemperatureConvertedError;
import com.alexzh.temperatureconverter.data.TemperatureConvertedSuccessful;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OfflineConvertTemperatureTest {
    private final static double CELSIUS_TEMPERATURE = 36.0d;
    private final static double FAHRENHEIT_TEMPERATURE = 96.8d;
    private final static double KELVIN_TEMPERATURE = 309.15d;

    @Mock
    private EventBus mEventBus;

    @Mock
    private ConvertTemperatureUseCase.Callback mCallback;

    private OfflineConvertTemperature mConverter;
    private InputData mInputData;
    private TemperatureConvertedSuccessful mSuccessful;
    private TemperatureConvertedError mError;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mConverter = new OfflineConvertTemperature();
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

        mConverter.convertData(mInputData, mCallback);
        assertEquals(FAHRENHEIT_TEMPERATURE, result, 0.0001);
        verify(mCallback).onResult(mSuccessful);
    }

    @Test
    public void shouldConvertFromFahrenheitToCelsius() {
        double result = mConverter.convertFromFahrenheitToCelsius(FAHRENHEIT_TEMPERATURE);

        mInputData = new InputData(FAHRENHEIT_TEMPERATURE, Temperature.FAHRENHEIT, Temperature.CELSIUS);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, result);

        mConverter.convertData(mInputData, mCallback);
        assertEquals(CELSIUS_TEMPERATURE, result, 0.0001);
        verify(mCallback).onResult(mSuccessful);
    }

    @Test
    public void shouldConvertFromCelsiusToKelvin() {
        double result = mConverter.convertFromCelsiusToKelvin(CELSIUS_TEMPERATURE);

        mInputData = new InputData(CELSIUS_TEMPERATURE, Temperature.CELSIUS, Temperature.KELVIN);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, result);

        mConverter.convertData(mInputData, mCallback);
        assertEquals(KELVIN_TEMPERATURE, result, 0.0001);
        verify(mCallback).onResult(mSuccessful);
    }

    @Test
    public void shouldConvertFromKelvinToCelsius() {
        double result = mConverter.convertFromKelvinToCelsius(KELVIN_TEMPERATURE);

        mInputData = new InputData(KELVIN_TEMPERATURE, Temperature.KELVIN, Temperature.CELSIUS);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, result);

        mConverter.convertData(mInputData, mCallback);
        assertEquals(CELSIUS_TEMPERATURE, result, 0.0001);
        verify(mCallback).onResult(mSuccessful);
    }

    @Test
    public void shouldConvertFromFahrenheitToKelvin() {
        double result = mConverter.convertFromFahrenheitToKelvin(FAHRENHEIT_TEMPERATURE);

        mInputData = new InputData(FAHRENHEIT_TEMPERATURE, Temperature.FAHRENHEIT, Temperature.KELVIN);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, result);

        mConverter.convertData(mInputData, mCallback);
        assertEquals(KELVIN_TEMPERATURE, result, 0.0001);
        verify(mCallback).onResult(mSuccessful);
    }

    @Test
    public void shouldConvertFromKelvinToFahrenheit() {
        double result = mConverter.convertFromKelvinToFahrenheit(KELVIN_TEMPERATURE);

        mInputData = new InputData(KELVIN_TEMPERATURE, Temperature.KELVIN, Temperature.FAHRENHEIT);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, result);

        mConverter.convertData(mInputData, mCallback);
        assertEquals(FAHRENHEIT_TEMPERATURE, result, 0.0001);
        verify(mCallback).onResult(mSuccessful);
    }

    @Test
    public void shouldConvertIncorrectData() {
        mConverter.convertData(null, mCallback);
        verify(mCallback, times(1)).onError(mError);

        mInputData = new InputData(CELSIUS_TEMPERATURE, null, Temperature.KELVIN);
        mConverter.convertData(mInputData, mCallback);
        verify(mCallback, times(2)).onError(mError);

        mInputData = new InputData(CELSIUS_TEMPERATURE, Temperature.CELSIUS, null);
        mConverter.convertData(mInputData, mCallback);
        verify(mCallback, times(3)).onError(mError);

        mInputData = new InputData(CELSIUS_TEMPERATURE, null, null);
        mConverter.convertData(mInputData, mCallback);
        verify(mCallback, times(4)).onError(mError);
    }

    @Test
    public void shouldConvertToTheSameTemperature() {
        mInputData = new InputData(CELSIUS_TEMPERATURE, Temperature.CELSIUS, Temperature.CELSIUS);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, CELSIUS_TEMPERATURE);
        mConverter.convertData(mInputData, mCallback);
        verify(mCallback).onResult(mSuccessful);

        mInputData = new InputData(FAHRENHEIT_TEMPERATURE, Temperature.FAHRENHEIT, Temperature.FAHRENHEIT);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, FAHRENHEIT_TEMPERATURE);
        mConverter.convertData(mInputData, mCallback);
        verify(mCallback).onResult(mSuccessful);

        mInputData = new InputData(KELVIN_TEMPERATURE, Temperature.KELVIN, Temperature.KELVIN);
        mSuccessful = mConverter.createTemperatureSuccessfulValue(mInputData, KELVIN_TEMPERATURE);
        mConverter.convertData(mInputData, mCallback);
        verify(mCallback).onResult(mSuccessful);
    }
}
