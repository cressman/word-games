package ca.acressman.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordleClueTest {

    @Test
    public void testMatches() {
        var clue = new WordleClue("aahed".toCharArray(), "GGYBB".toCharArray());
        assertTrue(clue.matches("aargh".toCharArray()));

        clue = new WordleClue("feals".toCharArray(), "BGGBY".toCharArray());
        assertFalse(clue.matches("geare".toCharArray()));
    }
}