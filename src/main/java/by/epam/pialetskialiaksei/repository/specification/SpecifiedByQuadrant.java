package by.epam.pialetskialiaksei.repository.specification;

import by.epam.pialetskialiaksei.coordinatesystem.quadrants.Quadrant;
import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Tetrahedron;
import by.epam.pialetskialiaksei.repository.specification.api.Specification;

public class SpecifiedByQuadrant implements Specification<Tetrahedron> {
    private final Quadrant quadrant;

    public SpecifiedByQuadrant(Quadrant quadrant) {
        this.quadrant = quadrant;
    }

    @Override
    public boolean isSpecified(Tetrahedron tetrahedron) {
        return quadrant.isInThisQuadrant(tetrahedron);
    }
}
