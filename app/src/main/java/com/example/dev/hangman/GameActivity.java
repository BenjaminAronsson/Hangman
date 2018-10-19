package com.example.dev.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private Hangman hangman = new Hangman();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activitty);

        //TODO add word list from web

        hangman.newWord();



    }

    public void guessButtonPressed() {
        //om spelet fortsätter
        if (!(hangman.hasWon() || hangman.hasLost())) {
            //tar emot gisning
            char guess = getGuess();
            //gissar
            hangman.guess(guess);
        }
    }

    public char getGuess() {
        //tar gisning från input
        do {
            String input = getInput();
            //testar att input är godkänt
            if (inputCorrect(input)) {
                return input.charAt(0);
            }
            //todo loop or exception
        }while (true);
    }

    private boolean inputCorrect(String input) {
        if (input.length() > 1) {
            toastWrongInput();
            return false;
        }
        if (hangman.hasUsedLetter(input.charAt(0))) {
            toastDuplicateGuess();
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
