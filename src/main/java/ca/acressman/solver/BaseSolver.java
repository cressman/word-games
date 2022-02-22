package ca.acressman.solver;

import ca.acressman.Dictionary;
import ca.acressman.puzzle.Clue;
import ca.acressman.puzzle.Puzzle;
import ca.acressman.result.Result;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSolver implements Solver {
    protected final List<char[]> candidates;
    protected final Result result = new Result();

    public BaseSolver(Dictionary dict) {
        candidates = new ArrayList<>(dict.getWords());
    }

    abstract protected char[] nextGuess();

    protected void processClue(Clue clue) {
        candidates.removeIf(candidate -> !clue.matches(candidate));
    }

    @Override
    public Result solve(Puzzle puzzle) {
        while (true) {
            Clue clue = puzzle.getResult(nextGuess());
            processClue(clue);
            result.addClue(clue);
            if (candidates.size() == 0) {
                result.print();
                throw new RuntimeException("Solution not found");
            } else if (clue.isSolved()) {
                result.finish();
                break;
            }
        }
        return result;
    }
}
