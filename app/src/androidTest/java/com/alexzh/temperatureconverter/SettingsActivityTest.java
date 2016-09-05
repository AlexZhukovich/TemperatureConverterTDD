package com.alexzh.temperatureconverter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.preference.PreferenceManager;

import com.alexzh.temperatureconverter.presentation.settings.SettingsActivity;

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

    @Rule
    public ActivityTestRule<SettingsActivity> mRule =
            new ActivityTestRule<>(SettingsActivity.class, false, false);

    @Before
    public void setup() {
        Context context = InstrumentationRegistry.getTargetContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();

        mRule.launchActivity(new Intent(context, SettingsActivity.class));
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
}
