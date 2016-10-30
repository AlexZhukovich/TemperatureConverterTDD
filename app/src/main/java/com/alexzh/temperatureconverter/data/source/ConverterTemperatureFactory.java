package com.alexzh.temperatureconverter.data.source;

import com.alexzh.temperatureconverter.data.source.offline.OfflineConvertTemperature;
import com.alexzh.temperatureconverter.data.source.online.OnlineConverterTemperature;
import com.alexzh.temperatureconverter.data.source.online.TemperatureConverterApiService;
import com.alexzh.temperatureconverter.utils.SharedPreferenceManager;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public class ConverterTemperatureFactory {
    private SharedPreferenceManager mPreferenceManager;

    @Inject
    TemperatureConverterApiService mApiService;

    @Inject
    EventBus mEventBus;

    @Inject
    public ConverterTemperatureFactory(SharedPreferenceManager preferenceManager) {
        this.mPreferenceManager = preferenceManager;
    }

    public ConvertTemperatureRepository getTemperatureConverter() {
        if (mPreferenceManager.isConvertOnline()) {
            return new OnlineConverterTemperature(mApiService);
        } else {
            return new OfflineConvertTemperature();
        }
    }

}
