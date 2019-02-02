package br.com.deordines.dataanalysis.exception;

public class InvalidFileException extends RuntimeException {

    public InvalidFileException() {
        super("Invalid file.");
    }
}
