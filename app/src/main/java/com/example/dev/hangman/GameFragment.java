package com.example.dev.hangman;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


public class GameFragment extends Fragment{

    public GameFragment() {
        // Required empty public constructor
    }

    private EditText inputField;
    private TextView guesses;
    private TextView guessesMade;
    private ImageView hangmanView;
    private Hangman hangman = new Hangman();
    private String hangmanPicturePath;

    // This is the game object
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        //loads theme from activity
        StartActivity active = (StartActivity) getActivity();
        String theme;
        if(Objects.requireNonNull(active).getFlag() ) {
            theme ="standard/";
        }
        else {
            theme = "halloween/";
        }
        String PATH_TO_RESOURCES = "https://benjaminaronsson.github.io/Hangman/theme/";
        hangmanPicturePath = PATH_TO_RESOURCES + theme;

        //draws layout
        layoutUpdate();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //sets toolbar
        setHasOptionsMenu(true);
        Objects.requireNonNull(((StartActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((StartActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);

         //sets object references
         InitializeViewObjects();


        //Preload images
        int HANGMAN_PLACEHOLDER = R.drawable.hanger;
        Picasso.get().load(hangmanPicturePath).placeholder(HANGMAN_PLACEHOLDER).into(hangmanView);



        //load game
        hangman = hangman.loadGame(getResources().getStringArray(R.array.wordList));

        //set onclick listener for guess button
        Objects.requireNonNull(getActivity()).findViewById(R.id.guessButton).setOnClickListener(
                //points to method buttonClicked
                view -> guessButtonPressed());
    }

    private void InitializeViewObjects() {

        //find all views
        inputField = Objects.requireNonNull(getView()).findViewById(R.id.guessText);
        guesses = getView().findViewById(R.id.hiddenWord);
        guessesMade = getView().findViewById(R.id.guessedLetters);
        hangmanView = Objects.requireNonNull(getActivity()).findViewById(R.id.hangmanView);
        hangmanView.setTag(hangman.getGuessesLeft());
    }

    private void loadHangmanImages() {

         //load images number
         String imagesNumber = "hang"+ Integer.toString(hangman.getGuessesLeft()) +".gif";

         //load path
         String url =  hangmanPicturePath +imagesNumber;
         //update hangman pic
         if (hangman.getGuessesLeft() < 10 && hangman.getGuessesLeft() >= 0) {

            Picasso.get().load(url).placeholder(hangmanView.getDrawable()).into(hangmanView);
         }
    }

    @Override
    public void onPause() {
        super.onPause();
        hangman.saveGame();

    }

    private void savePreferences() {
        //save all necessary data
        SharedPreferences.Editor editor = Objects.requireNonNull(getActivity()).getSharedPreferences("default", MODE_PRIVATE).edit();
        editor.putString("chosen word", hangman.getChoosenWord());
        editor.putInt("guesses left", hangman.getGuessesLeft());
        editor.putBoolean("is win", hangman.isWin());
        editor.commit();

    }


    private void guessButtonPressed() {


        if(hangman.isGameContinuing()) {

            //gets input from textfield
            String input = getInput(inputField);

            //tests if input is valid
            inputCorrect(input);

            //guesses
            hangman.guess(input);

            //draws layout
            layoutUpdate();

            //test if player won or lost
            if (hangman.isWin() || hangman.isLose()) {
                gameOver();
            }
        }
        inputField.setText("");
    }


    private void layoutUpdate() {

        //loads images
        loadHangmanImages();

        //updates text field
        guesses.setText(hangman.getHiddenWord());
        guessesMade.setText(hangman.getBadLettersUsed());
        inputField.setText("");

    }

    private void gameOver() {
        //save data
        savePreferences();

        //starts new games
        hangman.newGame();
        // Create new fragment and transaction
        Fragment gameOverFragment = StartActivity.gameOverFragment;
        FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        transaction.replace(R.id.mainFrame, gameOverFragment, "gameOverID");

        // Commit the transaction
        transaction.commit();
    }

    private String getInput(EditText inputField) {
        //takes guess from input field
        return inputField.getText().toString();

    }

    private void inputCorrect(String input) {
        //test for more then one character
        if (input.length() > 1) {
            toastWrongInput();
        }
        //test for no input
        else if (input.length() <= 0) {
            toastNoInput();
        }
        //test for letter all ready used
        else if (hangman.hasUsedLetter(input.toLowerCase())) {
            toastDuplicateGuess();
        }
        //test for special character
        else if(!Character.isAlphabetic(input.charAt(0))) {
            toastNoLetter();
        }
    }

    private void toastNoInput() {
        Toast myToast = Toast.makeText(getActivity(), R.string.toast1, Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 600);
        myToast.show();
    }

    private void toastNoLetter() {
        Toast myToast = Toast.makeText(getActivity(), R.string.toast2, Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 600);
        myToast.show();
    }

    private void toastWrongInput() {
        Toast myToast = Toast.makeText(getActivity(),  R.string.toast3, Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 600);
        myToast.show();
    }

    private void toastDuplicateGuess() {
        Toast myToast = Toast.makeText(getActivity(),  R.string.toast4, Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 600);
        myToast.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //sets visible items
        menu.findItem(R.id.action_about).setVisible(true);
        menu.findItem(R.id.action_play).setVisible(false);
    }

}
