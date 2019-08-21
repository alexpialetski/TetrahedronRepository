package by.epam.pialetskialiaksei.entity;

import by.epam.pialetskialiaksei.action.NameGenerator;
import by.epam.pialetskialiaksei.observer.ObservableTetrahedron;
import by.epam.pialetskialiaksei.observer.Warehouse;
import by.epam.pialetskialiaksei.observer.WarehouseObserver;

/**
 * Fields lengthOfEdge and height is calculated inside of
 * class so to save resources processing time in the future.
 * Field upsideDown is used to calculate relation of two figures
 * thar are cut by XY plane.
 */
public class Tetrahedron implements ObservableTetrahedron {
    private Point top ;
    private Point circle;
    private boolean upsideDown;
    private final String name;

    private WarehouseObserver warehouse;

    public Tetrahedron(Point top, Point circle) {
        this.top = top;
        this.circle = circle;
        this.upsideDown = top.getZ() < circle.getZ();
        warehouse = new Warehouse();
        updateObserver();
        name = NameGenerator.getInstance().getName();
    }

    public Tetrahedron(Point top, Point circle,String name) {
        this.top = top;
        this.circle = circle;
        this.upsideDown = top.getZ() < circle.getZ();
        warehouse = new Warehouse();
        updateObserver();
        this.name = name;
    }

    private WarehouseObserver getWarehouse(){
        if(warehouse == null){
            warehouse = new Warehouse(this);
        }
        return warehouse;
    }

    public Point getCircle() {
        return circle;
    }

    public Point getTop() {
        return top;
    }

    public double getLengthOfEdge(){
        return getWarehouse().getEdge();
    }

    public double getHeight(){
        return getWarehouse().getHeight();
    }

    public double getSquare() {
        return getWarehouse().getArea();
    }

    public double getVolume(){
        return getWarehouse().getVolume();
    }

    private void setCircle(Point point) {
        circle = point;
        updateObserver();
    }

    private void setTop(Point point) {
        top = point;
        updateObserver();
    }

    public void changeHeightOfTetrahedron(double height){
        Point point = new Point(getCircle());
        if(!isUpsideDown()) {
            point.setZ(point.getZ() + height);
            setTop(point);
        }else{
            point.setZ(point.getZ() - height);
            setTop(point);
        }
        updateObserver();
    }

    public void changeLengthOfEdge(double length){
        changeHeightOfTetrahedron((length*Math.sqrt(6))/3);
    }

    public void changeTopPoint(Point point){
        setTop(point);
        this.upsideDown = point.getZ() < circle.getZ();
    }

    public void changeCirclePoint(Point point){
        setCircle(point);
        this.upsideDown = top.getZ() < point.getZ();
    }

    public boolean isUpsideDown() {
        return upsideDown;
    }

    public void updateObserver(){
        if(warehouse == null){
            warehouse = new Warehouse(this);
            return;
        }
        warehouse.update(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tetrahedron that = (Tetrahedron) o;
        return  top.equals(that.getTop()) &&
                circle.equals(that.getCircle());
    }

    @Override
    public int hashCode() {
        return Double.hashCode(31*top.getX() + 31*circle.getX() + top.getY() + circle.getY());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + name;
    }
}
