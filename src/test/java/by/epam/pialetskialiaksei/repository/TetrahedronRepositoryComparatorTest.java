package by.epam.pialetskialiaksei.repository;

import by.epam.pialetskialiaksei.action.TwoPointsTetrahedronFactory;
import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Point;
import by.epam.pialetskialiaksei.entity.Tetrahedron;
import by.epam.pialetskialiaksei.repository.comparator.*;
import by.epam.pialetskialiaksei.scanner.FileEnter;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class TetrahedronRepositoryComparatorTest {
    private static final Tetrahedron TETRAHEDRON1 = new Tetrahedron(new Point(1.0 ,1.0, 1.0), new Point(1.0, 1.0, 2.0));
    private static final Tetrahedron TETRAHEDRON2 = new Tetrahedron(new Point(2.0, 2.0, 2.0), new Point(2.0, 2.0, 5.0));
    private static final Tetrahedron TETRAHEDRON3 = new Tetrahedron(new Point(2.0, 2.0, 6.0), new Point(2.0, 2.0, 5.0));
    private static final Tetrahedron TETRAHEDRON4 = new Tetrahedron(new Point(2.0, 2.0, 12.1 ), new Point(2.0, 2.0, 5.0));
    private static final Tetrahedron TETRAHEDRON5 = new Tetrahedron(new Point(2.0, 2.0, 12.0 ), new Point(2.0, 2.0, 6.0));
    private static final Tetrahedron TETRAHEDRON6 = new Tetrahedron(new Point(2.0, 2.0,14), new Point(2.0, 2.0 ,10));
    private static final Tetrahedron TETRAHEDRON7 = new Tetrahedron(new Point(2.0, 2, 9.4 ), new Point(2.0, 2.0, 1));

    @BeforeClass
    public void beforeClass() throws IOException {
        TetrahedronRepository.getInstance().
                addListToRepository(TwoPointsTetrahedronFactory.getInstance().
                        createTetrahedrons(FileEnter.getInstance().
                                read(".\\src\\main\\resources\\fileForSorting.txt")));
    }

    @AfterClass
    public void afterClass(){
        TetrahedronRepository.getInstance().clearRepository();
    }

    @Test
    public void testSortByArea() throws IllegalAccessException, IOException {
        List<Map.Entry<Tetrahedron, Id>> actual = TetrahedronRepository.getInstance().sort(new AreaAndPointComparator());

        List<Map.Entry<Tetrahedron, Id>> expected = new ArrayList<>();
        expected.add(new MyEntry<>(TETRAHEDRON1, new Id(0)));
        expected.add(new MyEntry<>(TETRAHEDRON3, new Id(2)));
        expected.add(new MyEntry<>(TETRAHEDRON2, new Id(1)));
        expected.add(new MyEntry<>(TETRAHEDRON6, new Id(5)));
        expected.add(new MyEntry<>(TETRAHEDRON5, new Id(4)));
        expected.add(new MyEntry<>(TETRAHEDRON4, new Id(3)));
        expected.add(new MyEntry<>(TETRAHEDRON7, new Id(6)));

        Assert.assertTrue(listWithEntryEquals(actual,expected));
    }

    @Test
    public void testSortByVolume() throws IllegalAccessException, IOException {
        List<Map.Entry<Tetrahedron, Id>> actual = TetrahedronRepository.getInstance().sort(new VolumeAndPointComparator());

        List<Map.Entry<Tetrahedron, Id>> expected = new ArrayList<>();
        expected.add(new MyEntry<>(TETRAHEDRON1, new Id(0)));
        expected.add(new MyEntry<>(TETRAHEDRON3, new Id(2)));
        expected.add(new MyEntry<>(TETRAHEDRON2, new Id(1)));
        expected.add(new MyEntry<>(TETRAHEDRON6, new Id(5)));
        expected.add(new MyEntry<>(TETRAHEDRON5, new Id(4)));
        expected.add(new MyEntry<>(TETRAHEDRON4, new Id(3)));
        expected.add(new MyEntry<>(TETRAHEDRON7, new Id(6)));

        Assert.assertTrue(listWithEntryEquals(actual,expected));
    }

    @Test
    public void testSortByIdReverse() throws IllegalAccessException, IOException {
        List<Map.Entry<Tetrahedron, Id>> actual = TetrahedronRepository.getInstance().sort(new IdComparator(true));

        List<Map.Entry<Tetrahedron, Id>> expected = new ArrayList<>();
        expected.add(new MyEntry<>(TETRAHEDRON7, new Id(6)));
        expected.add(new MyEntry<>(TETRAHEDRON6, new Id(5)));
        expected.add(new MyEntry<>(TETRAHEDRON5, new Id(4)));
        expected.add(new MyEntry<>(TETRAHEDRON4, new Id(3)));
        expected.add(new MyEntry<>(TETRAHEDRON3, new Id(2)));
        expected.add(new MyEntry<>(TETRAHEDRON2, new Id(1)));
        expected.add(new MyEntry<>(TETRAHEDRON1, new Id(0)));


        Assert.assertTrue(listWithEntryEquals(actual,expected));
    }

    @Test
    public void testSortByX() throws IllegalAccessException, IOException {
        List<Map.Entry<Tetrahedron, Id>> actual = TetrahedronRepository.getInstance().sort(new XCoordinateComparator());

        List<Map.Entry<Tetrahedron, Id>> expected = new ArrayList<>();
        expected.add(new MyEntry<>(TETRAHEDRON1, new Id(0)));
        expected.add(new MyEntry<>(TETRAHEDRON2, new Id(1)));
        expected.add(new MyEntry<>(TETRAHEDRON3, new Id(2)));
        expected.add(new MyEntry<>(TETRAHEDRON4, new Id(3)));
        expected.add(new MyEntry<>(TETRAHEDRON5, new Id(4)));
        expected.add(new MyEntry<>(TETRAHEDRON6, new Id(5)));
        expected.add(new MyEntry<>(TETRAHEDRON7, new Id(6)));

        Assert.assertTrue(listWithEntryEquals(actual,expected));
    }

    @Test
    public void testSortByY() throws IllegalAccessException, IOException {
        List<Map.Entry<Tetrahedron, Id>> actual = TetrahedronRepository.getInstance().sort(new YCoordinateComparator());

        List<Map.Entry<Tetrahedron, Id>> expected = new ArrayList<>();
        expected.add(new MyEntry<>(TETRAHEDRON1, new Id(0)));
        expected.add(new MyEntry<>(TETRAHEDRON2, new Id(1)));
        expected.add(new MyEntry<>(TETRAHEDRON3, new Id(2)));
        expected.add(new MyEntry<>(TETRAHEDRON4, new Id(3)));
        expected.add(new MyEntry<>(TETRAHEDRON5, new Id(4)));
        expected.add(new MyEntry<>(TETRAHEDRON6, new Id(5)));
        expected.add(new MyEntry<>(TETRAHEDRON7, new Id(6)));

        Assert.assertTrue(listWithEntryEquals(actual,expected));
//
//        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortByName(){
        List<Map.Entry<Tetrahedron, Id>> repository = new ArrayList<>();
        Tetrahedron tetrahedron1 = new Tetrahedron(new Point(1.0 ,1.0, 1.0), new Point(1.0, 1.0, 2.0), "Alexey");
        Tetrahedron tetrahedron2 = new Tetrahedron(new Point(1.0 ,1.0, 1.0), new Point(1.0, 1.0, 2.0), "Zhak");
        repository.add(new MyEntry<>(tetrahedron2, new Id(0)));
        repository.add(new MyEntry<>(tetrahedron1, new Id(1)));

        List<Map.Entry<Tetrahedron, Id>> expected = new ArrayList<>();
        expected.add(new MyEntry<>(tetrahedron1, new Id(1)));
        expected.add(new MyEntry<>(tetrahedron2, new Id(0)));

        repository.sort(new NameComparator());

        Assert.assertTrue(listWithEntryEquals(repository, expected));
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

    private <K, V> boolean listWithEntryEquals(List<Map.Entry<K, V>> left, List<Map.Entry<K, V>> right) {
        Iterator<Map.Entry<K, V>> leftItr = left.iterator();
        Iterator<Map.Entry<K, V>> rightItr = right.iterator();

        while ( leftItr.hasNext() && rightItr.hasNext()) {
            Map.Entry<K, V> leftEntry = leftItr.next();
            Map.Entry<K, V> rightEntry = rightItr.next();

            //AbstractList does null checks here but for maps we can assume you never get null entries
            if (!leftEntry.getKey().equals(rightEntry.getKey())&&!leftEntry.getValue().equals(rightEntry.getValue())){
                return false;
            }
        }
        return !(leftItr.hasNext() || rightItr.hasNext());
    }
}

final class MyEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }
}