package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.config.ParserCharacterConfig;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public abstract class AParser<T> implements IParse<T> {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    protected Long idLayout;
    protected List<String> lines;

    protected AParser(List<String> lines) {
        this.lines = lines;
    }

    protected AParser(Long idLayout, List<String> lines) {
        this.idLayout = idLayout;
        this.lines = filteredLinesByLayout(lines);
    }

    protected StringTokenizer split(String line, String character) {
        return new StringTokenizer(format(line, character), LINE_SEPARATOR);
    }

    private String format(String line, String character) {
        List<String> lineSplitted = Arrays.asList(line.split(character));
        StringBuilder stringBuilder = new StringBuilder();
        lineSplitted.forEach(x -> stringBuilder.append(x).append(LINE_SEPARATOR));
        return stringBuilder.toString();
    }

    private List<String> filteredLinesByLayout(List<String> lines) {
        return lines.parallelStream()
                .filter(x -> {
                    StringTokenizer tokenizer = split(x, ParserCharacterConfig.getSTANDARD());
                    return Long.parseLong(tokenizer.nextToken()) == idLayout;
                }).collect(Collectors.toList());
    }
}
