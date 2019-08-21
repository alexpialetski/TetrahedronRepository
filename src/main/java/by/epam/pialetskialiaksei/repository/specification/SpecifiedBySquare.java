package by.epam.pialetskialiaksei.repository.specification;

import by.epam.pialetskialiaksei.entity.Tetrahedron;
import by.epam.pialetskialiaksei.repository.specification.api.Specification;

public class SpecifiedBySquare implements Specification<Tetrahedron> {
    private double leftBorder;
    private double rightBorder;

    public SpecifiedBySquare(double leftBorder, double rightBorder) {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    @Override
    public boolean isSpecified(Tetrahedron tetrahedron) {
        double square = tetrahedron.getSquare();
        if(square<=rightBorder&&square>=leftBorder){
            return true;
        }
        return false;
    }
}
