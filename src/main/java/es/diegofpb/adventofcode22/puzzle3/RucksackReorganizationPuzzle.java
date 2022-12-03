package es.diegofpb.adventofcode22.puzzle3;

import es.diegofpb.adventofcode22.Puzzle;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class RucksackReorganizationPuzzle extends Puzzle {

    private static final int ASCII_VALUE_LOWERCASE = 'a' - 1;
    private static final int ASCII_VALUE_UPPERCASE = 'A' - 27;

    @Override
    public String solvePuzzlePartOne() {
        initialize(getPuzzleDate());
        return String.valueOf(getPriorityOfRuckSack());
    }

    @Override
    public String solvePuzzlePartTwo() {
        return null;
    }

    @Override
    public LocalDate getPuzzleDate() {
        return LocalDate.of(2022, 12, 3);
    }

    private int getPriorityOfRuckSack() {
        return linesOfFile.stream()
                .mapToInt(line -> {
                    String firstHalfRuckSack = line.substring(0, (line.length() / 2));
                    String secondHalfRuckSack = line.substring((line.length() / 2));
                    return getItemPriority(findDuplicateItemInString(firstHalfRuckSack, secondHalfRuckSack));
                })
                .sum();
    }

    private Character findDuplicateItemInString(String str1, String str2) {
        LinkedHashSet<Character> characterStr1LinkedHashSet = new LinkedHashSet<>();
        LinkedHashSet<Character> characterStr2LinkedHashSet = new LinkedHashSet<>();
        for (int i = 0; i < str1.length(); i++) {
            characterStr1LinkedHashSet.add(str1.charAt(i));
            characterStr2LinkedHashSet.add(str2.charAt(i));
        }
        Character repeatedItem = null;
        for (Character character : characterStr1LinkedHashSet) {
            repeatedItem = (characterStr2LinkedHashSet.contains(character) ? character : repeatedItem);
        }
        return repeatedItem;
    }

    private int getItemPriority(char item) {
        return (Character.isLowerCase(item) ? item - ASCII_VALUE_LOWERCASE : item - ASCII_VALUE_UPPERCASE);
    }
}
