package com.alexzh.temperatureconverter.interactor;

import com.alexzh.temperatureconverter.calculation.ConverterTemperatureFactory;
import com.alexzh.temperatureconverter.executor.InteractorExecutor;
import com.alexzh.temperatureconverter.executor.MainThreadExecutor;
import com.alexzh.temperatureconverter.model.InputData;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedError;
import com.alexzh.temperatureconverter.model.event.TemperatureConvertedSuccessful;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public class ConvertTemperatureImpl extends UseCase implements ConvertTemperature {
    private ConverterTemperatureFactory mConverterTemperatureFactory;
    private InputData mInputData;
    private EventBus mEventBus;

    @Inject
    public ConvertTemperatureImpl(EventBus eventBus,
                                  ConverterTemperatureFactory converterTemperatureFactory,
                                  InteractorExecutor interactorExecutor,
                                  MainThreadExecutor mainThreadExecutor) {
        super(interactorExecutor, mainThreadExecutor);
        this.mConverterTemperatureFactory = converterTemperatureFactory;
        this.mEventBus = eventBus;
    }

    @Override
    public void run() {
        mConverterTemperatureFactory.getTemperatureConverter().convertData(mInputData, new Callback() {
            @Override
            public void onResult(final TemperatureConvertedSuccessful convertedSuccessful) {
                getMainThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        mEventBus.post(convertedSuccessful);
                    }
                });
            }

            @Override
            public void onError(final TemperatureConvertedError convertedError) {
                getMainThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        mEventBus.post(convertedError);
                    }
                });
            }
        });
    }

    @Override
    public void execute(InputData data) {
        this.mInputData = data;

        getInteractorExecutor().run(this);
    }
}
