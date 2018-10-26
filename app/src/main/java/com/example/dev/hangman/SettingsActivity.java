package com.example.dev.hangman;

import android.os.Bundle;

public class SettingsActivity extends ActivityToolbar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected boolean isBackButton() {
        return true;
    }

    @Override
    protected boolean isAboutButton() {
        return false;
    }

    @Override
    protected boolean isPlayButton() {
        return false;
    }
}
