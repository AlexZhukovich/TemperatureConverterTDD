package com.alexzh.temperatureconverter.di;

import android.app.Application;

import com.alexzh.temperatureconverter.TemperatureConverterPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    public TemperatureConverterPresenter provideTemperatureConverterPresenter() {
        return new TemperatureConverterPresenter();
    }
}
