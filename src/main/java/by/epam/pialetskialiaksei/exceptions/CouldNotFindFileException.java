package by.epam.pialetskialiaksei.exceptions;

import java.io.IOException;

public class CouldNotFindFileException extends IOException {
    public CouldNotFindFileException(String s) {
        super(s);
    }
}
