package ca.acressman.puzzle;

public interface Clue {
    boolean matches(char[] word);

    boolean isSolved();

    char[] getGuess();

    char[] getResult();
}
