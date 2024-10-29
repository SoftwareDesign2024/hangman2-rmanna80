package game;

/*
 * Ryan Manna
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import util.DisplayWord;
import util.HangmanDictionary;

public class CheatingExecutioner extends Executioner {

    // List of potential words remaining that match the criteria
    private List<String> potentialWords;

    public CheatingExecutioner(HangmanDictionary dictionary, int wordLength) {
        super();
        potentialWords = dictionary.getWords(wordLength); // Load initial set of possible words based on length
    }

    public void applyCheatingStrategy(char guessedChar, DisplayWord currentDisplay) {
        // Map pattern of the display word to a list of matching words
        HashMap<DisplayWord, List<String>> patternToWordsMap = new HashMap<>();

        // For each word in the list, display pattern based on the guess
        for (String word : potentialWords) {
            DisplayWord pattern = new DisplayWord(currentDisplay); // Copy current word
            pattern.update(guessedChar, word); // Update pattern

            // Add this word to the list of words matching this pattern
            patternToWordsMap.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
        }

        // Find the pattern with the most words
        int maxPatternSize = 0;
        DisplayWord selectedPattern = new DisplayWord("");

        for (Entry<DisplayWord, List<String>> entry : patternToWordsMap.entrySet()) {
            int patternWordCount = entry.getValue().size();

            // maximum matching words
            if (patternWordCount > maxPatternSize) {
                maxPatternSize = patternWordCount;
                selectedPattern = entry.getKey();
            }
        }

        potentialWords = patternToWordsMap.get(selectedPattern);
        Collections.shuffle(potentialWords); // Randomize to keep the game fair but deceptive
        secretWord = potentialWords.get(0); // Select a new "secret" word
        currentDisplay = selectedPattern; // Update display to reflect the chosen pattern
    }
}
