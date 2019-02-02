package br.com.deordines.dataanalysis.exception;

public class MoveFileException extends RuntimeException {

    public MoveFileException() {
        super("Error to moving file.");
    }
}
