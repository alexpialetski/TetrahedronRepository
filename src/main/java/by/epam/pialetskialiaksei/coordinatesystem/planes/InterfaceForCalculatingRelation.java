package by.epam.pialetskialiaksei.coordinatesystem.planes;


import by.epam.pialetskialiaksei.entity.Tetrahedron;

public interface InterfaceForCalculatingRelation {
    boolean isTouchingTetrahedron(Tetrahedron tetrahedron);
    boolean canBeCalculated(Tetrahedron tetrahedron);
    double getVolumeOfPart(Tetrahedron tetrahedron);
}
