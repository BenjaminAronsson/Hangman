package com.example.dev.hangman;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class SettingsActivity extends ActivityToolbar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //creates fragment
        AboutFragment exampleFragment= new AboutFragment();

        //byta, växla mellan fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        //förändra fragmentet
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameUp, exampleFragment);

        //skickar komandot
        fragmentTransaction.commit();

        /*******************************************************/

        //creates fragment
        MenuFragment menuFragment = new MenuFragment();

        //byta, växla mellan fragments
        FragmentManager fragmentManager2 = getSupportFragmentManager();

        //förändra fragmentet
        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();

        fragmentTransaction2.add(R.id.frameDown, menuFragment);
        fragmentTransaction2.commit();
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
