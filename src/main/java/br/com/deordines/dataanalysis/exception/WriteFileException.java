package br.com.deordines.dataanalysis.exception;

public class WriteFileException extends RuntimeException {

    public WriteFileException() {
        super("Error to writing file.");
    }
}
