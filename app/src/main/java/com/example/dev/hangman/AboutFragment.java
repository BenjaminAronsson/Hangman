package com.example.dev.hangman;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container != null) {
            container.removeAllViews();
        }


        return inflater.inflate(R.layout.fragment_about, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*Objects.requireNonNull(getActivity()).findViewById(R.id.themeButton).setOnClickListener(
                //points to method buttonClicked
                this::buttonClicked);*/

        ((StartActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((StartActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);

        //tells fragment it has a toolbar
        setHasOptionsMenu(true);

        /******* theme ********/

        Button theme_button = getActivity().findViewById(R.id.themeButton);
        if(theme_button != null) {
            theme_button.setOnClickListener(
                    //points to method buttonClicked
                    view -> themeButtonPressed(view));
        }

        /******* end theme ********/
    }

    private void themeButtonPressed(View view) {



        StartActivity active = (StartActivity) getActivity();

        view.findViewById(R.id.themeButton).setBackgroundColor(getResources().getColor(R.color.primaryColor));
        /*if(active.getFlag() ) {
            view.findViewById(R.id.themeButton).setBackgroundColor(getResources().getColor(R.color.primaryColor));
        }
        else {
            view.findViewById(R.id.themeButton).setBackgroundColor(getResources().getColor(R.color.primaryColor));
        }*/


            ((Button)view).setText(R.string.change_theme_button_pressed);


        active.themeButtonPressed();
    }


    private void buttonClicked(View view) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu.tool_bar, menu);
        menu.findItem(R.id.action_about).setVisible(false);
        menu.findItem(R.id.action_play).setVisible(false);

    }
}
