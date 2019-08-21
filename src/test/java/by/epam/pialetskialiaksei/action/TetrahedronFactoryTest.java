package by.epam.pialetskialiaksei.action;

import by.epam.pialetskialiaksei.entity.Point;
import by.epam.pialetskialiaksei.entity.Tetrahedron;
import by.epam.pialetskialiaksei.exceptions.CouldNotFindFileException;
import by.epam.pialetskialiaksei.scanner.FileEnter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TetrahedronFactoryTest {

    @Test
    public void testCreateTetrahedron() throws IOException {
        List<String> points = FileEnter.getInstance().read(".\\src\\main\\resources\\coordinates.txt");

        List<Tetrahedron> expected = new ArrayList<>();
        expected.add(new Tetrahedron(new Point(1.0 ,1.0, 1.0), new Point(1.0, 1.0, 2.0)));
        expected.add(new Tetrahedron(new Point(2.0, 2.0, 2.0), new Point(2.0, 2.0, 5.0)));

        List<Tetrahedron> actual = TwoPointsTetrahedronFactory.getInstance()
                .createTetrahedrons(points);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetListOfPoints() throws IOException {
        List<String> points = FileEnter.getInstance().read(".\\src\\main\\resources\\coordinates.txt");

        List<Point> expected = new ArrayList<>();
        expected.add(new Point(1.0 ,1.0, 1.0));
        expected.add(new Point(1.0, 1.0, 2.0));
        expected.add(new Point(2.0, 2.0, 2.0));
        expected.add(new Point(2.0, 2.0, 5.0));

        List<Point> actual = TwoPointsTetrahedronFactory.getInstance()
                .getListOfPoints(points);

        Assert.assertEquals(expected, actual);
    }

    @Test(expectedExceptions = {CouldNotFindFileException.class})
    public void testGetListOfPointsErrors() throws IOException {
        FileEnter.getInstance().read(".\\src\\main\\resources\\cooordinates.txt");
    }
}