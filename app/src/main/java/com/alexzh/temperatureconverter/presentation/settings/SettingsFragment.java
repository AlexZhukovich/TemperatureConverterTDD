package com.alexzh.temperatureconverter.presentation.settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.alexzh.temperatureconverter.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.app_preferences);
    }
}
