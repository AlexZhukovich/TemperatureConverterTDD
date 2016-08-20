package com.alexzh.temperatureconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemperatureConverterActivity extends AppCompatActivity {

    @BindView(R.id.inputView) AppCompatEditText mInputView;
    @BindView(R.id.inputTemperatureSpinner) AppCompatSpinner mInputUnitSpinner;
    @BindView(R.id.outputView) TextView mIOutputView;
    @BindView(R.id.outputTemperatureSpinner) Spinner mOutputUnitSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        ButterKnife.bind(this);

        ArrayAdapter<String> temperatureUnitAdapter = new ArrayAdapter<>(
                this, R.layout.item_unit_spinner, getResources().getStringArray(R.array.temperature_units));
        temperatureUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mInputUnitSpinner.setAdapter(temperatureUnitAdapter);
        mOutputUnitSpinner.setAdapter(temperatureUnitAdapter);
    }
}
