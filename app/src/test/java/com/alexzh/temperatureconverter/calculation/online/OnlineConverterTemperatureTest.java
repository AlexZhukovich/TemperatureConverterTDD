package com.alexzh.temperatureconverter.calculation.online;

import com.alexzh.temperatureconverter.model.ConvertedResult;
import com.alexzh.temperatureconverter.model.InputData;
import com.alexzh.temperatureconverter.model.Temperature;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedError;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedSuccessful;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OnlineConverterTemperatureTest {
    private OnlineConverterTemperature mConverter;
    private InputData mInputData;
    private ConvertedResult mConvertedResult;

    @Mock
    private EventBus mEventBus;

    @Mock
    private TemperatureConverterApiService mApiService;

    @Mock
    private Call<ConvertedResult> mCall;

    @Mock
    private ResponseBody mResponseBody;

    @Captor
    ArgumentCaptor<Callback<ConvertedResult>> argumentCapture;

    @Before
    public void setup() {
        mConverter = new OnlineConverterTemperature(mApiService, mEventBus);
        mInputData = new InputData(100, Temperature.CELSIUS, Temperature.KELVIN);
        mConvertedResult = new ConvertedResult(10, Temperature.KELVIN, mInputData);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(mConverter);
        assertNotNull(mConvertedResult);
    }

    @Test
    public void shouldGetConvertedResult() {
        when(mApiService.getConvertedData(anyString(), anyString(), anyString())).thenReturn(mCall);
        Response<ConvertedResult> response = Response.success(mConvertedResult);

        mConverter.convertData(mInputData);
        verify(mCall).enqueue(argumentCapture.capture());
        argumentCapture.getValue().onResponse(null, response);

        verify(mEventBus).post(new TemperatureConvertedSuccessful(mConvertedResult));
    }

    @Test
    public void shouldGetIncorrectConvertedResults() {
        when(mApiService.getConvertedData(anyString(), anyString(), anyString())).thenReturn(mCall);
        Response<ConvertedResult> response = Response.error(500, mResponseBody);

        mConverter.convertData(mInputData);
        verify(mCall).enqueue(argumentCapture.capture());
        argumentCapture.getValue().onResponse(null, response);

        verify(mEventBus).post(new TemperatureConvertedError("Response error"));
    }

    @Test
    public void shouldGetFailure() {
        when(mApiService.getConvertedData(anyString(), anyString(), anyString())).thenReturn(mCall);
        Throwable throwable = new Throwable(new RuntimeException());

        mConverter.convertData(mInputData);
        verify(mCall).enqueue(argumentCapture.capture());
        argumentCapture.getValue().onFailure(null, throwable);

        verify(mEventBus).post(new TemperatureConvertedError("ERROR"));
    }

}
