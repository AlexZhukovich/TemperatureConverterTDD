package com.alexzh.temperatureconverter.di;

import android.app.Application;

import dagger.Module;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }
}
