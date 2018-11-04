package com.example.dev.hangman;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameOverFragment extends Fragment {

    private TextView guessesMadeView;
    private TextView wordView;
    private TextView endMessageView;

    public GameOverFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (container != null) {
            container.removeAllViews();
        }

        return inflater.inflate(R.layout.fragment_game_over, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //tells fragment it has a toolbar
        setHasOptionsMenu(true);
        Objects.requireNonNull(((StartActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((StartActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);


        //set view
        endMessageView = Objects.requireNonNull(getView()).findViewById(R.id.gameOverMessage);
        wordView = getView().findViewById(R.id.chosenWordTextView);
        guessesMadeView = getView().findViewById(R.id.guessesMadeTextView);



        //adds button
        getActivity().findViewById(R.id.toMenyButton).setOnClickListener(
                //points to method buttonClicked
                view -> backToMenu());

    }

    @Override
    public void onResume() {
        super.onResume();
        //load data
        loadPreferences();
    }

    private void backToMenu() {
        Fragment menuFragment = StartActivity.menuFragment;

        FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        transaction.replace(R.id.mainFrame, menuFragment);
        // and add the transaction to the back stack if needed
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    private void loadPreferences() {

        //load data
        SharedPreferences prefs = Objects.requireNonNull(getActivity()).getSharedPreferences("default", MODE_PRIVATE);
        String word = prefs.getString("chosen word", "Hello");//"No name defined" is the default value.
        int guessesLeft = prefs.getInt("guesses left", 10); //0 is the default value.
        boolean isWin = prefs.getBoolean("is win",  false);


        String i = Integer.toString(guessesLeft);

        //test for win or lose
        String j;
        if (isWin) {
            j = getString(R.string.Congratulation);
        }
        else {
            j = getString(R.string.good_luck);
        }

        //sets text fields
        guessesMadeView.setText(getString(R.string.guesses_left1) +i +getString(R.string.guesses_left2));
        wordView.setText(getString(R.string.word_was) +word);
        endMessageView.setText(j);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //sets visible items
        menu.findItem(R.id.action_about).setVisible(true);
        menu.findItem(R.id.action_play).setVisible(false);
    }


}
