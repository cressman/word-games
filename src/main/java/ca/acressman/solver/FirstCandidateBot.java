package ca.acressman.solver;

import ca.acressman.Dictionary;

public class FirstCandidateBot extends BaseSolver {

    public FirstCandidateBot(Dictionary dict) {
        super(dict);
    }

    protected char[] nextGuess() {
        return candidates.get(0);
    }

}
