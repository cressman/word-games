package ca.acressman.solver;

import ca.acressman.puzzle.Puzzle;
import ca.acressman.result.Result;

public interface Solver {
    Result solve(Puzzle puzzle);
}
