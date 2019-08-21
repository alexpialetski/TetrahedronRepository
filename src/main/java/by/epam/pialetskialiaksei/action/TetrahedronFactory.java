package by.epam.pialetskialiaksei.action;

import by.epam.pialetskialiaksei.entity.Tetrahedron;

import java.util.List;

public interface TetrahedronFactory {
    List<Tetrahedron> createTetrahedrons(List<String> masOfPoints);
}
