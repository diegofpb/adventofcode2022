package es.diegofpb.adventofcode22.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public static String removeNonAlphanumeric(String string) {
        return string.replaceAll("[^a-zA-Z]", "");

    }

}
