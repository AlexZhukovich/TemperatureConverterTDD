package com.alexzh.temperatureconverter.di.scope;

import com.alexzh.temperatureconverter.presentation.converter.TemperatureConverterActivity;

import dagger.Subcomponent;

@TemperatureConverterActivityScope
@Subcomponent(modules = {TemperatureConverterActivityModule.class})
public interface TemperatureConverterComponent {
    void inject(TemperatureConverterActivity target);
}
