package com.alexzh.temperatureconverter.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.alexzh.temperatureconverter.executor.InteractorExecutor;
import com.alexzh.temperatureconverter.executor.MainThreadExecutor;
import com.alexzh.temperatureconverter.executor.MainThreadExecutorImp;
import com.alexzh.temperatureconverter.executor.ThreadExecutor;
import com.alexzh.temperatureconverter.utils.SharedPreferenceManager;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    @Provides @Singleton
    public Context provideContext() {
        return mApplication;
    }

    @Provides
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides @Singleton
    public SharedPreferenceManager provideSharedPreferenceManager(SharedPreferences sharedPreferences) {
        return new SharedPreferenceManager(sharedPreferences);
    }

    @Provides
    public InteractorExecutor provideInteractorExecutor() {
        return new ThreadExecutor();
    }

    @Provides
    public MainThreadExecutor provideMainThreadExecutor() {
        return new MainThreadExecutorImp();
    }
}
