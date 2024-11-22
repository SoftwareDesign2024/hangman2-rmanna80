package game;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;

/**
 * Ryan Manna
 */
public class HangmanGame {

    // Number of incorrect guesses the player can make before losing
    private int remainingGuesses;

    // The word display that shows correct guesses and blanks for unguessed letters
    private DisplayWord displayWord;

    // Executioner responsible for managing the secret word and validating guesses
    private Executioner gameExecutioner;

    // Guesser handling the player's input and tracking guessed letters
    private Guess guessHandler;

    public HangmanGame(HangmanDictionary dictionary, int wordLength, int maxGuesses, Executioner executioner,
            Guess guesser) {
        gameExecutioner = executioner;
        guessHandler = guesser;
        remainingGuesses = maxGuesses;

        // Select a secret word of the specified length and set up the display word
        gameExecutioner.selectSecretWord(dictionary, wordLength);
        displayWord = gameExecutioner.getDisplayWordForSecret();
    }

    public void play() {
        boolean isGameOver = false;

        while (!isGameOver) {
            printStatus();

            // Prompt the player for a guess
            String playerGuess = guessHandler.promptPlayerGuess();

            // Validate that the guess is a single alphabetic character
            if (playerGuess.length() == 1 && Character.isAlphabetic(playerGuess.charAt(0))) {
                char guessedChar = playerGuess.toLowerCase().charAt(0);

                // Process the guess and reduce guesses left if the guess was incorrect
                if (guessHandler.processPlayerGuess(guessedChar, displayWord)) {
                    remainingGuesses--;
                }

                // Check if the game has ended
                if (isGameLost()) {
                    System.out.println("YOU ARE HUNG!!!");
                    isGameOver = true;
                } else if (isGameWon()) {
                    System.out.println("YOU WIN!!!");
                    isGameOver = true;
                }
            } else {
                System.out.println("Please enter a single letter...");
            }
        }
        gameExecutioner.revealSecretWord();

    }

    private boolean isGameWon() {
        return gameExecutioner.doesDisplayMatchSecret(displayWord);
    }

    private boolean isGameLost() {
        return remainingGuesses == 0;
    }

    // Print game stats
    private void printStatus() {
        System.out.println(displayWord);
        System.out.println("Remaining guesses: " + remainingGuesses);
        System.out.print("Available letters: ");
        guessHandler.displayRemainingLetters();

        System.out.print("Secret word : ");
        gameExecutioner.revealSecretWord();
        System.out.println();
    }
}