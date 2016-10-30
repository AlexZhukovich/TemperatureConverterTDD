package com.alexzh.temperatureconverter.converter.usecase;

import com.alexzh.temperatureconverter.base.UseCase;
import com.alexzh.temperatureconverter.data.InputData;
import com.alexzh.temperatureconverter.data.TemperatureConvertedError;
import com.alexzh.temperatureconverter.data.TemperatureConvertedSuccessful;
import com.alexzh.temperatureconverter.data.source.ConverterTemperatureFactory;
import com.alexzh.temperatureconverter.executor.InteractorExecutor;
import com.alexzh.temperatureconverter.executor.MainThreadExecutor;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public class ConvertTemperatureUseCaseImpl extends UseCase implements ConvertTemperatureUseCase {
    private ConverterTemperatureFactory mConverterTemperatureFactory;
    private InputData mInputData;
    private EventBus mEventBus;

    @Inject
    public ConvertTemperatureUseCaseImpl(EventBus eventBus,
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
