package com.alexzh.temperatureconverter.di;

import android.app.Application;

import com.alexzh.temperatureconverter.di.scope.TemperatureConverterActivityModule;
import com.alexzh.temperatureconverter.di.scope.TemperatureConverterComponent;

public class TemperatureConverterApp extends Application {
    private ApplicationComponent mAppComponent;
    private TemperatureConverterComponent mTempConverterComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return mAppComponent;
    }

    public TemperatureConverterComponent createTemperatureConverterComponent() {
        mTempConverterComponent = mAppComponent.plus(new TemperatureConverterActivityModule());
        return mTempConverterComponent;
    }

    public void releaseTemperatureConverterComponent() {
        mTempConverterComponent = null;
    }
}
