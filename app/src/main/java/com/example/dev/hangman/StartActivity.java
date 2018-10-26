package com.example.dev.hangman;

public class StartActivity extends ActivityToolbar {

    //toolbar visibility

    @Override
    protected boolean isBackButton() {
        return false;
    }

    @Override
    protected boolean isPlayButton() {
        return true;
    }

    @Override
    protected boolean isAboutButton() {
        return true;
    }
}
