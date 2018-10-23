package com.example.dev.hangman;

import java.util.ArrayList;
import java.util.Random;

public class Hangman {
    //fields
    private ArrayList<String> words;
    private int guessesLeft = 9;
    private static Random random = new Random();
    private ArrayList<String> guessedLetters = new ArrayList<>();
    private ArrayList<Boolean> hiddenWord = new ArrayList<>();
    private String word = "Hello";
    private static Hangman data = null;



    public void setGuessedLetters(ArrayList<String> guessedLetters) {
        this.guessedLetters = guessedLetters;
    }

    public void setGuessesLeft(int guessesLeft) {
        this.guessesLeft = guessesLeft;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<String> getGuessedLetters() {
        return guessedLetters;
    }

    public String getWord() {
        return word;
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
        int t = 0;
        //loop for each guess made
        for (String letter : guessedLetters) {
            //If the letter is not in the word
            if (!word.contains(letter)) {
                if (t == 0) {
                    builder.append(letter);
                    t++;
                }
                else {
                    //adds the
                    builder.append(", " + letter);
                }
            }
        }
        return builder.toString();
    }

    //Returns the current word, hiding all the letters the user hasn't guessed yet Example: Word is "N-KLAS". java.lang.String
    public String getHiddenWord() {
        StringBuilder sb = new StringBuilder();
        String letter;
        for (int i = 0; i < word.length(); i++) {
            if (hiddenWord.get(i) == null || !hiddenWord.get(i) ) {
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

    private boolean isGuessCorrect(String guess) {
        //if more then one letter
        if (guess.length() > 1 ) {
           return false;
        }
        //if no letter
        else if (guess.length() <= 0) {
            return false;
        }
        //if letter is not letter
        else if(!Character.isAlphabetic(guess.charAt(0))) {
            return false;
        }
        //if letter is all ready used
        else if (hasUsedLetter(guess)) {
            return false;
        }
        else {
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
        return !(hasWon() || hasLost());

    }

    public void newGame() {
        newWord();
        guessesLeft = 9;
        guessedLetters = new ArrayList<>(); //TODO clear old list
    }

    public Hangman loadGame() {

            if (data == null){
                data = new Hangman();
            }
            return data;

    }

    public void saveGame() {
        Hangman.data = this;
    }
}
