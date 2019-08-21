package by.epam.pialetskialiaksei.observer;

import by.epam.pialetskialiaksei.entity.Tetrahedron;

public interface WarehouseObserver {

    void update(Tetrahedron tetrahedron);

    void setHeight(double height);

    double getEdge();

    double getHeight();

    double getArea();

    double getVolume();

}
