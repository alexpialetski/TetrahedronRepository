package by.epam.pialetskialiaksei.repository.api;

import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Tetrahedron;
import by.epam.pialetskialiaksei.exceptions.NoSuchIdInRepositoryException;
import by.epam.pialetskialiaksei.exceptions.NoSuchTetrahedronInRepositoryException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface Repository<T, V>{
    void addToRepository(T tetrahedron);
    void deleteFromRepositoryByTetrahedron(T tetrahedron) throws NoSuchTetrahedronInRepositoryException;
    void deleteFromRepositoryById(V id) throws NoSuchIdInRepositoryException;
//    void deleteFromRepositoryBySpecification(Specification specification) throws NoSuchIdInRepositoryException;
    void clearRepository();
    List<Map.Entry<Tetrahedron, Id>> sort(Comparator<Map.Entry<Tetrahedron, Id>> comparator);
}
