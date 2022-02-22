package ca.acressman.result;

import ca.acressman.puzzle.Clue;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private final long startTime;
    private long endTime;
    private String solution;
    private final List<Clue> clues = new ArrayList<>();

    public Result() {
        startTime = System.currentTimeMillis();
    }

    public Integer getGuessCount() {
        return clues.size();
    }

    public void print() {
        for (int i = 0; i < clues.size(); i++) {
            System.out.println("Guess: " + String.valueOf(clues.get(i).getGuess())
                    + " | Result: " + String.valueOf(clues.get(i).getResult()));
        }
        System.out.println("Solved in " + getRuntime() + " milliseconds.");
    }

    public void finish() {
        endTime = System.currentTimeMillis();
        solution = String.valueOf(clues.get(clues.size() - 1).getGuess());
    }

    public long getRuntime() {
        return endTime - startTime;
    }

    public void addClue(Clue clue) {
        clues.add(clue);
    }

    public String getSolution() {
        return solution;
    }
}
