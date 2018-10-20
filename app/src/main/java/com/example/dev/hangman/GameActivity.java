package com.example.dev.hangman;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private Hangman hangman;
    private EditText inputField;
    private TextView guesses;
    private TextView guessesMade;
    private ImageView hangmanView;
    private SharedPreferences sharedPreferences;
    private List<Drawable> images= new ArrayList<>(); //spara bilder innan
    private int bild = 9;
    //TODO save pic, guesses, hidden word, chosen word

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activitty);
        inputField = findViewById(R.id.guessText);
        guesses = findViewById(R.id.hiddenWord);
        guessesMade = findViewById(R.id.guessedLetters);
        hangmanView = findViewById(R.id.hangmanView);

        hangmanView.setTag(bild);

        
        //TODO add word list from web
        hangman = new Hangman();

        //Preload images TODO add to web
        images.add(getResources().getDrawable(R.drawable.hang0, getTheme()));
        images.add(getResources().getDrawable(R.drawable.hang1, getTheme()));
        images.add(getResources().getDrawable(R.drawable.hang2, getTheme()));
        images.add(getResources().getDrawable(R.drawable.hang3, getTheme()));
        images.add(getResources().getDrawable(R.drawable.hang4, getTheme()));
        images.add(getResources().getDrawable(R.drawable.hang5, getTheme()));
        images.add(getResources().getDrawable(R.drawable.hang6, getTheme()));
        images.add(getResources().getDrawable(R.drawable.hang7, getTheme()));
        images.add(getResources().getDrawable(R.drawable.hang8, getTheme()));
        images.add(getResources().getDrawable(R.drawable.hang9, getTheme()));

        //sets image
        Drawable drawable = images.get(hangman.getGuessesLeft());
        hangmanView.setImageDrawable(drawable);

        layoutUpdate();

    }

    public void guessButtonPressed(View view) {

        if(hangman.isGameContinuing()) {

            //gets input from textfield
            String input = getInput(inputField);

            //tests if input is valid
            inputCorrect(input); //TODO check

            //guesses
            hangman.guess(input);

            //draws layout
            layoutUpdate();

            //test if player won or lost
            if (hangman.hasWon()) {
                gameWon();
            } else if (hangman.hasLost()) {
                gameLost();
                guesses.setText(hangman.getChoosenWord());
            }
        }
    }

    private void gameWon() {
        //TODO
    }

    private void gameLost() {
        //TODO
    }

    private void layoutUpdate() {

        //update hangman pic
        if (hangman.getGuessesLeft() < 10 && bild > 0) {
            hangmanView.setImageDrawable(images.get(hangman.getGuessesLeft()));
            hangmanView.setTag(hangman.getGuessesLeft());
        }

        //updates text field
        guesses.setText(hangman.getHiddenWord());
        guessesMade.setText(hangman.getBadLettersUsed());
        inputField.setText("");
    }

    private String getInput(EditText inputField) {
        //takes guess from input

     String input = inputField.getText().toString();
    return input;

    }

    private boolean inputCorrect(String input) {
        if (input.length() > 1) {
            toastWrongInput();
            return false;
        }
        if (hangman.hasUsedLetter(input)) {
            toastDuplicateGuess();
            return false;
        }
        return true;
    }

    private void toastWrongInput() {
        // Toast myToast = Toast.makeText(this, message, duration);
        Toast myToast = Toast.makeText(this, "You can only guess on one letter at a time!", Toast.LENGTH_SHORT);
        myToast.show();
    }

    private void toastDuplicateGuess() {
        // Toast myToast = Toast.makeText(this, message, duration);
        Toast myToast = Toast.makeText(this, "You can only guess on a letter once!", Toast.LENGTH_SHORT);
        myToast.show();
    }


}
