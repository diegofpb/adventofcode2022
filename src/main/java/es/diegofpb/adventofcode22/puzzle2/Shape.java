package es.diegofpb.adventofcode22.puzzle2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Shape implements ElementScore {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int score;
}
