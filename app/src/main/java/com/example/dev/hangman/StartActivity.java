package com.example.dev.hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Objects;

@SuppressWarnings("CanBeFinal")
public class StartActivity extends AppCompatActivity {

    public static final Fragment gameFragment = new GameFragment();
    public static final Fragment gameOverFragment = new GameOverFragment();
    public static final Fragment aboutFragment = new AboutFragment();
    public static final Fragment menuFragment = new MenuFragment();

    //byta, växla mellan fragments
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private boolean isBackButton = false;
    private boolean isPlayButton = true;
    private boolean isAboutButton = true;
    private boolean isNewWord = false;

    //toolbar visibility

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        //sets the theme
        //setTheme(getFlag() ? R.style.AppTheme : R.style.halloween);
        setTheme(R.style.halloween);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //sets toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //TODO fragment wont reappear after theme change
        //sets back button on toolbar
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(isBackButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(isBackButton);

        //byta, växla mellan fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        //förändra fragmentet
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainFrame, menuFragment);

        //skickar komandot
        fragmentTransaction.commit();

    }

    public void themeButtonPressed() {

        saveFlag(!getFlag());
        //TODO make app theme
        /*Intent intent = new Intent(StartActivity.this, StartActivity.class);
        startActivity(intent);
        finish();*/
    }

    private void saveFlag(boolean flag) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("theme", flag);
        editor.commit();
    }

    public boolean getFlag() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getBoolean("theme", false);
    }


    //create toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        //tolkar xml filen
        getMenuInflater().inflate(R.menu.menu_start, menu);

        //displays play item
        MenuItem playItem = menu.findItem(R.id.action_play);
        playItem.setVisible(isPlayButton);

        //displays about item
        MenuItem aboutItem = menu.findItem(R.id.action_about);
        aboutItem.setVisible(isAboutButton);


        return true;
    }

    //on toolbar click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_play:
                //TODO add action
                playButtonClicked();
                return true;
            case R.id.action_about:
                InfoButtonClicked();
                return true;
            case R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void newGame() {
        //Hangman.newGame() todo
    }


    //buttons
    private void playButtonClicked() {


         fragmentManager = getSupportFragmentManager();
        //förändra fragmentet
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, gameFragment);
        fragmentTransaction.addToBackStack(null);
        //skickar komandot

        fragmentTransaction.commit();

    }

    private void InfoButtonClicked() {

        fragmentManager = getSupportFragmentManager();
        //förändra fragmentet
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, aboutFragment);
        fragmentTransaction.addToBackStack(null);
        //skickar komandot
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0 )
            getSupportFragmentManager().popBackStack();
        else
            finish();
    }
}
