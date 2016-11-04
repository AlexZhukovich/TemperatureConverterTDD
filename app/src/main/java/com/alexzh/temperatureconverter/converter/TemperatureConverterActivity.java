package com.alexzh.temperatureconverter.converter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.alexzh.temperatureconverter.R;
import com.alexzh.temperatureconverter.data.Temperature;
import com.alexzh.temperatureconverter.di.TemperatureConverterApp;
import com.alexzh.temperatureconverter.settings.SettingsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TemperatureConverterActivity extends AppCompatActivity implements TemperatureConverterView {
    private final static String ACTION_CELSIUS_TO_FAHRENHEIT
            = "com.alexzh.temperatureconverter.ACTION_CELSIUS_TO_FAHRENHEIT";

    private final static String ACTION_CELSIUS_TO_KELVIN
            = "com.alexzh.temperatureconverter.ACTION_CELSIUS_TO_KELVIN";

    private final static String ACTION_FAHRENHEIT_TO_CELSIUS
            = "com.alexzh.temperatureconverter.ACTION_FAHRENHEIT_TO_CELSIUS";

    private final static String ACTION_FAHRENHEIT_TO_KELVIN
            = "com.alexzh.temperatureconverter.ACTION_FAHRENHEIT_TO_KELVIN";

    private final static String LAST_RESULT = "last_result";
    private final static int CELSIUS_POSITION = 0;
    private final static int FAHRENHEIT_POSITION = 1;
    private final static int KELVIN_POSITION = 2;


    @BindView(R.id.inputView) AppCompatEditText mInputView;
    @BindView(R.id.inputTemperatureSpinner) AppCompatSpinner mInputUnitSpinner;
    @BindView(R.id.outputView) TextView mOutputView;
    @BindView(R.id.outputTemperatureSpinner) Spinner mOutputUnitSpinner;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.resultLayout) RelativeLayout mResultLayout;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Inject
    TemperatureConverterPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        int defaultInputUnit;
        int defaultOutputUnit;

        if (ACTION_CELSIUS_TO_FAHRENHEIT.equals(getIntent().getAction())) {
            defaultInputUnit = CELSIUS_POSITION;
            defaultOutputUnit = FAHRENHEIT_POSITION;
        } else if (ACTION_CELSIUS_TO_KELVIN.equals(getIntent().getAction())) {
            defaultInputUnit = CELSIUS_POSITION;
            defaultOutputUnit = KELVIN_POSITION;
        } else if (ACTION_FAHRENHEIT_TO_CELSIUS.equals(getIntent().getAction())) {
            defaultInputUnit = FAHRENHEIT_POSITION;
            defaultOutputUnit = CELSIUS_POSITION;
        } else if (ACTION_FAHRENHEIT_TO_KELVIN.equals(getIntent().getAction())) {
            defaultInputUnit = FAHRENHEIT_POSITION;
            defaultOutputUnit = KELVIN_POSITION;
        } else {
            defaultInputUnit = CELSIUS_POSITION;
            defaultOutputUnit = FAHRENHEIT_POSITION;
        }

        ButterKnife.bind(this);
        ((TemperatureConverterApp) getApplication()).createTemperatureConverterComponent().inject(this);

        ArrayAdapter<String> temperatureUnitAdapter = new ArrayAdapter<>(
                this, R.layout.item_unit_spinner, getResources().getStringArray(R.array.temperature_units));
        temperatureUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mInputUnitSpinner.setAdapter(temperatureUnitAdapter);
        mInputUnitSpinner.setSelection(defaultInputUnit);
        mOutputUnitSpinner.setAdapter(temperatureUnitAdapter);
        mOutputUnitSpinner.setSelection(defaultOutputUnit);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        hideProgress();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.conrever_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                mPresenter.openSettings();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }

    @OnClick(R.id.convertButton)
    public void onConvertButtonClicked() {
        mPresenter.convertTemperature(
                mInputView.getText().toString(),
                getTemperatureUnitFromSpinner(mInputUnitSpinner),
                getTemperatureUnitFromSpinner(mOutputUnitSpinner));
    }

    private Temperature getTemperatureUnitFromSpinner(Spinner spinner) {
        switch (spinner.getSelectedItemPosition()) {
            case FAHRENHEIT_POSITION:
                return Temperature.FAHRENHEIT;
            case KELVIN_POSITION:
                return Temperature.KELVIN;
            case CELSIUS_POSITION:
            default:
                return Temperature.CELSIUS;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LAST_RESULT, mOutputView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mOutputView.setText(savedInstanceState.getString(LAST_RESULT));
    }

    @Override
    public void displayResult(double value) {
        mOutputView.setText(getString(R.string.output_text_format, value));
    }

    @Override
    public void displayProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mInputUnitSpinner.setEnabled(false);
        mInputView.setEnabled(false);
        mOutputUnitSpinner.setEnabled(false);
        mOutputView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
        mInputUnitSpinner.setEnabled(true);
        mInputView.setEnabled(true);
        mOutputUnitSpinner.setEnabled(true);
        mOutputView.setVisibility(View.VISIBLE);
    }

    @Override
    public void launchSettingsActivity() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void displayErrorMessage(String message) {
        Snackbar.make(mResultLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        mPresenter.detachView();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        ((TemperatureConverterApp) getApplication()).releaseTemperatureConverterComponent();
        super.onDestroy();
    }
}
