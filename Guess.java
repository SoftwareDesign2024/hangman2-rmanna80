package game;

/*Ryan manna
 * 
 */
import java.util.Scanner;

import util.ConsoleReader;
import util.DisplayWord;

public class Guess {

    // Constant representing all available alphabet letters
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    // Tracks letters that have not yet been guessed by the player
    protected StringBuilder remainingLetters;

    // Executioner that manages the game's secret word and "cheating" behavior
    protected Executioner gameExecutioner;

    public Guess(Executioner executioner) {
        gameExecutioner = executioner;
        remainingLetters = new StringBuilder(ALPHABET);
    }

    /*
     * updates player guess and sees if in executioner the guess is in the secret
     */
    public boolean processPlayerGuess(char guessedLetter, DisplayWord displayWord) {

        gameExecutioner.applyCheatingStrategy(guessedLetter, displayWord);

        // Check if the guessed letter has already been used
        int letterIndex = remainingLetters.indexOf("" + guessedLetter);
        if (letterIndex >= 0) {
            // remove the letter from remaining letters
            removeGuessedLetter(letterIndex);

            // Check if the guessed letter is not in the secret word
            if (!gameExecutioner.isGuessCorrect(guessedLetter, displayWord)) {
                return true; // The guess was incorrect but not repeated
            }
        }
        return false;
    }

    public String promptPlayerGuess() {
        return ConsoleReader.promptString("Make a guess: ");
    }

    protected void removeGuessedLetter(int index) {
        remainingLetters.deleteCharAt(index);
    }

    public void displayRemainingLetters() {
        System.out.println(remainingLetters);
    }
}
