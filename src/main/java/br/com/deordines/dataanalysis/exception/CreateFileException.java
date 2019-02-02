package br.com.deordines.dataanalysis.exception;

public class CreateFileException extends RuntimeException {

    public CreateFileException() {
        super("Error to creating file.");
    }
}
