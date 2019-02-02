package br.com.deordines.dataanalysis.exception;

public class FailReportException extends RuntimeException {

    public FailReportException() {
        super("Error to reporting data.");
    }
}
