package ca.acressman.result;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsAggregator {
    private final Map<Integer, AtomicInteger> numGuesses = new HashMap<>();
    private int numPuzzles = 0;
    private int totalGuessCnt = 0;
    private long totalComputeMillis = 0;

    public void printSummary() {
        numGuesses.forEach((guessCount, numTimes) -> {
            System.out.println("Num guesses: " + guessCount + " happened " + numTimes + " times");
        });
        System.out.println("Average number of guesses = " + Integer.valueOf(totalGuessCnt).doubleValue() / numPuzzles);
        System.out.println("Time per solution = " + Long.valueOf(totalComputeMillis).doubleValue() / numPuzzles);
    }

    public void add(Result result) {
        numPuzzles ++;
        totalGuessCnt += result.getGuessCount();
        numGuesses.computeIfAbsent(result.getGuessCount(), key -> new AtomicInteger(0)).incrementAndGet();
        totalComputeMillis += result.getRuntime();
    }
}
