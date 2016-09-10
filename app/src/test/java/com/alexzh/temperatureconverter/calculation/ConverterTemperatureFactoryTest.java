package com.alexzh.temperatureconverter.calculation;

import com.alexzh.temperatureconverter.calculation.offline.OfflineConvertTemperature;
import com.alexzh.temperatureconverter.calculation.online.OnlineConverterTemperature;
import com.alexzh.temperatureconverter.service.SharedPreferenceManager;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConverterTemperatureFactoryTest {

    @Mock
    private EventBus mEventBus;

    @InjectMocks
    private OnlineConverterTemperature mOnlineConverter;

    @Mock
    private OfflineConvertTemperature mOfflineConverter;

    @Mock
    private SharedPreferenceManager mPreferenceManager;

    private ConverterTemperatureFactory mConverterFactory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mConverterFactory = new ConverterTemperatureFactory(mPreferenceManager);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(mConverterFactory);
    }

    @Test
    public void shouldGetOnlineConverter() {
        when(mPreferenceManager.isConvertOnline()).thenReturn(true);

        mConverterFactory.getTemperatureConverter();

        verify(mPreferenceManager).isConvertOnline();
        assertEquals(OnlineConverterTemperature.class,
                mConverterFactory.getTemperatureConverter().getClass());
    }

    @Test
    public void shouldGetOfflineConverter() {
        when(mPreferenceManager.isConvertOnline()).thenReturn(false);

        mConverterFactory.getTemperatureConverter();

        verify(mPreferenceManager).isConvertOnline();
        assertEquals(OfflineConvertTemperature.class,
                mConverterFactory.getTemperatureConverter().getClass());
    }
}
