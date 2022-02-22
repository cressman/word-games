package ca.acressman.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordlePuzzleTest {

    private String getStringResult(Puzzle puzzle, String guess) {
        return String.valueOf(puzzle.getResult(guess.toCharArray()));
    }

    @Test
    public void testResult() {
        var puzzle = new WordlePuzzle("ABCDD".toCharArray());
        assertEquals("GYYGY", getStringResult(puzzle, "ACDDB"));
        assertEquals("GYYYG", getStringResult(puzzle, "ACDBD"));
        assertEquals("YYBBB", getStringResult(puzzle, "DAAAX"));
        assertEquals("BYBBG", getStringResult(puzzle, "XAAAD"));
        assertEquals("BBBBB", getStringResult(puzzle, "XXXXX"));
        assertEquals("YYBBB", getStringResult(puzzle, "DDXXX"));
    }
}