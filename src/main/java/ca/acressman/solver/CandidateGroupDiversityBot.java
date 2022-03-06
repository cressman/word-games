package ca.acressman.solver;

import ca.acressman.Dictionary;
import ca.acressman.puzzle.WordlePuzzle;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the solver that I originally envisioned when thinking about the most optimal solution possible
 * I want to make each guess such that the number of remaining candidates is minimized.
 * How?
 * Consider all remaining possible puzzles. For any given guess that we could make, we can record the clue that each
 * puzzle would provide. By sorting and counting these clues, we can form groups of subsequent candidates that each
 * guess and result would reveal.
 * Score each possible guess by taking the probability that a puzzle will fall in each candidate group times
 * the size of that group. The guess with the lowest score is likely to narrow down the next round of candidates
 * the most.
 */
public class CandidateGroupDiversityBot extends BaseSolver {

    // as an optimization, for each dictionary store the first guess - it will always be the same anyway.
    private static final Map<String, char[]> CACHED_FIRST_GUESS = new HashMap<>();
    private final Dictionary dict;

    public CandidateGroupDiversityBot(Dictionary dict) {
        super(dict);
        this.dict = dict;
        CACHED_FIRST_GUESS.putIfAbsent(dict.getFileName(), nextGuess());
    }

    protected char[] nextGuess() {
        if (candidates.size() == dict.size() && CACHED_FIRST_GUESS.containsKey(dict.getFileName())) {
            return CACHED_FIRST_GUESS.get(dict.getFileName());
        }

        // initialization to empty char array is for the benefit of the compiler.
        // We only get here if there are candidates, and one will be selected.
        char[] bestCandidate = new char[0];
        long bestScore = Long.MAX_VALUE;
        for (char[] hypotheticalGuess : candidates) {
            var resultGroupCounts = new HashMap<String, Long>();
            for (char[] candidate : candidates) {
                var hypotheticalPuzzle = new WordlePuzzle(candidate);
                var result = hypotheticalPuzzle.getResult(hypotheticalGuess).getResult();
                resultGroupCounts.merge(String.valueOf(result), 1L, Long::sum);
            }
            // The probability that a puzzle will fall into the group is (proportional to) the size of the group
            Long score = resultGroupCounts.values().stream()
                    .reduce(0L, (sumsq, nextCount) -> sumsq + nextCount * nextCount);
            if (score < bestScore) {
                bestScore = score;
                bestCandidate = hypotheticalGuess;
            }
        }
        return bestCandidate;
    }

}
