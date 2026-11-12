package net.midget807.disenchanted.util;

import net.midget807.disenchanted.DisenchantedMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisenchantedStringUtil {
    /** Converts the path of an identifier to capitalized words. <br>
     * <br>
     * Example:
     * <blockquote><pre>
     *     "disenchanted:overworld_tablet" -> "Overworld Tablet"
     * </pre></blockquote>
     * <br>
     * Note: This method takes the entire path.
     * */
    public static String convertPathNameToName(String pathName) {
        List<String> parts = Arrays.asList(pathName.split("_"));
        List<String> upperCaseParts = new ArrayList<>();
        parts.forEach(part -> upperCaseParts.add(part.substring(0, 1).toUpperCase() + part.substring(1)));
        return String.join(" ", upperCaseParts);
    }
}
