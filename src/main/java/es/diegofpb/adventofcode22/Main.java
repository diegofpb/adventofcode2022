package es.diegofpb.adventofcode22;

import es.diegofpb.adventofcode22.puzzle1.CalorieCountingPuzzle;
import es.diegofpb.adventofcode22.puzzle2.RockPaperScissorsPuzzle;
import es.diegofpb.adventofcode22.puzzle3.RucksackReorganizationPuzzle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    private static final String LOG_MESSAGE = "Puzzle of day [{}] - Part One result [{}] - Part two result [{}]";

    public static void main(String[] args) {


        Puzzle calorieCountingPuzzle = new CalorieCountingPuzzle();
        Puzzle rockPaperScissorsPuzzle = new RockPaperScissorsPuzzle();
        Puzzle rucksackReorganizationPuzzle = new RucksackReorganizationPuzzle();

        log.info(LOG_MESSAGE, calorieCountingPuzzle.getPuzzleDate().toString(),
                calorieCountingPuzzle.solvePuzzlePartOne(),
                calorieCountingPuzzle.solvePuzzlePartTwo());

        log.info(LOG_MESSAGE, rockPaperScissorsPuzzle.getPuzzleDate().toString(),
                rockPaperScissorsPuzzle.solvePuzzlePartOne(),
                rockPaperScissorsPuzzle.solvePuzzlePartTwo());

        log.info(LOG_MESSAGE, rucksackReorganizationPuzzle.getPuzzleDate().toString(),
                rucksackReorganizationPuzzle.solvePuzzlePartOne(),
                rucksackReorganizationPuzzle.solvePuzzlePartTwo());

    }
}