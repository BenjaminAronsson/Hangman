package com.example.dev.hangman;

import android.annotation.SuppressLint;
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



    //toolbar visibility

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //set style
    setTheme(R.style.halloween);

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);

    //sets toolbar
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //sets back button on toolbar
    Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(isBackButton);
    getSupportActionBar().setDisplayHomeAsUpEnabled(isBackButton);
    toolbar.setNavigationOnClickListener(v -> onBackPressed());

    //byta, växla mellan fragments
    fragmentManager = getSupportFragmentManager();

    //förändra fragmentet
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.mainFrame, menuFragment);
    //skickar komandot
    fragmentTransaction.commit();




    }

    public void themeButtonPressed() {
        //thange theme
        saveFlag(!getFlag());
    }

    @SuppressLint("ApplySharedPref")
    private void saveFlag(boolean flag) {
        //save theme
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("theme", flag);
        editor.commit();
    }

    public boolean getFlag() {
        //load theme
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

        Fragment game = getSupportFragmentManager().findFragmentByTag("gameID");

        //identifying item clicked
        switch (item.getItemId()) {
            case R.id.action_play:
                //om game fragmentet visas
                if(!(game != null && game.isVisible() )) {
                    playButtonClicked();
                }
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

    //buttons
    private void playButtonClicked() {

        //load fragment
         fragmentManager = getSupportFragmentManager();
        //förändra fragmentet
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, gameFragment,"gameID");
        fragmentTransaction.addToBackStack(null);
        //skickar komandot

        fragmentTransaction.commit();

    }

    private void InfoButtonClicked() {
        //load fragment
        fragmentManager = getSupportFragmentManager();
        //förändra fragmentet
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, aboutFragment, "aboutID");
        fragmentTransaction.addToBackStack(null);
        //skickar komandot
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        //adds fragment stack to backbutton
        if(getSupportFragmentManager() != null && getSupportFragmentManager().getBackStackEntryCount() > 0 ) {
            getSupportFragmentManager().popBackStack();
        }
        else {
            finish();
        }
    }

}
