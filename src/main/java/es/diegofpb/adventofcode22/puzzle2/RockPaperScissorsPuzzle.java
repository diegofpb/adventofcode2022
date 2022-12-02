package es.diegofpb.adventofcode22.puzzle2;

import es.diegofpb.adventofcode22.Puzzle;
import es.diegofpb.adventofcode22.utils.StringUtils;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static es.diegofpb.adventofcode22.puzzle2.RoundScore.*;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class RockPaperScissorsPuzzle extends Puzzle {

    private static final Map<Character, Shape> characterShapeMap = new HashMap<>();
    private static final Map<Character, RoundScore> characterRoundScoreMap = new HashMap<>();
    private static final Map<Shape, Shape> mapShapeWinShape = new HashMap<>();

    @Override
    public String solvePuzzlePartOne() {
        Puzzle.initialize(getPuzzleDate());
        initializeCharacterShapeMap();
        return getTotalScoreFromStrategyGuide();
    }

    @Override
    public String solvePuzzlePartTwo() {
        initializeCharacterRoundScoreMap();
        initializeMapShapeWinShape();
        return getTotalScoreFromNewStrategyGuide();
    }

    @Override
    public LocalDate getPuzzleDate() {
        return LocalDate.of(2022, 12, 2);
    }

    private String getTotalScoreFromStrategyGuide() {
        AtomicInteger totalScore = new AtomicInteger();
        linesOfFile.forEach(line -> {
            String lineWithoutSpaces = StringUtils.removeNonAlphanumeric(line);
            if (!lineWithoutSpaces.isBlank() && lineWithoutSpaces.length() == 2) {
                Shape opponentShape = characterShapeMap.get(lineWithoutSpaces.substring(0, 1).charAt(0));
                Shape myShape = characterShapeMap.get(lineWithoutSpaces.substring(1, 2).charAt(0));
                totalScore.addAndGet(getRoundScore(opponentShape, myShape));
            }
        });

        return String.valueOf(totalScore.get());

    }

    private String getTotalScoreFromNewStrategyGuide() {
        AtomicInteger totalScore = new AtomicInteger();
        linesOfFile.forEach(line -> {
            String lineWithoutSpaces = StringUtils.removeNonAlphanumeric(line);
            if (!lineWithoutSpaces.isBlank() && lineWithoutSpaces.length() == 2) {
                Shape opponentShape = characterShapeMap.get(lineWithoutSpaces.substring(0, 1).charAt(0));
                RoundScore roundScore = characterRoundScoreMap.get(lineWithoutSpaces.substring(1, 2).charAt(0));
                totalScore.addAndGet(getRoundScoreWithSecondGuide(opponentShape, roundScore));
            }
        });
        return String.valueOf(totalScore.get());
    }

    private int getRoundScoreWithSecondGuide(Shape opponentShape, RoundScore roundEndNeededScore) {
        int roundScore = roundEndNeededScore.getScore();
        if (roundEndNeededScore.equals(DRAW)) {
            roundScore += opponentShape.getScore();
        } else if (roundEndNeededScore.equals(WON)) {
            Optional<Shape> first = mapShapeWinShape.entrySet().stream()
                    .filter(entry -> Objects.equals(entry.getValue(), opponentShape))
                    .map(Map.Entry::getKey)
                    .findFirst();
            roundScore += first.map(Shape::getScore).orElse(0);
        } else if (roundEndNeededScore.equals(LOST)) {
            roundScore += mapShapeWinShape.get(opponentShape).getScore();
        }

        return roundScore;
    }

    private int getRoundScore(Shape opponentShape, Shape myShape) {
        int myShapeScore = myShape.getScore();
        RoundScore roundScore = null;
        if (opponentShape.equals(myShape)) {
            return DRAW.getScore() + myShapeScore;
        }
        switch (opponentShape) {
            case ROCK -> roundScore = myShape.equals(Shape.PAPER) ? WON : LOST;
            case PAPER -> roundScore = myShape.equals(Shape.SCISSORS) ? WON : LOST;
            case SCISSORS -> roundScore = myShape.equals(Shape.ROCK) ? WON : LOST;
        }
        return ((nonNull(roundScore)) ? roundScore.getScore() : 0) + myShapeScore;
    }

    private void initializeCharacterShapeMap() {
        characterShapeMap.put('A', Shape.ROCK);
        characterShapeMap.put('X', Shape.ROCK);

        characterShapeMap.put('B', Shape.PAPER);
        characterShapeMap.put('Y', Shape.PAPER);

        characterShapeMap.put('C', Shape.SCISSORS);
        characterShapeMap.put('Z', Shape.SCISSORS);
    }

    private void initializeCharacterRoundScoreMap() {
        characterRoundScoreMap.put('X', LOST);
        characterRoundScoreMap.put('Y', DRAW);
        characterRoundScoreMap.put('Z', WON);
    }

    private void initializeMapShapeWinShape() {
        mapShapeWinShape.put(Shape.ROCK, Shape.SCISSORS);
        mapShapeWinShape.put(Shape.SCISSORS, Shape.PAPER);
        mapShapeWinShape.put(Shape.PAPER, Shape.ROCK);
    }
}
