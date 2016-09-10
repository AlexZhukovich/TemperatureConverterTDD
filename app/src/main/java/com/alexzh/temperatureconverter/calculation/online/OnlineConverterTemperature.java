package com.alexzh.temperatureconverter.calculation.online;

import com.alexzh.temperatureconverter.calculation.ConvertTemperatureRepository;
import com.alexzh.temperatureconverter.model.ConvertedResult;
import com.alexzh.temperatureconverter.model.InputData;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedError;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedSuccessful;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineConverterTemperature implements ConvertTemperatureRepository {
    private TemperatureConverterApiService mApiService;
    private EventBus mEventBus;

    @Inject
    public OnlineConverterTemperature(TemperatureConverterApiService apiService, EventBus eventBus) {
        this.mApiService = apiService;
        this.mEventBus = eventBus;
    }

    @Override
    public void convertData(final InputData inputData) {
        mApiService.getConvertedData(
                String.valueOf(inputData.getInputValue()),
                inputData.getFromUnit().toString(),
                inputData.getToUnit().toString()).enqueue(new Callback<ConvertedResult>() {
                    @Override
                    public void onResponse(Call<ConvertedResult> call, Response<ConvertedResult> response) {
                        int code = response.code();
                        if (code == 200) {
                            mEventBus.post(new TemperatureConvertedSuccessful(new ConvertedResult(response.body().getResult(), inputData.getToUnit(), inputData)));
                        } else {
                            mEventBus.post(new TemperatureConvertedError("Response error"));
                        }
                    }

                    @Override
                    public void onFailure(Call<ConvertedResult> call, Throwable t) {
                        mEventBus.post(new TemperatureConvertedError("ERROR"));
            }
        });
    }
}
