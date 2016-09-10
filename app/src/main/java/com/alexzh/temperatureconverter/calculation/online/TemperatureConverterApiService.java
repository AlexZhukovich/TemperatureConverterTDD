package com.alexzh.temperatureconverter.calculation.online;

import com.alexzh.temperatureconverter.model.ConvertedResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TemperatureConverterApiService {

    @GET("convert")
    Call<ConvertedResult> getConvertedData(@Query("value") String value, @Query("fromUnit") String fromUnits, @Query("toUnit") String toUnits);
}
