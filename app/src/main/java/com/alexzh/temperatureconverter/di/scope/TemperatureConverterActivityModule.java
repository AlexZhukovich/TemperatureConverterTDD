package com.alexzh.temperatureconverter.di.scope;

import com.alexzh.temperatureconverter.presentation.converter.TemperatureConverterPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class TemperatureConverterActivityModule {

    @Provides
    public TemperatureConverterPresenter provideTemperatureConverterPresenter() {
        return new TemperatureConverterPresenter();
    }

}
