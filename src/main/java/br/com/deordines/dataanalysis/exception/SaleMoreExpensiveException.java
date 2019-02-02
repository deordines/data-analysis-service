package br.com.deordines.dataanalysis.exception;

public class SaleMoreExpensiveException extends RuntimeException {

    public SaleMoreExpensiveException() {
        super("Error to getting sale more expensive.");
    }
}
