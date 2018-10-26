package com.example.dev.hangman;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EndActivity extends AppCompatActivity {

    private TextView guessesMadeView;
    private TextView wordView;
    private TextView endMessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        guessesMadeView = findViewById(R.id.textView);
        wordView = findViewById(R.id.textView3);
        endMessageView = findViewById(R.id.textView4);

        ImageView im = findViewById(R.id.imageView);

        Picasso.get().load("https://benjaminaronsson.github.io/Hangman/standard/hang0.gif").into(im);

        loadPreferences();

    }

    private void loadPreferences() {



        //save data
        SharedPreferences prefs = getSharedPreferences("default", MODE_PRIVATE);
        String word = prefs.getString("chosen word", "Hello");//"No name defined" is the default value.
        int guessesLeft = prefs.getInt("guesses left", 10); //0 is the default value.
        boolean isWin = prefs.getBoolean("is win",  false);

        String i = Integer.toString(guessesLeft);
        String j;
        if (isWin) {
            j = getString(R.string.Congratulation);
        }
        else {
            //j = "Better luck next time";
             j = getString(R.string.goodLuck);
        }

        guessesMadeView.setText("There was: " +i +" guesses left");
        wordView.setText("The word was: " +word);
        endMessageView.setText(j);

    }

    public void toMenuButtonPressed(View view) {

    }
}
