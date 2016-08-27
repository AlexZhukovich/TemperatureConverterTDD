package com.alexzh.temperatureconverter.di;

import com.alexzh.temperatureconverter.di.scope.TemperatureConverterActivityModule;
import com.alexzh.temperatureconverter.di.scope.TemperatureConverterComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class})
public interface ApplicationComponent {
    void inject(TemperatureConverterApp target);

    TemperatureConverterComponent plus(TemperatureConverterActivityModule target);
}
