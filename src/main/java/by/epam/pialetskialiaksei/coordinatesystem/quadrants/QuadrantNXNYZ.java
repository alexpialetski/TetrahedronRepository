package by.epam.pialetskialiaksei.coordinatesystem.quadrants;

import by.epam.pialetskialiaksei.entity.Point;
import by.epam.pialetskialiaksei.entity.Tetrahedron;

public class QuadrantNXNYZ  implements Quadrant {
    @Override
    public boolean isInThisQuadrant(Tetrahedron tetrahedron) {
        Point circle = tetrahedron.getCircle();
        if (circle.getX() < 0 && circle.getY() < 0 && circle.getZ() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
