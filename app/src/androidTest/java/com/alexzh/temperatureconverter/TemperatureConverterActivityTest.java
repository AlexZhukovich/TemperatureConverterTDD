package com.alexzh.temperatureconverter;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.alexzh.temperatureconverter.presentation.converter.TemperatureConverterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class TemperatureConverterActivityTest {
    private final static String CELSIUS_STR = "°C";
    private final static String FAHRENHEIT_STR = "°F";
    private final static String KELVIN_STR = "K";
    private final static double CELSIUS_VALUE = 12.5;
    private final static double FAHRENHEIT_VALUE = 54.5;
    private final static double KELVIN_VALUE = 285.65;

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

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.action_settings))
                .check(matches(isDisplayed()));
    }

    private void setTemperatureUnit(int spinnerId, String temperatureUnit) {
        onView(withId(spinnerId))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is(temperatureUnit)))
                .perform(click());
        onView(withId(spinnerId))
                .check(matches(withSpinnerText(containsString(temperatureUnit))));
    }

    @Test
    public void shouldVerifyConvertingFromCelsiusToFahrenheit() {
        onView(withId(R.id.inputView))
                .perform(typeText(String.valueOf(CELSIUS_VALUE)), ViewActions.closeSoftKeyboard());

        setTemperatureUnit(R.id.inputTemperatureSpinner, CELSIUS_STR);

        setTemperatureUnit(R.id.outputTemperatureSpinner, FAHRENHEIT_STR);

        onView(withId(R.id.convertButton))
                .perform(click());

        onView(withId(R.id.outputView))
                .check(matches(withText(mRule.getActivity().getString(R.string.output_text_format, FAHRENHEIT_VALUE))));
    }

    @Test
    public void shouldVerifyConvertingFromCelsiusToKelvin() {
        onView(withId(R.id.inputView))
                .perform(typeText(String.valueOf(CELSIUS_VALUE)), ViewActions.closeSoftKeyboard());

        setTemperatureUnit(R.id.inputTemperatureSpinner, CELSIUS_STR);

        setTemperatureUnit(R.id.outputTemperatureSpinner, KELVIN_STR);

        onView(withId(R.id.convertButton))
                .perform(click());

        onView(withId(R.id.outputView))
                .check(matches(withText(mRule.getActivity().getString(R.string.output_text_format, KELVIN_VALUE))));
    }

    @Test
    public void shouldVerifyConvertingFromFahrenheitToCelsius() {
        onView(withId(R.id.inputView))
                .perform(typeText(String.valueOf(FAHRENHEIT_VALUE)), ViewActions.closeSoftKeyboard());

        setTemperatureUnit(R.id.inputTemperatureSpinner, FAHRENHEIT_STR);

        setTemperatureUnit(R.id.outputTemperatureSpinner, CELSIUS_STR);

        onView(withId(R.id.convertButton))
                .perform(click());

        onView(withId(R.id.outputView))
                .check(matches(withText(mRule.getActivity().getString(R.string.output_text_format, CELSIUS_VALUE))));
    }

    @Test
    public void shouldVerifyConvertingFromFahrenheitToKelvin() {
        onView(withId(R.id.inputView))
                .perform(typeText(String.valueOf(FAHRENHEIT_VALUE)), ViewActions.closeSoftKeyboard());

        setTemperatureUnit(R.id.inputTemperatureSpinner, FAHRENHEIT_STR);

        setTemperatureUnit(R.id.outputTemperatureSpinner, KELVIN_STR);

        onView(withId(R.id.convertButton))
                .perform(click());

        onView(withId(R.id.outputView))
                .check(matches(withText(mRule.getActivity().getString(R.string.output_text_format, KELVIN_VALUE))));
    }

    @Test
    public void shouldVerifyConvertingFromKelvinToFahrenheit() {
        onView(withId(R.id.inputView))
                .perform(typeText(String.valueOf(KELVIN_VALUE)), ViewActions.closeSoftKeyboard());

        setTemperatureUnit(R.id.inputTemperatureSpinner, KELVIN_STR);

        setTemperatureUnit(R.id.outputTemperatureSpinner, FAHRENHEIT_STR);

        onView(withId(R.id.convertButton))
                .perform(click());

        onView(withId(R.id.outputView))
                .check(matches(withText(mRule.getActivity().getString(R.string.output_text_format, FAHRENHEIT_VALUE))));
    }

    @Test
    public void shouldVerifyConvertingFromKelvinToCelsius() {
        onView(withId(R.id.inputView))
                .perform(typeText(String.valueOf(KELVIN_VALUE)), ViewActions.closeSoftKeyboard());

        setTemperatureUnit(R.id.inputTemperatureSpinner, KELVIN_STR);

        setTemperatureUnit(R.id.outputTemperatureSpinner, CELSIUS_STR);

        onView(withId(R.id.convertButton))
                .perform(click());

        onView(withId(R.id.outputView))
                .check(matches(withText(mRule.getActivity().getString(R.string.output_text_format, CELSIUS_VALUE))));
    }
}
