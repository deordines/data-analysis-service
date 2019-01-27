package br.com.deordines.dataanalysis.stubs;

import br.com.deordines.dataanalysis.dto.Salesman;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public final class SalesmanStub {

    public static Salesman salesman1() {
        return new Salesman(
                "12345678901",
                "Salesman 1",
                BigDecimal.valueOf(10000).setScale(2, RoundingMode.CEILING));
    }

    public static Salesman salesman2() {
        return new Salesman(
                "12345678902",
                "Salesman 2",
                BigDecimal.valueOf(10000.99).setScale(2, RoundingMode.CEILING));
    }

    public static List<Salesman> salesmans() {
        return Arrays.asList(salesman1(), salesman2());
    }
}
