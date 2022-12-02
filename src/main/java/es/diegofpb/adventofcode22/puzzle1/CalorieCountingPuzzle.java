package es.diegofpb.adventofcode22.puzzle1;

import es.diegofpb.adventofcode22.Puzzle;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class CalorieCountingPuzzle extends Puzzle {

    public String solvePuzzlePartOne() {
        Puzzle.initialize(getPuzzleDate());
        return getHighestCalories();
    }

    @Override
    public String solvePuzzlePartTwo() {
        return getTopElvesWithHighestCalories(3);
    }

    @Override
    public LocalDate getPuzzleDate() {
        return LocalDate.of(2022, 12, 1);
    }

    private String getHighestCalories() {
        AtomicInteger maxCalories = new AtomicInteger();
        AtomicInteger currentElfCalories = new AtomicInteger();
        linesOfFile.forEach(line -> {
            if (line == null || line.isBlank()) {
                maxCalories.set(Math.max(currentElfCalories.get(), maxCalories.get()));
                currentElfCalories.set(0);
            } else {
                currentElfCalories.set(currentElfCalories.get() + Integer.parseInt(line));
            }
        });

        return String.valueOf(maxCalories.get());

    }

    private String getTopElvesWithHighestCalories(int numberOfElves) {
        List<Integer> caloriesOfAllElves = new ArrayList<>();
        int currentElfCalories = 0;

        for (String calorieLine : linesOfFile) {
            if (calorieLine == null || calorieLine.isBlank()) {
                caloriesOfAllElves.add(currentElfCalories);
                currentElfCalories = 0;
            } else {
                currentElfCalories += Integer.parseInt(calorieLine);
            }
        }

        return String.valueOf(caloriesOfAllElves.stream()
                .sorted(Comparator.reverseOrder())
                .limit(numberOfElves)
                .mapToInt(Integer::valueOf)
                .sum());

    }
}
