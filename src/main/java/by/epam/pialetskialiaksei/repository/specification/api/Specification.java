package by.epam.pialetskialiaksei.repository.specification.api;

import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Tetrahedron;

public interface Specification<T>{
    public boolean isSpecified(T tetrahedron);
}
