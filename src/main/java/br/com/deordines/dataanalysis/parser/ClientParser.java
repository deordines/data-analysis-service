package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.config.ParserCharacterConfig;
import br.com.deordines.dataanalysis.dto.Client;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ClientParser extends AParser<Client> {

    private static final Long ID_LAYOUT = 2L;

    public ClientParser(List<String> lines) {
        super(ID_LAYOUT, lines);
    }

    @Override
    public List<Client> parse() {
        return lines.parallelStream().map(x -> {
            StringTokenizer tokenizer = split(x, ParserCharacterConfig.getSTANDARD());
            if (tokenizer.countTokens() > 3)
                tokenizer.nextToken();
            String cnpj = tokenizer.nextToken();
            String name = tokenizer.nextToken();
            String businessArea = tokenizer.nextToken();
            return new Client(cnpj, name, businessArea);
        }).collect(Collectors.toList());
    }
}
