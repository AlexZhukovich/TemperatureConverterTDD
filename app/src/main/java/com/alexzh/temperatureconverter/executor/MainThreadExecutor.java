package com.alexzh.temperatureconverter.executor;

public interface MainThreadExecutor {

    void execute(Runnable runnable);
}
