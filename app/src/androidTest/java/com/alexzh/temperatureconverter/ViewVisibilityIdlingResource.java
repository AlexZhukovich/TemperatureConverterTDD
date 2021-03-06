package com.alexzh.temperatureconverter;

import android.support.test.espresso.IdlingResource;
import android.view.View;

public class ViewVisibilityIdlingResource implements IdlingResource {
    private final View mView;
    private final int mExpectedVisibility;

    private boolean mIdle;
    private ResourceCallback mResourceCallback;

    public ViewVisibilityIdlingResource(final View view, final int expectedVisibility) {
        this.mView = view;
        this.mExpectedVisibility = expectedVisibility;
    }

    @Override
    public final String getName() {
        return ViewVisibilityIdlingResource.class.getSimpleName();
    }

    @Override
    public final boolean isIdleNow() {
        mIdle = mView.getVisibility() == mExpectedVisibility;

        if (mIdle && mResourceCallback != null) {
                mResourceCallback.onTransitionToIdle();
        }
        return mIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        mResourceCallback = resourceCallback;
    }
}