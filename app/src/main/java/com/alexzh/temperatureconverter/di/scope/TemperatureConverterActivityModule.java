package com.alexzh.temperatureconverter.di.scope;

import com.alexzh.temperatureconverter.converter.TemperatureConverterPresenter;
import com.alexzh.temperatureconverter.converter.TemperatureConverterPresenterImpl;
import com.alexzh.temperatureconverter.converter.usecase.ConvertTemperatureUseCase;
import com.alexzh.temperatureconverter.converter.usecase.ConvertTemperatureUseCaseImpl;
import com.alexzh.temperatureconverter.data.source.ConverterTemperatureFactory;
import com.alexzh.temperatureconverter.executor.InteractorExecutor;
import com.alexzh.temperatureconverter.executor.MainThreadExecutor;

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

    @Provides
    public TemperatureConverterPresenter provideTemperatureConverterPresenter(ConvertTemperatureUseCase useCase,
                                                                              EventBus eventBus) {
        return new TemperatureConverterPresenterImpl(useCase, eventBus);
    }
}
