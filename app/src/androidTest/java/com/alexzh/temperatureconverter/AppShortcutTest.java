package com.alexzh.temperatureconverter;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 25)
public class AppShortcutTest {
    private final static long TIMEOUT = 5000;

    private final static String APP_NAME = "TemperatureConverterTDD";
    private final static String APP_PACKAGE = "com.alexzh.temperatureconverter";
    private final static String APPS = "Apps list";
    private final static String FAHRENHEIT_UNIT = "°F";
    private final static String CELSIUS_UNIT = "°C";
    private final static String KELVIN_UNIT = "K";

    private final static String PACKAGE_NEXUS_LAUNCHER = "com.google.android.apps.nexuslauncher";
    private final static String SEARCH_BOX_NEXUS_LAUNCHER = "search_box_input";

    private final static String CONVERT_CELSIUS_TO_FAHRENHEIT_DESC = "Convert Celsius to Fahrenheit";
    private final static String CONVERT_CELSIUS_TO_KELVIN_DESC = "Convert Celsius to Kelvin";
    private final static String CONVERT_FAHRENHEIT_TO_CELSIUS_DESC = "Convert Fahrenheit to Celsius";
    private final static String CONVERT_FAHRENHEIT_TO_KELVIN_DESC = "Convert Fahrenheit to Kelvin";

    private final static String INPUT_SPINNER_ID = "inputTemperatureSpinner";
    private final static String OUTPUT_SPINNER_ID = "outputTemperatureSpinner";

    private UiDevice mDevice;

    @Before
    public void setup() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();

        mDevice.findObject(By.desc(APPS)).click();

        mDevice.wait(Until.hasObject(By.res(PACKAGE_NEXUS_LAUNCHER, SEARCH_BOX_NEXUS_LAUNCHER)), TIMEOUT);
        UiObject2 search = mDevice.findObject(By.res(PACKAGE_NEXUS_LAUNCHER, SEARCH_BOX_NEXUS_LAUNCHER));
        search.click();
        search.setText(APP_NAME);
    }

    @Test
    public void shouldVerifyCelsiusToFahrenheitShortcut() {
        mDevice.findObject(By.desc(APP_NAME)).longClick();

        mDevice.wait(Until.hasObject(By.desc(CONVERT_CELSIUS_TO_FAHRENHEIT_DESC)), TIMEOUT);
        mDevice.findObject(By.desc(CONVERT_CELSIUS_TO_FAHRENHEIT_DESC)).click();

        verifyInputAndOutputUnits(CELSIUS_UNIT, FAHRENHEIT_UNIT);
    }

    @Test
    public void shouldVerifyCelsiusToKelvinShortcut() {
        mDevice.findObject(By.desc(APP_NAME)).longClick();

        mDevice.wait(Until.hasObject(By.desc(CONVERT_CELSIUS_TO_KELVIN_DESC)), TIMEOUT);
        mDevice.findObject(By.desc(CONVERT_CELSIUS_TO_KELVIN_DESC)).click();

        verifyInputAndOutputUnits(CELSIUS_UNIT, KELVIN_UNIT);
    }

    @Test
    public void shouldVerifyFahrenheitToCelsiusShortcut() {
        mDevice.findObject(By.desc(APP_NAME)).longClick();

        mDevice.wait(Until.hasObject(By.desc(CONVERT_FAHRENHEIT_TO_CELSIUS_DESC)), TIMEOUT);
        mDevice.findObject(By.desc(CONVERT_FAHRENHEIT_TO_CELSIUS_DESC)).click();

        verifyInputAndOutputUnits(FAHRENHEIT_UNIT, CELSIUS_UNIT);
    }

    @Test
    public void shouldVerifyFahrenheitToKelvinShortcut() {
        mDevice.findObject(By.desc(APP_NAME)).longClick();

        mDevice.wait(Until.hasObject(By.desc(CONVERT_FAHRENHEIT_TO_KELVIN_DESC)), TIMEOUT);
        mDevice.findObject(By.desc(CONVERT_FAHRENHEIT_TO_KELVIN_DESC)).click();

        verifyInputAndOutputUnits(FAHRENHEIT_UNIT, KELVIN_UNIT);
    }

    @After
    public void tearDown() {
        if (mDevice != null) {
            mDevice.pressBack();
        }
    }

    private void verifyInputAndOutputUnits(final String expectedInput, final String expectedOutput) {
        mDevice.wait(Until.hasObject(By.res(APP_PACKAGE, INPUT_SPINNER_ID)), TIMEOUT);

        UiObject2 input = mDevice.findObject(By.res(APP_PACKAGE, INPUT_SPINNER_ID));
        UiObject2 output = mDevice.findObject(By.res(APP_PACKAGE, OUTPUT_SPINNER_ID));

        assertEquals(expectedInput, input.getChildren().get(0).getText());
        assertEquals(expectedOutput, output.getChildren().get(0).getText());
    }
}
