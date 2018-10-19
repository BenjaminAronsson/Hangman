package com.example.dev.hangman;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private Hangman hangman;
    private TextView v;
    private ImageView hangmanView;
    private SharedPreferences sharedPreferences;
    private List<Drawable> images= new ArrayList<>(); //spara bilder innan
    private int bild = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activitty);
        v = findViewById(R.id.guessText);
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

    }

    //TODO
    public void guessButtonPressed(View view) {

        if(hangman.isGameContinuing()) {

            //tar emot gisning
            String guess = getInput();
            //gissar
            hangman.guess(guess);

            //ändrar bilden
            if (hangman.getGuessesLeft() < 10 && bild > 0) {
                hangmanView.setImageDrawable(images.get(hangman.getGuessesLeft()));
                hangmanView.setTag(hangman.getGuessesLeft());
            }

            if (hangman.hasWon()) {
                //TODO spelet är slut
            } else if (hangman.hasLost()) {
                //TODO spelet är slut
            }
        }
    }


    //TODO
    private String getInput() {
        //tar gisning från input
        do {
            // TODO String input = getInput();
            String input = "TODO";
            //testar att input är godkänt
            if (inputCorrect(input)) {
                return input;
            }
            //todo loop or exception
        }while (true);
    }

    private boolean inputCorrect(String input) {
        if (input.length() > 1) {
            toastWrongInput(v);
            return false;
        }
        if (hangman.hasUsedLetter(input)) {
            toastDuplicateGuess(v);
            return false;
        }
        return true;
    }

    /**
     * Show a toast
     * @param view -- the view that is clicked
     */
    private void toastWrongInput(View view) {
        // Toast myToast = Toast.makeText(this, message, duration);
        Toast myToast = Toast.makeText(this, "You can only guess on one letter at a time!", Toast.LENGTH_SHORT);
        myToast.show();
    }
    /**
     * Show a toast
     * @param view -- the view that is clicked
     */
    private void toastDuplicateGuess(View view) {
        // Toast myToast = Toast.makeText(this, message, duration);
        Toast myToast = Toast.makeText(this, "You can only guess on a letter once!", Toast.LENGTH_SHORT);
        myToast.show();
    }


}
