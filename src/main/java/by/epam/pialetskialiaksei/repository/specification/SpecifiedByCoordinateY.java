package by.epam.pialetskialiaksei.repository.specification;

import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Tetrahedron;
import by.epam.pialetskialiaksei.repository.specification.api.Specification;

public class SpecifiedByCoordinateY  implements Specification<Tetrahedron> {
    private final double y;
    public SpecifiedByCoordinateY(double y) {
        this.y = y;
    }

    @Override
    public boolean isSpecified(Tetrahedron tetrahedron) {
        if(tetrahedron == null){
            throw new IllegalArgumentException("Error TETRAHEDRON is null");
        }
        if(Double.compare(tetrahedron.getCircle().getY(), y) == 0){
            return true;
        }
        return false;
    }
}
