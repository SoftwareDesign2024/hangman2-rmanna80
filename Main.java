
/*
 * Ryan Manna
 */
import game.HangmanGame;
import game.AutoGuess;
import game.CheatingExecutioner;
import game.Executioner;
import game.Guess;
import util.HangmanDictionary;

public class Main {

    public static final String DICTIONARY = "C:\\Users\\Owner\\Downloads\\HangmanLab1\\HangmanLab1\\data\\lowerwords.txt";
    public static final int NUM_LETTERS = 6;
    public static final int NUM_MISSES = 8;

    public static void main(String[] args) {

        HangmanDictionary dictionary = new HangmanDictionary(DICTIONARY);

        // Executioner gameExecutioner = new Executioner();
        Executioner gameExecutioner = new CheatingExecutioner(dictionary, NUM_LETTERS);

        // Guess autoGuesser = new Guess(gameExecutioner);
        Guess autoGuesser = new AutoGuess(gameExecutioner);

        new HangmanGame(dictionary, NUM_LETTERS, NUM_MISSES, gameExecutioner, autoGuesser).play();
    }
}
