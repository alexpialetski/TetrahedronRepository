package by.epam.pialetskialiaksei.repository.comparator;

import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Tetrahedron;

import java.util.Comparator;
import java.util.Map;

public class IdComparator implements Comparator<Map.Entry<Tetrahedron, Id>>{
    private boolean reverseOrder;

    public IdComparator(boolean reverseOrder){
        this.reverseOrder = reverseOrder;
    }

    @Override
    public int compare(Map.Entry<Tetrahedron, Id> o1, Map.Entry<Tetrahedron, Id> o2) {
        if(reverseOrder){
            return -Id.compare(o1.getValue(), o2.getValue());
        }else{
            return Id.compare(o1.getValue(), o2.getValue());
        }
    }
}