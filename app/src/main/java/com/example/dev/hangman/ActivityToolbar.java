package com.example.dev.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public abstract class ActivityToolbar extends AppCompatActivity {
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //sets back button on toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(isBackButton());
        getSupportActionBar().setDisplayHomeAsUpEnabled(isBackButton());


    }




    //Visibility
    protected abstract boolean isBackButton();

    protected abstract boolean isAboutButton();

    protected abstract boolean isPlayButton();


    //create menu
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

    //on menu click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_play:
                playButtonClicked();
                return true;
            case R.id.action_about:
                aboutButtonClicked();
                return true;
            case R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //bar buttons

    private void aboutButtonClicked() {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    private void playButtonClicked() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }


    //buttons
    public void startButtonClicked(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void InfoButtonClicked(View view) {
    /*FragmentManager manager = getFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.add(R.id.container, GameFragment.class , "game" );
    transaction.addToBackStack(null);
    transaction.commit();

    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.container, fragmentObject);
    ft.commit();*/

        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

        
    }


