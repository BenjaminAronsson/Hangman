package com.example.dev.hangman;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void startButtonClicked(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        // Get the text view
        //noteView  = findViewById(R.id.editText);
        //String note = noteView.getText().toString();

        //intent.putExtra("Message", note);
        startActivity(intent);
    }

    public void InfoButtonClicked(View view) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //transaction.add(R.id.container,GameFragment.class,"param1");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
