package com.alexzh.temperatureconverter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.WindowManager;

import com.alexzh.temperatureconverter.presentation.converter.TemperatureConverterActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
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

    private ViewVisibilityIdlingResource mVisibilityIdlingResource;

    @Rule
    public ActivityTestRule<TemperatureConverterActivity> mRule =
            new ActivityTestRule<>(TemperatureConverterActivity.class);

    @Before
    public void setUp() {
        mVisibilityIdlingResource =
                new ViewVisibilityIdlingResource(mRule.getActivity().findViewById(R.id.outputView), View.VISIBLE);

        final TemperatureConverterActivity activity = mRule.getActivity();
        Runnable wakeUpDevice = new Runnable() {
            public void run() {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        };
        activity.runOnUiThread(wakeUpDevice);

        registerIdlingResources(mVisibilityIdlingResource);
    }

    @Test
    public void shouldVerifyResultAfterRotate() {
        onView(withId(R.id.inputView))
                .perform(typeText(String.valueOf(CELSIUS_VALUE)), ViewActions.closeSoftKeyboard());

        setTemperatureUnit(R.id.inputTemperatureSpinner, CELSIUS_STR);
        setTemperatureUnit(R.id.outputTemperatureSpinner, KELVIN_STR);

        onView(withId(R.id.convertButton))
                .perform(click());

        onView(withId(R.id.outputView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.outputView))
                .check(matches(withText(getOutputString(KELVIN_VALUE))));

        rotateScreen();

        onView(withId(R.id.outputView))
                .check(matches(withText(getOutputString(KELVIN_VALUE))));
    }

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

    @Test
    public void shouldVerifyConvertingFromCelsiusToFahrenheit() {
        onView(withId(R.id.inputView))
                .perform(typeText(String.valueOf(CELSIUS_VALUE)), ViewActions.closeSoftKeyboard());

        setTemperatureUnit(R.id.inputTemperatureSpinner, CELSIUS_STR);

        setTemperatureUnit(R.id.outputTemperatureSpinner, FAHRENHEIT_STR);

        onView(withId(R.id.convertButton))
                .perform(click());

        onView(withId(R.id.outputView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.outputView))
                .check(matches(withText(getOutputString(FAHRENHEIT_VALUE))));
    }

    @Test
    public void shouldVerifyDisplayingError() {
        onView(withId(R.id.inputView))
                .perform(typeText("."), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.convertButton))
                .perform(click());

        onView(withText("ERROR"))
                .check(matches(isDisplayed()));
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
                .check(matches(withText(getOutputString(KELVIN_VALUE))));
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
                .check(matches(isDisplayed()));

        onView(withId(R.id.outputView))
                .check(matches(withText(getOutputString(CELSIUS_VALUE))));
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
                .check(matches(isDisplayed()));

        onView(withId(R.id.outputView))
                .check(matches(withText(getOutputString(KELVIN_VALUE))));
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
                .check(matches(isDisplayed()));

        onView(withId(R.id.outputView))
                .check(matches(withText(getOutputString(FAHRENHEIT_VALUE))));
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
                .check(matches(isDisplayed()));

        onView(withId(R.id.outputView))
                .check(matches(withText(getOutputString(CELSIUS_VALUE))));
    }

    @Test
    public void shouldVerifyOpenSettingsIntent() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        Espresso.pressBack();

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText(R.string.action_settings))
                .check(matches(isDisplayed()));

        onView(withText(R.string.action_settings))
                .perform(click());

        onView(withText(R.string.converter_options_category))
                .check(matches(isDisplayed()));
    }

    @After
    public void tearDown() {
        unregisterIdlingResources(mVisibilityIdlingResource);
    }

    private void setTemperatureUnit(int spinnerId, String temperatureUnit) {
        onView(withId(spinnerId))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is(temperatureUnit)))
                .perform(click());
        onView(withId(spinnerId))
                .check(matches(withSpinnerText(containsString(temperatureUnit))));
    }

    private void rotateScreen() {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation
                = context.getResources().getConfiguration().orientation;

        Activity activity = mRule.getActivity();
        activity.setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE :
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private String getOutputString(double value) {
        return mRule.getActivity().getString(R.string.output_text_format, value);
    }
}
