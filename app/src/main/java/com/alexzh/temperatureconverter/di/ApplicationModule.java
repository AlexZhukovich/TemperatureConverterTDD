package com.alexzh.temperatureconverter.di;

import android.app.Application;

import com.alexzh.temperatureconverter.presentation.converter.TemperatureConverterPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }
}
