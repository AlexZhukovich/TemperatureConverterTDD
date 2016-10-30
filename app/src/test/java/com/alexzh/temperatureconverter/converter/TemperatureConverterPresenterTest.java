package com.alexzh.temperatureconverter.converter;

import com.alexzh.temperatureconverter.converter.usecase.ConvertTemperatureUseCase;
import com.alexzh.temperatureconverter.data.source.ConverterTemperatureFactory;
import com.alexzh.temperatureconverter.data.source.offline.OfflineConvertTemperature;
import com.alexzh.temperatureconverter.data.InputData;
import com.alexzh.temperatureconverter.data.Temperature;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureConverterPresenterTest {
    private final static String INPUT_VALUE_STR = "12.5";
    private final static Temperature FROM_TEMPERATURE_UNIT = Temperature.CELSIUS;
    private final static Temperature TO_TEMPERATURE_UNIT = Temperature.FAHRENHEIT;

    @Mock
    private TemperatureConverterView mView;

    @Mock
    private EventBus mEventBus;

    @Mock
    private ConverterTemperatureFactory mTemperatureFactory;

    @Mock
    private ConvertTemperatureUseCase mConvertTemperature;

    @Mock
    private OfflineConvertTemperature mConverter;

    private TemperatureConverterPresenter mPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new TemperatureConverterPresenterImpl(mConvertTemperature, mEventBus);
    }

    @Test
    public void shouldVerifyConvertDataSuccessfulWithCorrectView() {
        when(mTemperatureFactory.getTemperatureConverter()).thenReturn(mConverter);
        doNothing().when(mConverter).convertData(any(InputData.class), any(ConvertTemperatureUseCase.Callback.class));

        mPresenter.attachView(mView);
        mPresenter.convertTemperature(INPUT_VALUE_STR, FROM_TEMPERATURE_UNIT, TO_TEMPERATURE_UNIT);
        mPresenter.detachView();

        verify(mEventBus).register(any());
        verify(mView).displayProgress();
        verify(mEventBus).unregister(any());
    }

    @Test
    public void shouldVerifyConvertDataWithNullView() {
        mPresenter.attachView(null);
        mPresenter.convertTemperature(INPUT_VALUE_STR, FROM_TEMPERATURE_UNIT, TO_TEMPERATURE_UNIT);
        mPresenter.detachView();

        verify(mView, never()).displayResult(anyDouble());
    }

    @Test
    public void shouldVerifyOpenSettingsWithCorrectView() {
        mPresenter.attachView(mView);
        mPresenter.openSettings();
        mPresenter.detachView();

        verify(mView, never()).displayResult(anyDouble());
        verify(mView, times(1)).launchSettingsActivity();
    }

    @Test
    public void shouldVerifyOpenSettingsWithNullView() {
        mPresenter.attachView(null);
        mPresenter.openSettings();
        mPresenter.detachView();

        verify(mView, never()).displayResult(anyDouble());
        verify(mView, never()).launchSettingsActivity();
    }
}
