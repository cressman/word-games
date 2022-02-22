package ca.acressman.puzzle;

import java.util.Arrays;

public class WordlePuzzle implements Puzzle {
    public static char CORRECT_LOCATION = 'G';
    public static char CORRECT_LETTER = 'Y';
    public static char INCORRECT = 'B';

    private final char[] word;

    public WordlePuzzle(char[] word) {
        this.word = word;
    }

    @Override
    public WordleClue getResult(char[] guess) {
        char[] result = new char[5];
        Arrays.fill(result, INCORRECT);
        // According to the following resource, if you repeat a letter more times than it shows
        // up in the word, it will show any correct position, or otherwise the first non-correct position.
        // https://nerdschalk.com/wordle-same-letter-twice-rules-explained-how-does-it-work/
        // So, first go through to check for correct letter postions ...
        for (int i = 0; i < 5; i++) {
            if (word[i] == guess[i]) {
                result[i] = CORRECT_LOCATION;
            }
        }
        // ...then go through the actual word and fill in the result left to right with correct letters.
        for (int i = 0; i < 5; i++) {
            if (result[i] == CORRECT_LOCATION) {
                continue;
            }
            char c = word[i];
            // check if guessed
            for (int j = 0; j < 5; j++) {
                if (guess[j] == c && result[j] == INCORRECT) {
                    result[j] = CORRECT_LETTER;
                    break;
                }
            }
        }
        return new WordleClue(guess, result);
    }
}
