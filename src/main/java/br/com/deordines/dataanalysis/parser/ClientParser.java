package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.dto.Client;

import java.util.StringTokenizer;

public class ClientParser extends AParser {

    public static Client parse(String line) {
        StringTokenizer tokenizer = splitDefault(line);
        if (tokenizer.countTokens() > 3)
            tokenizer.nextToken();
        String cnpj = tokenizer.nextToken();
        String name = tokenizer.nextToken();
        String businessArea = tokenizer.nextToken();
        return new Client(cnpj, name, businessArea);
    }
}
