package com.example.dev.hangman;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class SettingsActivity extends ActivityToolbar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        //creates fragment
        Fragment exampleFragment= new AboutFragment();

        //byta, växla mellan fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        //förändra fragmentet
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainFrame, exampleFragment);

        //skickar komandot
        fragmentTransaction.commit();
    }


}
