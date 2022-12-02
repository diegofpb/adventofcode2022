package es.diegofpb.adventofcode22.puzzle2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoundScore implements ElementScore {
    LOST(0),
    DRAW(3),
    WON(6);

    private final int score;
}
