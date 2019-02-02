package br.com.deordines.dataanalysis.exception;

public class FailMonitorException extends RuntimeException {

    public FailMonitorException() {
        super("Error to monitoring folder.");
    }
}
