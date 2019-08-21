package by.epam.pialetskialiaksei.repository;

import by.epam.pialetskialiaksei.action.TwoPointsTetrahedronFactory;
import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Point;
import by.epam.pialetskialiaksei.entity.Tetrahedron;
import by.epam.pialetskialiaksei.exceptions.NoSuchIdInRepositoryException;
import by.epam.pialetskialiaksei.exceptions.NoSuchTetrahedronInRepositoryException;
import by.epam.pialetskialiaksei.scanner.FileEnter;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TetrahedronRepositoryTest {
    private static final Tetrahedron TETRAHEDRON1 = new Tetrahedron(new Point(1.0 ,1.0, 1.0), new Point(1.0, 1.0, 2.0));
    private static final Tetrahedron TETRAHEDRON2 = new Tetrahedron(new Point(2.0, 2.0, 2.0), new Point(2.0, 2.0, 5.0));

    @BeforeMethod
    public void beforeMethod() throws IOException {
        TetrahedronRepository.getInstance().
                addListToRepository(TwoPointsTetrahedronFactory.getInstance().
                        createTetrahedrons(FileEnter.getInstance().
                                read(".\\src\\main\\resources\\coordinates.txt")));
    }

    @AfterMethod
    public void tearDown() {
        TetrahedronRepository.getInstance().clearRepository();
    }

    @Test
    public void testAddListToRepository() throws IllegalAccessException {
        Map<Tetrahedron, Id> actual = getRepository();

        Map<Tetrahedron, Id> expected = new HashMap<>();
        expected.put(TETRAHEDRON1, new Id(0));
        expected.put(TETRAHEDRON2, new Id(1));

        boolean is = expected.equals(actual);
        Assert.assertTrue(is);
    }

    @Test
    public void testDeleteFromRepositoryById() throws IllegalAccessException, NoSuchIdInRepositoryException {
        TetrahedronRepository.getInstance().deleteFromRepositoryById(new Id(0));

        Map<Tetrahedron, Id> actual = getRepository();

        Map<Tetrahedron, Id> expected = new HashMap<>();
        expected.put(TETRAHEDRON2, new Id(1));

        Assert.assertTrue(expected.equals(actual));
    }

    @Test(expectedExceptions = NoSuchIdInRepositoryException.class)
    public void testDeleteFromRepositoryByIdError() throws NoSuchIdInRepositoryException {
        TetrahedronRepository.getInstance().deleteFromRepositoryById(new Id(10));
    }

    @Test(expectedExceptions = NoSuchTetrahedronInRepositoryException.class)
    public void testDeleteFromRepositoryByValueError()  throws IOException, NoSuchTetrahedronInRepositoryException {
        Tetrahedron tetrahedron1 = new Tetrahedron(new Point(1.0 ,1.0, 1.0), new Point(1.0, 1.0, 4.0));
        TetrahedronRepository.getInstance().deleteFromRepositoryByTetrahedron(tetrahedron1);
    }

    @Test
    public void testAddToRepository() throws IllegalAccessException{
        TetrahedronRepository.getInstance().addToRepository(TETRAHEDRON1);

        Map<Tetrahedron, Id> actual = getRepository();

        Map<Tetrahedron, Id> expected = new HashMap<>();
        expected.put(TETRAHEDRON1, new Id(0));
        expected.put(TETRAHEDRON2, new Id(1));
        expected.put(TETRAHEDRON1, new Id(2));

        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void testDeleteFromRepositoryByTetrahedron() throws NoSuchTetrahedronInRepositoryException, IllegalAccessException {
        Tetrahedron tetrahedron2 = new Tetrahedron(new Point(2.0 ,2.0, 2.0), new Point(2.0, 2.0, 3.0));

        Map<Tetrahedron, Id> expected = getRepository();

        TetrahedronRepository.getInstance().addToRepository(tetrahedron2);
        TetrahedronRepository.getInstance().deleteFromRepositoryByTetrahedron(tetrahedron2);

        Map<Tetrahedron, Id> actual = getRepository();

        Assert.assertTrue(linkedEquals( actual, expected));

    }

    private Map<Tetrahedron, Id> getRepository() throws IllegalAccessException {
        Map<Tetrahedron, Id> result = new LinkedHashMap<>();
        Object someObject = TetrahedronRepository.getInstance();
        for (Field field : someObject.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            Object value = field.get(someObject);
            if(value instanceof Map){
                result = (LinkedHashMap<Tetrahedron, Id>)value;
            }
        }
        return result;
    }

    private <K, V> boolean linkedEquals( Map<K, V> left, Map<K, V> right) {
        Iterator<Map.Entry<K, V>> leftItr = left.entrySet().iterator();
        Iterator<Map.Entry<K, V>> rightItr = right.entrySet().iterator();

        while ( leftItr.hasNext() && rightItr.hasNext()) {
            Map.Entry<K, V> leftEntry = leftItr.next();
            Map.Entry<K, V> rightEntry = rightItr.next();

            //AbstractList does null checks here but for maps we can assume you never get null entries
            if (! leftEntry.equals(rightEntry))
                return false;
        }
        return !(leftItr.hasNext() || rightItr.hasNext());
    }
}