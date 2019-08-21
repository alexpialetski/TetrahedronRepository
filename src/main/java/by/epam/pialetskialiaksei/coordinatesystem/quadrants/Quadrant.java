package by.epam.pialetskialiaksei.coordinatesystem.quadrants;

import by.epam.pialetskialiaksei.entity.Tetrahedron;

public interface Quadrant {
    boolean isInThisQuadrant(Tetrahedron tetrahedron);
}
