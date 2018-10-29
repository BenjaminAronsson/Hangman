package com.example.dev.hangman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MenuFragment extends Fragment {

    private FragmentTransaction transaction;


    public MenuFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.playButton).setOnClickListener(
                //points to method buttonClicked
                this::playButtonClicked);

        getActivity().findViewById(R.id.aboutButton).setOnClickListener(
                //points to method buttonClicked
                this::aboutButtonClicked);
    }


    private void playButtonClicked(View view) {
        // Create new fragment and transaction

        Fragment gameFragment = StartActivity.gameFragment;

        transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.mainFrame, gameFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    private void aboutButtonClicked(View view) {
        // Create new fragment and transaction
        Fragment aboutFragment = StartActivity.aboutFragment;
        transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.mainFrame, aboutFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}



