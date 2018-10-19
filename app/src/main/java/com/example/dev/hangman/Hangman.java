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
    private char guessingLetter;
    private int guessesLeft = 9;
    private static Random random = new Random();
    private Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        Hangman hangman = new Hangman();
        System.out.println(hangman.word);
        System.out.println(hangman.getHiddeWord());

        while (true) {
            hangman.guess(hangman.sc.next());
            System.out.println(hangman.getBadLettersUsed());
            System.out.println(hangman.getHiddeWord());
            System.out.println(hangman.getGuessesLeft());
        }

    }


    public Hangman(ArrayList<String> words) {
        this.words = words;
        newWord();
    }
    public Hangman() {
       ArrayList<String> words = new ArrayList<>();
       /*words.add("Hej");
       words.add("Panda");
       words.add("Ferrari");
        words.add("Klocka");
        words.add("Blomma");*/
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
    public String getHiddenWord() {
        StringBuilder sb = new StringBuilder();
        //for every character in word
        for (int i = 0; i < word.length();) {
            //for every guessed letter

            for (String guesses: guessedLetters) {

                //bokstaven i ordet
                char letterInWord = word.charAt(i);

                // TODO om bokstaven finns i ordet lägg till den
                if (letterInWord == guesses.charAt(0) ) {
                    sb.append(letterInWord);
                }
                //TODO annars lägg till ett - istället
                else {
                    sb.append("-");
                }
            }
            sb.append("a");
        }
        return sb.toString();
    }

    public String getHiddeWord() {
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
            //TODO Makes a guess for a letter.

            //if letter is all ready used
            if (hasUsedLetter(guess)) {
                return;
                //TODO något
            }
            //adds letter to guesses
            guessedLetters.add(guess);

            //if letter in word
            if (word.contains(guess)) {
                int letterNumber = word.indexOf(guess);
                hiddenWord.set(letterNumber, true);
            } else {
                guessesLeft--;
            }
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
        return getHiddenWord().toString().equals(word);
    }

    //randomizes a new word from the list
    public void newWord() {
        int index = random.nextInt(words.size());
        word = words.get(index);

        hiddenWord.clear();
        for (int i = 0; i < word.length(); i++) {
            hiddenWord.add(false);
        }
    }

    public boolean isGameContinuing() {
        return hasWon() || !hasLost();
    }
}
