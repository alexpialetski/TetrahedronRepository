package by.epam.pialetskialiaksei.repository.comparator;

import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Point;
import by.epam.pialetskialiaksei.entity.Tetrahedron;

import java.util.Comparator;
import java.util.Map;

public class XCoordinateComparator implements Comparator<Map.Entry<Tetrahedron, Id>> {

    public int compare(Map.Entry<Tetrahedron, Id> o1, Map.Entry<Tetrahedron, Id> o2) {
        Point circleO1 = o1.getKey().getCircle();
        Point circleO2 = o1.getKey().getCircle();
        return Double.compare(circleO1.getX(), circleO2.getX());
    }
}
