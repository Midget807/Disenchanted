package net.midget807.disenchanted.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisenchantedTextUtil {
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


    public static final int MAX_WRAPPED_TOOLTIP_CHAR_LENGTH = 40;
    /** Wraps a single line of text to {@link DisenchantedTextUtil#MAX_WRAPPED_TOOLTIP_CHAR_LENGTH} characters long.
     * Also includes a way to line break using '\\n' to indicate a line break. <br>
     * Example: <br>
     * <blockquote><pre>
     *     "Dummy text with line breaks. \\n This is awesome! \\n \\n It even allows empty lines!!"
     * </pre></blockquote> <br>
     * Output: <br>
     * <blockquote><pre>
     *     "Dummy text with line breaks."
     *     "This is awesome!"
     *
     *     "It even allows empty lines!!"
     * </pre></blockquote>
     *
     * @see #getWrappedText(List text)
     * */
    public static List<Component> getWrappedText(Component text) {
        List<Component> lines = new ArrayList<>(List.of());
        String fullText = text.getString();
        Style style = text.getStyle();
        String[] paragraphs = fullText.split("\\\\n");
        for (String paragraph : paragraphs) {
            paragraph = paragraph.trim();
            String[] wordsPerParagraph = paragraph.split(" ");
            if (wordsPerParagraph.length == 0) {
                lines.add(Component.literal(" "));
            } else {
                int characterCount = 0;
                String line = "";
                for (String word : wordsPerParagraph) {
                    characterCount += word.length();
                    characterCount++;
                    if (characterCount > MAX_WRAPPED_TOOLTIP_CHAR_LENGTH) {
                        lines.add(Component.literal(line).setStyle(style));
                        line = "";
                        characterCount = 0;
                    }
                    line = line.concat(word).concat(" ");
                }
                if (!line.isEmpty()) {
                    lines.add(Component.literal(line).setStyle(style));
                }
            }
        }
        return lines;
    }

    /** Wraps a list of texts to {@link DisenchantedTextUtil#MAX_WRAPPED_TOOLTIP_CHAR_LENGTH} characters long.
     * Also includes a way to line break using '\\n' to indicate a line break. <br>
     * Example: <br>
     * <blockquote><pre>
     *     List.of("Dummy text with line breaks.", "\\n This is awesome! \\n", "\\n It even allows empty lines!!");
     * </pre></blockquote> <br>
     * Output: <br>
     * <blockquote><pre>
     *     "Dummy text with line breaks."
     *     "This is awesome!"
     *
     *     "It even allows empty lines!!"
     * </pre></blockquote>
     *
     * @see #getWrappedText(Component text)
     * */
    public static List<Component> getWrappedText(List<Component> text) {
        Map<Component, Style> styles = new HashMap<>();
        text.forEach(component -> styles.put(component, component.getStyle()));
        MutableComponent fullText = Component.empty();
        text.forEach(component -> fullText.append(component).setStyle(component.getStyle()));
        return getWrappedText(fullText);
    }
}
