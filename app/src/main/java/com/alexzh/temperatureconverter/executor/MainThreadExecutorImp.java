package com.alexzh.temperatureconverter.executor;

import android.os.Handler;
import android.os.Looper;

public class MainThreadExecutorImp implements MainThreadExecutor {
    private Handler mHandler;

    public MainThreadExecutorImp() {
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
        mHandler.post(runnable);
    }
}
