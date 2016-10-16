package com.alexzh.temperatureconverter.di.scope;

import com.alexzh.temperatureconverter.calculation.ConverterTemperatureFactory;
import com.alexzh.temperatureconverter.executor.InteractorExecutor;
import com.alexzh.temperatureconverter.executor.MainThreadExecutor;
import com.alexzh.temperatureconverter.interactor.ConvertTemperature;
import com.alexzh.temperatureconverter.interactor.ConvertTemperatureImpl;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class TemperatureConverterActivityModule {

    @Provides
    public ConvertTemperature provideConvertTemperature(EventBus eventBus,
                                                        ConverterTemperatureFactory converterFactory,
                                                        InteractorExecutor interactorExecutor,
                                                        MainThreadExecutor mainThreadExecutor) {
        return new ConvertTemperatureImpl(eventBus, converterFactory, interactorExecutor, mainThreadExecutor);
    }
}
