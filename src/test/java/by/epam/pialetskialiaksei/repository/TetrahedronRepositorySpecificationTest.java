package by.epam.pialetskialiaksei.repository;

import by.epam.pialetskialiaksei.action.TwoPointsTetrahedronFactory;
import by.epam.pialetskialiaksei.coordinatesystem.quadrants.*;
import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Point;
import by.epam.pialetskialiaksei.entity.Tetrahedron;
import by.epam.pialetskialiaksei.exceptions.NoSuchIdInRepositoryException;
import by.epam.pialetskialiaksei.exceptions.NoSuchTetrahedronInRepositoryException;
import by.epam.pialetskialiaksei.repository.specification.*;
import by.epam.pialetskialiaksei.repository.specification.api.Specification;
import by.epam.pialetskialiaksei.scanner.FileEnter;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TetrahedronRepositorySpecificationTest {
    private static final Tetrahedron TETRAHEDRON1 = new Tetrahedron(new Point(1.0 ,1.0, 1.0), new Point(1.0, 1.0, 2.0));
    private static final Tetrahedron TETRAHEDRON2 = new Tetrahedron(new Point(2.0, 2.0, 2.0), new Point(2.0, 2.0, 5.0));
    private boolean update = false;

    @BeforeClass
    public void beforeClass() throws IOException {
//        afterClass();
        TetrahedronRepository.getInstance().
                addListToRepository(TwoPointsTetrahedronFactory.getInstance().
                        createTetrahedrons(FileEnter.getInstance().
                                read(".\\src\\main\\resources\\coordinates.txt")));
    }

    @AfterClass
    public void afterClass(){
        TetrahedronRepository.getInstance().clearRepository();
    }

    @DataProvider
    public Object[][] dataQuadrantProvider() {
        Object[][] mas = {
                {new QuadrantNXNYNZ(), new Tetrahedron(new Point(-1, -2, 0), new Point(-1, -2, -3))},
                {new QuadrantNXNYZ(), new Tetrahedron(new Point(-1, -2, 4), new Point(-1, -2, 3))},
                {new QuadrantNXYNZ(), new Tetrahedron(new Point(-1, 2, 0), new Point(-1, 2, -3))},
                {new QuadrantNXYZ(), new Tetrahedron(new Point(-1, 2, 6), new Point(-1, 2, 3))},
                {new QuadrantXNYNZ(), new Tetrahedron(new Point(1, -2, 0), new Point(1, -2, -3))},
                {new QuadrantXNYZ(), new Tetrahedron(new Point(1, -2, 6), new Point(1, -2, 3))},
                {new QuadrantXYNZ(), new Tetrahedron(new Point(1, 2, 0), new Point(1, 2, -3))},
                {new QuadrantXYZ(), new Tetrahedron(new Point(1, 2, 6), new Point(1, 2, 3))}
        };
        return mas;
    }

    @DataProvider
    public Object[][] dataSpecifiedByCoordinateProvider() {
        List<Tetrahedron> expected1 = new ArrayList<>();
        expected1.add(TETRAHEDRON1);
        List<Tetrahedron> expected2 = new ArrayList<>();
        expected2.add(TETRAHEDRON2);
        Object[][] mas = {
                {expected1, new SpecifiedByCoordinateX(1)},
                {expected2, new SpecifiedByCoordinateY(2)},
                {expected1, new SpecifiedByIndentX(1)}
        };
        return mas;
    }

    @Test(expectedExceptions = {NoSuchIdInRepositoryException.class}, priority = 1)
    public void FindTetrahedronByIdNoTetrahedronException() throws IOException, NoSuchIdInRepositoryException {
        Tetrahedron actual = TetrahedronRepository.getInstance().findTetrahedronById(new Id(10));
    }

    @Test(priority = 1)
    public void testFindTetrahedrons() throws IOException, NoSuchIdInRepositoryException, NoSuchTetrahedronInRepositoryException {
        List<Tetrahedron> expected = new ArrayList<>();
        expected.add(TETRAHEDRON1);
        expected.add(TETRAHEDRON2);

        List<Tetrahedron> actual = TetrahedronRepository.getInstance().findTetrahedronsById(0, 2);

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = {NoSuchTetrahedronInRepositoryException.class}, priority = 1)
    public void testFindTetrahedronsNoTetrahedrons() throws IOException, NoSuchIdInRepositoryException, NoSuchTetrahedronInRepositoryException {
        List<Tetrahedron> actual = TetrahedronRepository.getInstance().findTetrahedronsById(5, 0);
    }

    @Test(expectedExceptions = {NoSuchIdInRepositoryException.class}, priority = 1)
    public void testFindTetrahedronsNoIds() throws IOException, NoSuchIdInRepositoryException, NoSuchTetrahedronInRepositoryException {
        List<Tetrahedron> actual = TetrahedronRepository.getInstance().findTetrahedronsById(5, 6);
    }

    @Test(priority = 1)
    public void testFindTetrahedronById() throws IOException, NoSuchIdInRepositoryException {
        Tetrahedron expected = TETRAHEDRON1;

        Tetrahedron actual = TetrahedronRepository.getInstance().findTetrahedronById(new Id(0));

        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataSpecifiedByCoordinateProvider", priority = 1)
    public void testFindTetrahedronsSpecifiedByCoordinate(List<Tetrahedron> expected, Specification<Tetrahedron> specification){
        List<Tetrahedron> actual = TetrahedronRepository.getInstance().
                find(specification);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataQuadrantProvider", priority = 2)
    public void testFindTetrahedronsInQuadrant(Quadrant quadrant, Tetrahedron tetrahedron) throws IOException {
        TetrahedronRepository.getInstance().
                addListToRepository(TwoPointsTetrahedronFactory.getInstance().
                        createTetrahedrons(FileEnter.getInstance().
                                read(".\\src\\main\\resources\\fileForTestingQuadrant.txt")));

        List<Tetrahedron> expected = new ArrayList<>();
        expected.add(tetrahedron);

        List<Tetrahedron> actual = TetrahedronRepository.getInstance().find(new SpecifiedByQuadrant(quadrant));
        Assert.assertEquals(actual, expected);
        TetrahedronRepository.getInstance().clearRepository();
    }

    @Test(priority = 1)
    public void testFindTetrahedronsBySquare(){
        List<Tetrahedron> expected = new ArrayList<>();
        expected.add(TETRAHEDRON1);

        List<Tetrahedron> actual = TetrahedronRepository.
                getInstance().
                find(new SpecifiedBySquare(2.5, 3));

        Assert.assertEquals(actual, expected);
    }
}