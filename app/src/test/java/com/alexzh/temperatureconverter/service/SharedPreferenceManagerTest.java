package com.alexzh.temperatureconverter.service;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SharedPreferenceManagerTest {

    @Mock
    private SharedPreferences mPrefs;

    @Mock
    private SharedPreferences.Editor mEditor;

    private SharedPreferenceManager mManager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mManager = new SharedPreferenceManager(mPrefs);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(mManager);
    }

    @Test
    public void shouldVerifyConvertOnlineAttribute() {
        when(mPrefs.edit()).thenReturn(mEditor);
        when(mEditor.putBoolean(anyString(), anyBoolean())).thenReturn(mEditor);

        mManager.setConvertOnline(true);
        verify(mEditor).putBoolean(anyString(), eq(true));
        verify(mEditor).apply();

        mManager.isConvertOnline();
        verify(mPrefs).getBoolean(anyString(), anyBoolean());
    }
}
