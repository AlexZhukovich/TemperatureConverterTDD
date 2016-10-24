package com.alexzh.temperatureconverter.di.scope;

import com.alexzh.temperatureconverter.calculation.ConverterTemperatureFactory;
import com.alexzh.temperatureconverter.executor.InteractorExecutor;
import com.alexzh.temperatureconverter.executor.MainThreadExecutor;
import com.alexzh.temperatureconverter.interactor.ConvertTemperatureUseCase;
import com.alexzh.temperatureconverter.interactor.ConvertTemperatureUseCaseImpl;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class TemperatureConverterActivityModule {

    @Provides
    public ConvertTemperatureUseCase provideConvertTemperature(EventBus eventBus,
                                                               ConverterTemperatureFactory converterFactory,
                                                               InteractorExecutor interactorExecutor,
                                                               MainThreadExecutor mainThreadExecutor) {
        return new ConvertTemperatureUseCaseImpl(eventBus, converterFactory, interactorExecutor, mainThreadExecutor);
    }
}
