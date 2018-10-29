package com.example.dev.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    private Fragment gameFragment;
    private Fragment menuFragment;
    private Fragment aboutFragment;
    //byta, växla mellan fragments
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    //toolbar visibility

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //sets back button on toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(isBackButton());
        getSupportActionBar().setDisplayHomeAsUpEnabled(isBackButton());

        //creates menu fragment
        menuFragment = new MenuFragment();
        //creates game fragment
        gameFragment = new GameFragment();
        //creates menu fragment
        aboutFragment = new AboutFragment();

        //byta, växla mellan fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        //förändra fragmentet
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainFrame, menuFragment);

        //skickar komandot
        fragmentTransaction.commit();

    }

    protected boolean isBackButton() {
        return false;
    }


    protected boolean isPlayButton() {
        return true;
    }


    protected boolean isAboutButton() {
        return true;
    }

    //create toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //get menu
        getMenuInflater().inflate(R.menu.menu_start, menu);

        //displays play item
        MenuItem playItem = menu.findItem(R.id.action_play);
        playItem.setVisible(isPlayButton());

        //displays about item
        MenuItem aboutItem = menu.findItem(R.id.action_about);
        aboutItem.setVisible(isAboutButton());

        return true;
    }

    //on toolbar click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_play:
                //TODO add action
                return true;
            case R.id.action_about:
                gameButtonClicked();
                return true;
            case R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void gameButtonClicked() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }




    //buttons
    public void playButtonClicked(View view) {


         fragmentManager = getSupportFragmentManager();
        //förändra fragmentet
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, gameFragment);
        //skickar komandot
        fragmentTransaction.commit();

    }

    public void InfoButtonClicked(View view) {

        fragmentManager = getSupportFragmentManager();
        //förändra fragmentet
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, aboutFragment);
        //skickar komandot
        fragmentTransaction.commit();
    }

}
