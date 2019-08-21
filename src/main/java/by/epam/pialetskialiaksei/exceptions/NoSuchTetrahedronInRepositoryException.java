package by.epam.pialetskialiaksei.exceptions;

public class NoSuchTetrahedronInRepositoryException extends Exception {
    public NoSuchTetrahedronInRepositoryException(String s) {
        super(s);
    }

    public NoSuchTetrahedronInRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
