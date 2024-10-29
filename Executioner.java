package game;

/*
 * Ryan Manna
 */
import util.DisplayWord;
import util.HangmanDictionary;

public class Executioner {

    // The secret word that the player is trying to guess
    protected String secretWord;

    public boolean isGuessCorrect(char guessedChar, DisplayWord displayWord) {
        if (secretWord.indexOf(guessedChar) >= 0) {
            displayWord.update(guessedChar, secretWord); // Update display with correct guesses
            return true;
        }
        return false;
    }

    public void selectSecretWord(HangmanDictionary dictionary, int wordLength) {
        secretWord = dictionary.getRandomWord(wordLength).toLowerCase(); // Select random word from dictionary
    }

    public DisplayWord getDisplayWordForSecret() {
        return new DisplayWord(secretWord);
    }

    public void revealSecretWord() {
        System.out.println(secretWord);
    }

    public boolean doesDisplayMatchSecret(DisplayWord displayWord) {
        return displayWord.equals(secretWord);
    }

    public void applyCheatingStrategy(char guessedChar, DisplayWord displayWord) {
        // No cheating behavior in the base Executioner class
    }
}
