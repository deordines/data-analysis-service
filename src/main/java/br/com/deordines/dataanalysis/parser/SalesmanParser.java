package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.config.ParserCharacterConfig;
import br.com.deordines.dataanalysis.dto.Salesman;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class SalesmanParser extends AParser<Salesman> {

    private static final Long ID_LAYOUT = 1L;

    public SalesmanParser(List<String> lines) {
        super(ID_LAYOUT, lines);
    }

    @Override
    public List<Salesman> parse() {
        return lines.parallelStream().map(x -> {
            StringTokenizer tokenizer = split(x, ParserCharacterConfig.getSTANDARD());
            if (tokenizer.countTokens() > 3)
                tokenizer.nextToken();
            String cpf = tokenizer.nextToken();
            String name = tokenizer.nextToken();
            BigDecimal salary = new BigDecimal(tokenizer.nextToken()).setScale(2, RoundingMode.CEILING);
            return new Salesman(cpf, name, salary);
        }).collect(Collectors.toList());
    }
}
