package by.epam.pialetskialiaksei.repository.comparator;

import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Tetrahedron;

import java.util.Comparator;
import java.util.Map;

public class AreaComparator implements Comparator<Map.Entry<Tetrahedron, Id>>{

    @Override
    public int compare(Map.Entry<Tetrahedron, Id> o1, Map.Entry<Tetrahedron, Id> o2) {
        Tetrahedron tetrahedron1 = o1.getKey();
        Tetrahedron tetrahedron2 = o2.getKey();
        return Double.compare(tetrahedron1.getSquare(), tetrahedron2.getSquare());
    }
}