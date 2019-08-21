package by.epam.pialetskialiaksei.action;


import by.epam.pialetskialiaksei.entity.Point;
import by.epam.pialetskialiaksei.entity.Tetrahedron;

import java.util.ArrayList;
import java.util.List;

public class TwoPointsTetrahedronFactory implements TetrahedronFactory{
    private static final TwoPointsTetrahedronFactory INSTANCE = new TwoPointsTetrahedronFactory();

    private TwoPointsTetrahedronFactory(){}

    public static TwoPointsTetrahedronFactory getInstance(){
        return INSTANCE;
    }

    /**
     * Factory method that returns <tt>ArrayList</tt> of created Tetrahedrons.
     * @param masOfPoints - mas with strings from file.
     * @return <tt>List</tt> of Tetrahedrons.
     * @throws IllegalArgumentException if tetrahedron or axis is null.
     */
    public List<Tetrahedron> createTetrahedrons(List<String> masOfPoints){
        List<Tetrahedron> result = new ArrayList<>();
        List<Point> listWithPoints = getListOfPoints(masOfPoints);

        Point bufferPoint = null;
        for(Integer i=0; i < listWithPoints.size(); i++){
            if(i%2==0){
                bufferPoint = listWithPoints.get(i);
            }else{
                if(bufferPoint != null) {
                    result.add(new Tetrahedron(bufferPoint, listWithPoints.get(i)));
                }
            }
        }
        return result;
    }

    /**
     * Returns <tt>ArrayList</tt> with points that is created from arrayOfPoints.
     * @param arrayOfPoints - mas with strings from file.
     * @return <tt>ArrayList</tt> with points.
     */
    public List<Point> getListOfPoints(List<String> arrayOfPoints){
        List<Point> result = new ArrayList<>();

        for(String string: arrayOfPoints){
            String[] mas = string.split(" ");
            result.add(new Point(Double.parseDouble(mas[0]),
                    Double.parseDouble(mas[1]),
                    Double.parseDouble(mas[2])));
            result.add(new Point(Double.parseDouble(mas[3]),
                    Double.parseDouble(mas[4]),
                    Double.parseDouble(mas[5])));
        }
        return result;
    }
}
