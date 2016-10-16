package com.alexzh.temperatureconverter.interactor;

import com.alexzh.temperatureconverter.executor.Interactor;
import com.alexzh.temperatureconverter.executor.InteractorExecutor;
import com.alexzh.temperatureconverter.executor.MainThreadExecutor;

public abstract class UseCase implements Interactor {
    private InteractorExecutor mInteractorExecutor;
    private MainThreadExecutor mMainThreadExecutor;

    public UseCase(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor) {
        this.mInteractorExecutor = interactorExecutor;
        this.mMainThreadExecutor = mainThreadExecutor;
    }

    public InteractorExecutor getInteractorExecutor() {
        return mInteractorExecutor;
    }

    public MainThreadExecutor getMainThreadExecutor() {
        return mMainThreadExecutor;
    }
}
