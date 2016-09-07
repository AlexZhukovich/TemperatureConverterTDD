package com.alexzh.temperatureconverter.presentation;

import com.alexzh.temperatureconverter.model.Temperature;
import com.alexzh.temperatureconverter.presentation.converter.TemperatureConverterPresenter;
import com.alexzh.temperatureconverter.presentation.converter.TemperatureConverterView;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
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

    private TemperatureConverterView mView;
    private TemperatureConverterPresenter mPresenter;
    private EventBus mEventBus;

    @Before
    public void setup() {
        mView = mock(TemperatureConverterView.class);
        mEventBus = mock(EventBus.class);

        mPresenter = new TemperatureConverterPresenter(mEventBus);

        when(mView.getInputValue()).thenReturn(INPUT_VALUE_STR);
        when(mView.getFromTemperatureUnit()).thenReturn(FROM_TEMPERATURE_UNIT);
        when(mView.getToTemperatureUnit()).thenReturn(TO_TEMPERATURE_UNIT);
    }

    @Test
    public void shouldVerifyConvertDataSuccessfulWithCorrectView() {
        mPresenter.attachView(mView);
        mPresenter.convertTemperature();
        mPresenter.detachView();

        verify(mView, times(1)).getInputValue();
        verify(mView, times(1)).getFromTemperatureUnit();
        verify(mView, times(1)).getToTemperatureUnit();
        verify(mEventBus, times(1)).post(any());
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

    @Test
    public void shouldVerifyOpenSettingsWithCorrectView() {
        mPresenter.attachView(mView);
        mPresenter.openSettings();
        mPresenter.detachView();

        verify(mView, never()).getInputValue();
        verify(mView, never()).getFromTemperatureUnit();
        verify(mView, never()).getToTemperatureUnit();
        verify(mView, never()).setOutputValue(anyDouble());
        verify(mView, times(1)).launchSettingsActivity();
    }

    @Test
    public void shouldVerifyOpenSettingsWithNullView() {
        mPresenter.attachView(null);
        mPresenter.openSettings();
        mPresenter.detachView();

        verify(mView, never()).getInputValue();
        verify(mView, never()).getFromTemperatureUnit();
        verify(mView, never()).getToTemperatureUnit();
        verify(mView, never()).setOutputValue(anyDouble());
        verify(mView, never()).launchSettingsActivity();
    }
}
