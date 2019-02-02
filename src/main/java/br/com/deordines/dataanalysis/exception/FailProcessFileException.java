package br.com.deordines.dataanalysis.exception;

public class FailProcessFileException extends RuntimeException {

    public FailProcessFileException() {
        super("Error to PROCESSING file.");
    }
}
