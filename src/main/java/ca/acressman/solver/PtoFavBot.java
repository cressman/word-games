package ca.acressman.solver;

import ca.acressman.Dictionary;
import ca.acressman.puzzle.Clue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class PtoFavBot extends RandomCandidateBot {
    // List of favorite words to lead with - suggested by my group of work colleagues that wordle
    private static final List<char[]> FAVORITES = Stream.of(
            "irate", "roate", "audio", "ouija", "soare", "minty", "pluck", "raise", "stare", "steak", "cloud",
            "arise", "sated", "shire", "riots").map(String::toCharArray).toList();
    private static final Random r = new Random();
    private final List<char[]> favs = new ArrayList<>(FAVORITES);

    public PtoFavBot(Dictionary dict) {
        super(dict);
    }

    protected char[] nextGuess() {
        // Pick a random favorite word starter until the list is ruled out by previous clues
        if (favs.size() > 0) {
            return favs.get(r.nextInt(favs.size()));
        }
        // Then fall back to a random word from remaining candidates
        return super.nextGuess();
    }

    protected void processClue(Clue clue) {
        super.processClue(clue);
        favs.removeIf(f -> !clue.matches(f));
    }
}
