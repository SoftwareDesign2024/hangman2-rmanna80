package game;

/*
 * Ryan Manna
 */
import util.DisplayWord;

public class AutoGuess extends Guess {

    private static final String FREQUENCY = "etaoinshrldcumfpgwybvkxjqz";

    private String myLetters;
    private int index;

    public AutoGuess(Executioner e) {
        super(e);
        myLetters = FREQUENCY;
        index = 0;
    }

    public String getGuess() {
        return "" + myLetters.charAt(index++);
    }

}