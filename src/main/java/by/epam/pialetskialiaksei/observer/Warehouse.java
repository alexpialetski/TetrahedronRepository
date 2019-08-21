package by.epam.pialetskialiaksei.observer;

import by.epam.pialetskialiaksei.action.Calculator;
import by.epam.pialetskialiaksei.entity.Tetrahedron;


public class Warehouse implements WarehouseObserver {
    private double area;
    private double height;
    private double edge;
    private double volume;

    public Warehouse(){}

    public Warehouse(Tetrahedron tetrahedron){
        height = Calculator.getInstance().findHeightOfTetrahedron(tetrahedron);
        edge = Calculator.getInstance().findEdgeOfTetrahedron(tetrahedron);
        area = Calculator.getInstance().findAreaOfTetrahedron(tetrahedron);
        volume = Calculator.getInstance().findVolumeOfTetrahedron(tetrahedron);
    }

    public double getArea() {
        return area;
    }

    public double getEdge() {
        return edge;
    }

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        return volume;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setEdge(double edge) {
        this.edge = edge;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public void update(Tetrahedron tetrahedron) {
        height = Calculator.getInstance().findHeightOfTetrahedron(tetrahedron);
        edge = Calculator.getInstance().findEdgeOfTetrahedron(tetrahedron);
        area = Calculator.getInstance().findAreaOfTetrahedron(tetrahedron);
        volume = Calculator.getInstance().findVolumeOfTetrahedron(tetrahedron);
    }
}
