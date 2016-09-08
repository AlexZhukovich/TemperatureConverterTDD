package com.alexzh.temperatureconverter.service;

import android.content.SharedPreferences;

public class SharedPreferenceManager {
    private final static String KEY_CONVERT_ONLINE = "convert_online";
    private SharedPreferences mSharedPreference;

    public SharedPreferenceManager(SharedPreferences preferences) {
        this.mSharedPreference = preferences;
    }

    public boolean isConvertOnline() {
        return mSharedPreference.getBoolean(KEY_CONVERT_ONLINE, false);
    }

    public void setConvertOnline(boolean value) {
        mSharedPreference.edit().putBoolean(KEY_CONVERT_ONLINE, value).apply();
    }
}