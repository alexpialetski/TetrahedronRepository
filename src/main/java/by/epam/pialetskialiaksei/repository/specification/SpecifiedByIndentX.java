package by.epam.pialetskialiaksei.repository.specification;

import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Point;
import by.epam.pialetskialiaksei.entity.Tetrahedron;
import by.epam.pialetskialiaksei.repository.specification.api.Specification;

public class SpecifiedByIndentX implements Specification<Tetrahedron> {

    private final double indent;

    public SpecifiedByIndentX(double indent) {
        this.indent = indent;
    }

    @Override
    public boolean isSpecified(Tetrahedron tetrahedron) {
        Point circle = tetrahedron.getCircle();
        if(Double.compare(circle.getX(), indent) == 0){
            return true;
        }
        return false;
    }
}
