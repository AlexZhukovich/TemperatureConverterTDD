package com.alexzh.temperatureconverter.di.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiModule {

    @Provides @Singleton
    public Retrofit provideRetrofir() {
        return new Retrofit.Builder()
                .baseUrl("https://temperatureconverterapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
