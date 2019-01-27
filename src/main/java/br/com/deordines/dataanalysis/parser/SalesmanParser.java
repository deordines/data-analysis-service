package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.dto.Salesman;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.StringTokenizer;

public class SalesmanParser extends AParser {

    public static Salesman parse(String line) {
        StringTokenizer tokenizer = splitDefault(line);
        if (tokenizer.countTokens() > 3)
            tokenizer.nextToken();
        String cpf = tokenizer.nextToken();
        String name = tokenizer.nextToken();
        BigDecimal salary = new BigDecimal(tokenizer.nextToken())
                .setScale(2, RoundingMode.CEILING);
        return new Salesman(cpf, name, salary);
    }
}
