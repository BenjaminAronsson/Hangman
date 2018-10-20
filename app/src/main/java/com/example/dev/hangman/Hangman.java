package com.example.dev.hangman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    //fields
    private ArrayList<String> words;
    private ArrayList<String> guessedLetters = new ArrayList<>();
    private ArrayList<Boolean> hiddenWord = new ArrayList<>();
    private String word = "Hello";
    private int guessesLeft = 9;
    private static Random random = new Random();
    private Scanner sc = new Scanner(System.in);
    private String error =  "false";

    public static void main(String args[]) {
        Hangman hangman = new Hangman();
        System.out.println(hangman.word);
        System.out.println(hangman.getHiddenWord());

        while (true) {
            hangman.guess(hangman.sc.next());
            System.out.println(hangman.getBadLettersUsed());
            System.out.println(hangman.getHiddenWord());
            System.out.println(hangman.getGuessesLeft());
        }

    }


    public Hangman(ArrayList<String> words) {
        this.words = words;
        newWord();
    }
    public Hangman() {
       ArrayList<String> words = new ArrayList<>();
       words.add("Hej");
       words.add("Panda");
       words.add("Ferrari");
        words.add("Klocka");
        words.add("Blomma");
       words.add("Hello");
       this.words = words;
       newWord();
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
    //TODO flera lika dana bokstäver
    public String getHiddenWord() {
        StringBuilder sb = new StringBuilder();
        String letter;
        for (int i = 0; i < word.length(); i++) {
            if (!hiddenWord.get(i)) {
                sb.append("-");
            }
            else {
                letter = word.substring(i, i+1);
                sb.append(letter);
            }
        }
        return sb.toString();
    }

    public void guess(String guess) {

        if (isGameContinuing()) {

            //Make everything lowercase
            guess = guess.toLowerCase();

            if(isGuessCorrect(guess)) {
                //adds letter to guesses
                guessedLetters.add(guess);

                //if letter in word
                guessForLetterInWord(guess);
            }
        }
    }

    public String getError() {
        return error;
    }

    private boolean isGuessCorrect(String guess) {
        //TODO Makes a guess for a letter.
        if (guess.length() > 1) {
            //TODO toMannyLetters();
            error = "many";
           return false;
        }

        //if letter is all ready used
        else if (hasUsedLetter(guess)) {

            //TODO letterallreadyused();
            error = "used";
            return false;
        }
        else {
            error = "false";
            return true;
        }
    }

    private void guessForLetterInWord(String guess) {
        if (word.contains(guess)) {
            //loop through word
            for (int i = 0; i < word.length(); i++) {
                //test letter for letter
                 if (word.charAt(i) == guess.charAt(0)) {
                     //show the letter
                     hiddenWord.set(i, true);
                 }
            }

        } else {
            guessesLeft--;
        }
    }


    //Returns the current word, without any hidden letters.
    public String getChoosenWord() {
        return word;
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
        return getHiddenWord().equals(word);
    }

    //randomizes a new word from the list
    public void newWord() {
        int index = random.nextInt(words.size());
        word = words.get(index).toLowerCase();

        hiddenWord.clear();
        for (int i = 0; i < word.length(); i++) {
            hiddenWord.add(false);
        }
    }

    public boolean isGameContinuing() {
        return hasWon() || !hasLost();
    }
}
