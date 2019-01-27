package br.com.deordines.dataanalysis.stubs;

import java.nio.charset.StandardCharsets;

public final class LineStub {

    public static String salesman1() {
        return "001ç12345678901çSalesman 1ç10000";
    }

    public static String salesman2() {
        return "001ç12345678902çSalesman 2ç10000.99";
    }

    public static String client1() {
        return "002ç12345678000101çClient 1çBusiness Area 1";
    }

    public static String client2() {
        return "002ç12345678000102çClient 2çBusiness Area 2";
    }

    public static String sale1() {
        return "003ç1ç[1-10-10.10]çSalesman 1";
    }

    public static String sale2() {
        return "003ç2ç[1-10-10.10,2-20-20.99]çSalesman 2";
    }

    public static String sale3() {
        return "003ç3ç[1-10-10.10,2-20-20.99,3-30-0.30]çSalesman 1";
    }

    public static String item1() {
        return "1-10-10.10";
    }

    public static byte[] fileData() {
        String salesmansLine = String.format("%s%n%s%n", salesman1(), salesman2());
        String clientsLine = String.format("%s%n%s%n", client1(), client2());
        String salesLine = String.format("%s%n%s%n%s%n", sale1(), sale2(), sale3());
        String lines = salesmansLine + clientsLine + salesLine;
        return lines.getBytes(StandardCharsets.ISO_8859_1);
    }
}
