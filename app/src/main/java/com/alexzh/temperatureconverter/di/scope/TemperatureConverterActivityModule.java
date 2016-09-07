package com.alexzh.temperatureconverter.di.scope;

import com.alexzh.temperatureconverter.presentation.converter.TemperatureConverterPresenter;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class TemperatureConverterActivityModule {

    @Provides
    public TemperatureConverterPresenter provideTemperatureConverterPresenter(EventBus eventBus) {
        return new TemperatureConverterPresenter(eventBus);
    }

    @Provides
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

}
