package com.alexzh.temperatureconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.alexzh.temperatureconverter.di.TemperatureConverterApp;
import com.alexzh.temperatureconverter.model.Temperature;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TemperatureConverterActivity extends AppCompatActivity implements TemperatureConverterView {
    private final static int CELSIUS_POSITION = 0;
    private final static int FAHRENHEIT_POSITION = 1;
    private final static int KELVIN_POSITION = 2;


    @BindView(R.id.inputView) AppCompatEditText mInputView;
    @BindView(R.id.inputTemperatureSpinner) AppCompatSpinner mInputUnitSpinner;
    @BindView(R.id.outputView) TextView mIOutputView;
    @BindView(R.id.outputTemperatureSpinner) Spinner mOutputUnitSpinner;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Inject TemperatureConverterPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        ButterKnife.bind(this);
        ((TemperatureConverterApp)getApplication()).getComponent().inject(this);

        ArrayAdapter<String> temperatureUnitAdapter = new ArrayAdapter<>(
                this, R.layout.item_unit_spinner, getResources().getStringArray(R.array.temperature_units));
        temperatureUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mInputUnitSpinner.setAdapter(temperatureUnitAdapter);
        mOutputUnitSpinner.setAdapter(temperatureUnitAdapter);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.conrever_activity_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }

    @OnClick(R.id.convertButton)
    public void onConvertButtonClicked() {
        mPresenter.convertTemperature();
    }

    @Override
    public String getInputValue() {
        return mInputView.getText().toString();
    }

    @Override
    public Temperature getFromTemperatureUnit() {
        return getTemperatureUnitFromSpinner(mInputUnitSpinner);
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
    public Temperature getToTemperatureUnit() {
        return getTemperatureUnitFromSpinner(mOutputUnitSpinner);
    }

    @Override
    public void setOutputValue(double value) {
        mIOutputView.setText(getString(R.string.output_text_format, value));
    }

    @Override
    protected void onStop() {
        mPresenter.detachView();
        super.onStop();
    }
}
