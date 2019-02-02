package br.com.deordines.dataanalysis.exception;

public class ReadFileException extends RuntimeException {

    public ReadFileException() {
        super("Error to reading file.");
    }
}
