package ca.acressman.solver;

import ca.acressman.Dictionary;

import java.util.Random;

public class RandomCandidateBot extends BaseSolver {
    private static final Random r = new Random();

    public RandomCandidateBot(Dictionary dict) {
        super(dict);
    }

    protected char[] nextGuess() {
        return candidates.get(r.nextInt(candidates.size()));
    }

}
