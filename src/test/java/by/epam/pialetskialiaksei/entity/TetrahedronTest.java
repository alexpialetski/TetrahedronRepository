package by.epam.pialetskialiaksei.entity;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TetrahedronTest {

    private Tetrahedron tetrahedron;

    @BeforeMethod
    public void setUp() {
        tetrahedron = new Tetrahedron(new Point(0,0,1), new Point(0,0,0));
    }

    @Test
    public void testChangeHeightOfTetrahedron() {
        tetrahedron.changeHeightOfTetrahedron(2);

        Point expected = new Point(0,0,2);

        Point actual = tetrahedron.getTop();

        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void testChangeHeightOfTetrahedronUpsidedown() {
        Tetrahedron tetrahedron1 = new Tetrahedron(new Point(0,0,0), new Point(0,0,1));
        tetrahedron1.changeHeightOfTetrahedron(2);

        Point expected = new Point(0,0,-1);

        Point actual = tetrahedron1.getTop();

        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void testChangeLengthOfEdge() {
        Tetrahedron tetrahedron1 = new Tetrahedron(new Point(0,0,2), new Point(0,0,0));
        tetrahedron1.changeLengthOfEdge(1.22475);

        double expected = 1;

        double actual = tetrahedron1.getHeight();

        Assert.assertEquals(actual, expected, 0.0001);
    }

    @Test
    public void testChangeTopPoint() {
        tetrahedron.changeTopPoint(new Point(0,0,2));

        Point expected = new Point(0,0,2);

        Point actual = tetrahedron.getTop();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testChangeCirclePoint() {
        tetrahedron.changeCirclePoint(new Point(0,0,-1));

        Point expected = new Point(0,0,-1);

        Point actual = tetrahedron.getCircle();

        Assert.assertEquals(actual, expected);
    }
}