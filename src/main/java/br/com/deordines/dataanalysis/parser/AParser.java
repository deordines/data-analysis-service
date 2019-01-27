package br.com.deordines.dataanalysis.parser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class AParser {

    private static String LINE_SEPARATOR = System.getProperty("line.separator");
    private static String DEFAULT_CHARACTER;
    private static String ITEMS_CHARACTER;
    private static String ITEM_CHARACTER;

    protected static StringTokenizer splitDefault(String line) {
        return split(line, DEFAULT_CHARACTER);
    }

    protected static StringTokenizer splitItems(String line) {
        return split(line, ITEMS_CHARACTER);
    }

    protected static StringTokenizer splitItem(String line) {
        return split(line, ITEM_CHARACTER);
    }

    private static StringTokenizer split(String line, String character) {
        return new StringTokenizer(format(line, character), LINE_SEPARATOR);
    }

    private static String format(String line, String character) {
        List<String> lineSplitted = Arrays.asList(line.split(character));
        StringBuilder stringBuilder = new StringBuilder();
        lineSplitted.forEach(x -> stringBuilder.append(x).append(LINE_SEPARATOR));
        return stringBuilder.toString();
    }

    @Value("${parser.character.default}")
    private void setDefaultCharacter(String character) {
        DEFAULT_CHARACTER = character;
    }

    @Value("${parser.character.items}")
    private void setItemsCharacter(String character) {
        ITEMS_CHARACTER = character;
    }

    @Value("${parser.character.item}")
    private void setItemCharacter(String character) {
        ITEM_CHARACTER = character;
    }
}
