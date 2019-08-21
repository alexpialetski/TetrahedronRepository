package by.epam.pialetskialiaksei.repository.specification;

import by.epam.pialetskialiaksei.entity.Tetrahedron;
import by.epam.pialetskialiaksei.repository.specification.api.Specification;

public class SpecifiedByCoordinateX implements Specification<Tetrahedron> {
    private final double x;
    public SpecifiedByCoordinateX(double x) {
        this.x = x;
    }

    @Override
    public boolean isSpecified(Tetrahedron tetrahedron) {
        if(tetrahedron == null){
            throw new IllegalArgumentException("Error TETRAHEDRON is null");
        }
        if(Double.compare(tetrahedron.getCircle().getX(), x) == 0){
            return true;
        }
        return false;
    }
}
