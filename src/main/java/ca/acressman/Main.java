package ca.acressman;

import ca.acressman.puzzle.Puzzle;
import ca.acressman.puzzle.WordlePuzzle;
import ca.acressman.result.Result;
import ca.acressman.result.StatsAggregator;
import ca.acressman.solver.Solver;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        var props = Config.getProperties();
        var dict = new Dictionary(props);
        var solverFQCN = props.getProperty("solver", "ca.acressman.solver.FirstCandidateBot");
        var overallStats = new StatsAggregator();
        String currentPuzzle = props.getProperty("puzzle");
        if (currentPuzzle == null) {
            // for each word in the dictionary, create a puzzle and then see how long it takes the solver to solve it.
            for (char[] word : dict.getWords()) {
                System.out.println("The puzzle: " + String.valueOf(word));
                Puzzle puzzle = new WordlePuzzle(word);
                Result result = getSolver(solverFQCN, dict).solve(puzzle);
                overallStats.add(result);
            }
            overallStats.printSummary();
        } else {
            Puzzle puzzle = new WordlePuzzle(currentPuzzle.toCharArray());
            Result result = getSolver(solverFQCN, dict).solve(puzzle);
            result.print();
        }
    }

    private static Solver getSolver(String className, Dictionary dict) {
        try {
            return (Solver) Class
                    .forName(className)
                    .getConstructor(Dictionary.class)
                    .newInstance(dict);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                NoSuchMethodException | ClassNotFoundException e) {
            System.out.println("The specified solver class is invalid!");
            throw new IllegalStateException("Configuration Error");
        }
    }
}
