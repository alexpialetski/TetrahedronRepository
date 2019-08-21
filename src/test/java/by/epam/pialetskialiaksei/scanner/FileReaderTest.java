package by.epam.pialetskialiaksei.scanner;

import by.epam.pialetskialiaksei.exceptions.CouldNotFindFileException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FileReaderTest {

    @Test
    public void testRead() throws IOException {
        List<String> expected = new ArrayList<>();
        Collections.addAll(expected, "1.0 1.0 1.0 1.0 1.0 2.0",
                "2.0 2.0 2.0 2.0 2.0 5.0");

        List<String> actual = FileEnter.getInstance().read(".\\src\\main\\resources\\coordinates.txt");

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CouldNotFindFileException.class)
    public void nullSuppliedRead() throws IOException {
        FileEnter.getInstance().read(".\\src\\main\\resources\\cooordinates.txt");
    }
}