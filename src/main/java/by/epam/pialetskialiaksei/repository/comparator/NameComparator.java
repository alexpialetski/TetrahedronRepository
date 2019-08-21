package by.epam.pialetskialiaksei.repository.comparator;

import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Tetrahedron;

import java.util.Comparator;
import java.util.Map;

public class NameComparator implements Comparator<Map.Entry<Tetrahedron, Id>> {
    @Override
    public int compare(Map.Entry<Tetrahedron, Id> o1, Map.Entry<Tetrahedron, Id> o2) {
        String name1 = o1.getKey().toString();
        String name2 = o2.getKey().toString();
        int i = name1.compareTo(name2);
        if(i<0){
            return -1;
        }else if(i>0){
            return 1;
        }else{
            return  0;
        }
    }
}
