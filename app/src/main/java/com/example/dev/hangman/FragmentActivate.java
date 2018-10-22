package com.example.dev.hangman;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class FragmentActivate extends FragmentActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new GameFragment()).commit();}
    }
}

