package com.alexzh.temperatureconverter.di;

import com.alexzh.temperatureconverter.TemperatureConverterActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class})
public interface ApplicationComponent {
    void inject(TemperatureConverterActivity target);
}
