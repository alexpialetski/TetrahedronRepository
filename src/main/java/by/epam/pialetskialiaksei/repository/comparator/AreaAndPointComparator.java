package by.epam.pialetskialiaksei.repository.comparator;

import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Tetrahedron;

import java.util.Map;

public class AreaAndPointComparator extends AreaComparator {

    @Override
    public int compare(Map.Entry<Tetrahedron, Id> o1, Map.Entry<Tetrahedron, Id> o2) {
        int compareValue = super.compare(o1, o2);
        if(compareValue==0){
            Tetrahedron tetrahedron1 = o1.getKey();
            Tetrahedron tetrahedron2 = o2.getKey();
            return tetrahedron1.getCircle().compareTo(tetrahedron2.getCircle());
        }
        return compareValue;
    }
}
