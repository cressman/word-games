package ca.acressman.puzzle;

public class WordleClue implements Clue {
    private final char[] guess;
    private final char[] result;

    public WordleClue(char[] guess, char[] result) {
        this.guess = guess;
        this.result = result;
    }

    @Override
    public boolean matches(char[] word) {
        // go through results and make sure everything that was correct letter or location are represented in the word
        // keep track of indexes used so that the same letter in the guess can not use the same clue twice
        boolean[] wordIndexesUsed = new boolean[] {false, false, false, false, false};

        // check correct location first as this will more quickly narrow things down
        for (int i = 0; i < 5; i++) {
            if (result[i] == WordlePuzzle.CORRECT_LOCATION) {
                if (word[i] != guess[i]) {
                    return false;
                }
                wordIndexesUsed[i] = true;
            } else if (word[i] == guess[i]) {
                return false;
            }
        }

        // now go through correct letters and make sure they show up
        for (int i = 0; i < 5; i++) {
            if (result[i] == WordlePuzzle.CORRECT_LOCATION) {
                // already validated, and these can not count
                continue;
            } else if (result[i] == WordlePuzzle.INCORRECT) {
                for (int j = 0; j < 5; j++) {
                    if (wordIndexesUsed[j]) {
                        // The guess might in fact still show up in the puzzle if there were duplicate letters
                        // in the guess, but those will already be marked as used. So only test that the unused
                        // word letters do not show up from the incorrect clue
                        continue;
                    }
                    if (word[j] == guess[i]) {
                        return false;
                    }
                }
            } else if (result[i] == WordlePuzzle.CORRECT_LETTER) {
                boolean found = false;
                for (int j = 0; j < 5; j++) {
                    if (wordIndexesUsed[j]) {
                        continue;
                    }
                    if (word[j] == guess[i]) {
                        wordIndexesUsed[j] = true;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            } else {
                throw new IllegalStateException();
            }
        }
        // we got through all the checks, so the word must match the guess
        return true;
    }

    @Override
    public boolean isSolved() {
        for (int i = 0; i < 5; i++) {
            if (result[i] != WordlePuzzle.CORRECT_LOCATION) {
                return false;
            }
        }
        return true;
    }

    @Override
    public char[] getGuess() {
        return guess;
    }

    @Override
    public char[] getResult() {
        return result;
    }
}
