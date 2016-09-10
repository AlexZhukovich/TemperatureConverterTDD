package com.alexzh.temperatureconverter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.preference.PreferenceManager;
import android.view.WindowManager;

import com.alexzh.temperatureconverter.presentation.converter.TemperatureConverterActivity;
import com.alexzh.temperatureconverter.presentation.settings.SettingsActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SettingsActivityTest {

    private Context mContext;

    @Rule
    public ActivityTestRule<SettingsActivity> mRule =
            new ActivityTestRule<>(SettingsActivity.class, false, false);

    @Before
    public void setup() {
        mContext = InstrumentationRegistry.getTargetContext();

        cleanSharedPreference();
        mRule.launchActivity(new Intent(mContext, SettingsActivity.class));

        final SettingsActivity activity = mRule.getActivity();
        Runnable wakeUpDevice = new Runnable() {
            public void run() {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        };
        activity.runOnUiThread(wakeUpDevice);
    }

    @Test
    public void shouldActivateOnlineConverting() {
        onView(withText(R.string.convert_temperature_online_title))
                .check(matches(isDisplayed()));

        onView(withText(R.string.convert_locally_description))
                .check(matches(isDisplayed()));

        onView(withText(R.string.convert_temperature_online_title))
                .perform(click());

        onView(withText(R.string.convert_online_description))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldDeactivateOnlineConverting() {
        onView(withText(R.string.convert_temperature_online_title))
                .check(matches(isDisplayed()));

        onView(withText(R.string.convert_locally_description))
                .check(matches(isDisplayed()));

        onView(withText(R.string.convert_temperature_online_title))
                .perform(click());

        onView(withText(R.string.convert_online_description))
                .check(matches(isDisplayed()));

        onView(withText(R.string.convert_temperature_online_title))
                .perform(click());

        onView(withText(R.string.convert_locally_description))
                .check(matches(isDisplayed()));
    }

    @After
    public void tearDown() {
        cleanSharedPreference();
    }

    private void cleanSharedPreference() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
