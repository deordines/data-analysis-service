package br.com.deordines.dataanalysis.parser;

import java.util.StringTokenizer;

public class LayoutParser extends AParser {

    public static Long parse(String line) {
        StringTokenizer tokenizer = splitDefault(line);
        return Long.parseLong(tokenizer.nextToken());
    }
}
