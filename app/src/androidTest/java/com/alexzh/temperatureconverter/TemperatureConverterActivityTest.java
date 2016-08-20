package com.alexzh.temperatureconverter;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TemperatureConverterActivityTest {

    @Rule
    public ActivityTestRule<TemperatureConverterActivity> mRule =
            new ActivityTestRule<>(TemperatureConverterActivity.class);

    @Test
    public void shouldVerifyGeneralViews() {
        onView(withId(R.id.inputView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.inputTemperatureSpinner))
                .check(matches(isDisplayed()));

        onView(withId(R.id.convertButton))
                .check(matches(isDisplayed()));

        onView(withId(R.id.outputView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.outputTemperatureSpinner))
                .check(matches(isDisplayed()));
    }
}
