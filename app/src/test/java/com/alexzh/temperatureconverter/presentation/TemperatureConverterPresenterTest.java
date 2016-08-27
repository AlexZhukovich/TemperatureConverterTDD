package com.alexzh.temperatureconverter.presentation;

import com.alexzh.temperatureconverter.model.Temperature;
import com.alexzh.temperatureconverter.presentation.converter.TemperatureConverterPresenter;
import com.alexzh.temperatureconverter.presentation.converter.TemperatureConverterView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureConverterPresenterTest {
    private final static String INPUT_VALUE_STR = "12.5";
    private final static Temperature FROM_TEMPERATURE_UNIT = Temperature.CELSIUS;
    private final static Temperature TO_TEMPERATURE_UNIT = Temperature.FAHRENHEIT;
    private final static double CORRECT_RESULT = 54.5d;

    private TemperatureConverterView mView;
    private TemperatureConverterPresenter mPresenter;

    @Before
    public void setup() {
        mView = mock(TemperatureConverterView.class);

        mPresenter = new TemperatureConverterPresenter();

        when(mView.getInputValue()).thenReturn(INPUT_VALUE_STR);
        when(mView.getFromTemperatureUnit()).thenReturn(FROM_TEMPERATURE_UNIT);
        when(mView.getToTemperatureUnit()).thenReturn(TO_TEMPERATURE_UNIT);
    }

    @Test
    public void shouldVerifyConvertDataWithCorrectView() {
        mPresenter.attachView(mView);
        mPresenter.convertTemperature();
        mPresenter.detachView();

        verify(mView, times(1)).getInputValue();
        verify(mView, times(1)).getFromTemperatureUnit();
        verify(mView, times(1)).getToTemperatureUnit();
        verify(mView, times(1)).setOutputValue(CORRECT_RESULT);
    }

    @Test
    public void shouldVerifyConvertDataWithNullView() {
        mPresenter.attachView(null);
        mPresenter.convertTemperature();
        mPresenter.detachView();

        verify(mView, never()).getInputValue();
        verify(mView, never()).getFromTemperatureUnit();
        verify(mView, never()).getToTemperatureUnit();
        verify(mView, never()).setOutputValue(anyDouble());
    }
}
