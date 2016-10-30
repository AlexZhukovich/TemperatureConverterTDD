package com.alexzh.temperatureconverter.di.api;

import com.alexzh.temperatureconverter.data.source.online.TemperatureConverterApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiModule {

    @Provides @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://temperatureconverterapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides @Singleton
    public TemperatureConverterApiService provideTemperatureConverterApiService(Retrofit retrofit) {
        return retrofit.create(TemperatureConverterApiService.class);
    }

}
