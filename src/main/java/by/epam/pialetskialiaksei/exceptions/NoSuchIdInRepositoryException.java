package by.epam.pialetskialiaksei.exceptions;

public class NoSuchIdInRepositoryException extends Exception {
    public NoSuchIdInRepositoryException(String message) {
        super(message);
    }

    public NoSuchIdInRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
