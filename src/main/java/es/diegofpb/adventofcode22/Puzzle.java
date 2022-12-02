package es.diegofpb.adventofcode22;

import es.diegofpb.adventofcode22.utils.FileReaderUtils;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;


@Getter
public abstract class Puzzle {

    protected static List<String> linesOfFile;

    public static void initialize(LocalDate localDate) {
        linesOfFile = FileReaderUtils.readInputFile(localDate.toString());
    }

    public abstract String solvePuzzlePartOne();

    public abstract String solvePuzzlePartTwo();

    public abstract LocalDate getPuzzleDate();
}
