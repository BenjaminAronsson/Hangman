package com.example.dev.hangman;



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
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    private ImageView im;


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


        ((StartActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((StartActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);

        //tells fragment it has a toolbar
        setHasOptionsMenu(true);

        //set imageview
        im = getActivity().findViewById(R.id.imageView);


        //button listener
        Button theme_button = getActivity().findViewById(R.id.themeButton);
        if(theme_button != null) {
            theme_button.setOnClickListener(
                    //points to method buttonClicked
                    this::themeButtonPressed);
        }

        //loads theme
        themeButtonPressed(getView());
        themeButtonPressed(getView());


    }

    //change theme
    private void themeButtonPressed(View view) {
        StartActivity active = (StartActivity) getActivity();

        //test theme
        if(!active.getFlag() ) {
            Picasso.get().load("https://benjaminaronsson.github.io/Hangman/theme/standard/hang0.gif").placeholder(R.drawable.hanger).into(im);
        }
        else {
            Picasso.get().load("https://benjaminaronsson.github.io/Hangman/theme/halloween/hang0.gif").placeholder(R.drawable.hanger).into(im);
        }
        //get activity

        //change theme
        active.themeButtonPressed();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu.tool_bar, menu);
        menu.findItem(R.id.action_about).setVisible(false);
        menu.findItem(R.id.action_play).setVisible(false);
    }


}
