package com.alexzh.temperatureconverter.data.source.online;

import com.alexzh.temperatureconverter.converter.usecase.ConvertTemperatureUseCase;
import com.alexzh.temperatureconverter.data.ConvertedResult;
import com.alexzh.temperatureconverter.data.InputData;
import com.alexzh.temperatureconverter.data.TemperatureConvertedError;
import com.alexzh.temperatureconverter.data.TemperatureConvertedSuccessful;
import com.alexzh.temperatureconverter.data.source.ConvertTemperatureRepository;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineConverterTemperature implements ConvertTemperatureRepository {
    private TemperatureConverterApiService mApiService;

    @Inject
    public OnlineConverterTemperature(TemperatureConverterApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public void convertData(final InputData inputData, final ConvertTemperatureUseCase.Callback callback) {
        mApiService.getConvertedData(
                String.valueOf(inputData.getInputValue()),
                inputData.getFromUnit().toString(),
                inputData.getToUnit().toString()).enqueue(new Callback<ConvertedResult>() {
            @Override
            public void onResponse(Call<ConvertedResult> call, Response<ConvertedResult> response) {
                int code = response.code();
                if (code == 200) {
                    callback.onResult(new TemperatureConvertedSuccessful(
                            new ConvertedResult(
                                    response.body().getResult(),
                                    inputData.getToUnit(),
                                    inputData)));
                } else {
                    callback.onError(new TemperatureConvertedError("Response error"));
                }
            }

            @Override
            public void onFailure(Call<ConvertedResult> call, Throwable t) {
                callback.onError(new TemperatureConvertedError("ERROR"));
            }
        });
    }
}
