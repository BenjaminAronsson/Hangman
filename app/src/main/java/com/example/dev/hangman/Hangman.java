package com.example.dev.hangman;

import java.util.ArrayList;
import java.util.Random;

public class Hangman {
    //fields
    private ArrayList<String> words;
    private ArrayList<String> guessedLetters = new ArrayList<>();
    //private ArrayList<Character> hiddenWord = new ArrayList<>();
    private String choosenWord = "test";
    String word = "Hello";
    private char guessingLetter;
    private int guessesLeft = 10;
    private static Random random = new Random();


    public Hangman(ArrayList<String> words) {
        this.words = words;
    }
    public Hangman() {
       ArrayList<String> words = new ArrayList<>();
       words.add("Hej");
       words.add("Panda");
       words.add("Ferrari");
       this.words = words;
    }

    //Returns a String with all wrong guesses the user has made
    public String getBadLettersUsed() {
        //creates string builder
        StringBuilder builder = new StringBuilder();
        //loop for each guess made
        for (String letter : guessedLetters) {
            //If the letter is not in the word
            if (!word.contains(letter)) {
                //adds the
                builder.append(", " +letter);
            }
        }
        return builder.toString();
    }


    //Returns the current word, hiding all the letters the user hasn't guessed yet Example: Word is "N-KLAS". java.lang.String
    public String getHiddenWord() {
        //for every character in word
        for (int i = 0; i < choosenWord.length();) {
            //for every guessed letter
            for (String a: guessedLetters) {

                // TODO om bokstaven finns i ordet lägg till den
                if (hiddenWord.get(i).equals(choosenWord.charAt())) {

                }
                //TODO annars lägg till ett - istället
                else ()
            }
        }
        return //TODO hidden word;
    }

     void guess(String guess) {
         //TODO Makes a guess for a letter.
    }




    //Returns the current word, without any hidden letters.
    public String getChoosenWord() {
        return choosenWord;
    }

    //Returns the number of guesses left.
    public int getGuessesLeft() {
        return guessesLeft;
    }

    // Checks to see if the user has used up all her guesses
    public boolean hasLost() {
        return guessesLeft <= 0;
    }

    //Checks to see if the supplied char has been guessed for already (erroneously or correctly)
    public boolean	hasUsedLetter(String c) {
        for (String letter : guessedLetters) {
            if (c.contains(letter)) {
                return true;
            }
        }
        return false;
    }

    //Checks to see if the user has guessed all letters correctly
    public boolean hasWon() {
        return getHiddenWord().toString().equals(word);
    }

    //randomizes a new word from the list
    public void newWord() {
        int index = random.nextInt(words.size());
        word = words.get(index);
    }
}
