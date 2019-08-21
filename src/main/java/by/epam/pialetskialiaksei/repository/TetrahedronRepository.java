package by.epam.pialetskialiaksei.repository;

import by.epam.pialetskialiaksei.coordinatesystem.quadrants.Quadrant;
import by.epam.pialetskialiaksei.entity.Id;
import by.epam.pialetskialiaksei.entity.Tetrahedron;
import by.epam.pialetskialiaksei.exceptions.NoSuchIdInRepositoryException;
import by.epam.pialetskialiaksei.exceptions.NoSuchTetrahedronInRepositoryException;
import by.epam.pialetskialiaksei.repository.api.Repository;
import by.epam.pialetskialiaksei.repository.specification.api.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class TetrahedronRepository implements Repository<Tetrahedron, Id> {
    private Map<Tetrahedron, Id> repository = new LinkedHashMap<>();
    private static final TetrahedronRepository INSTANCE = new TetrahedronRepository();
    private static final Logger LOGGER = LogManager.getLogger(TetrahedronRepository.class);
    private static int id = 0;
    private TetrahedronRepository(){}

    public static TetrahedronRepository getInstance(){
        return INSTANCE;
    }

    public void addToRepository(Tetrahedron tetrahedron){
        Id idOfTetrahedron = new Id(id++);
        repository.put(tetrahedron, idOfTetrahedron);
    }

    public void addListToRepository(List<Tetrahedron> arrayOfTetrahedrons){
        for(Tetrahedron tetrahedron: arrayOfTetrahedrons){
            addToRepository(tetrahedron);
        }
    }

    public void deleteFromRepositoryByTetrahedron(Tetrahedron tetrahedron) throws NoSuchTetrahedronInRepositoryException {
        if(repository.containsKey(tetrahedron)) {
            repository.remove(tetrahedron);
        }else{
            LOGGER.error("No Such Key In Repository Exception!");
            throw new NoSuchTetrahedronInRepositoryException("No Such Key In Repository Exception!");
        }
    }

    public void deleteFromRepositoryById(Id id) throws NoSuchIdInRepositoryException {
        if(repository.containsValue(id)){
            repository.remove(findTetrahedronById(id));
        }else{
            LOGGER.error("No Such Value In Repository Exception!");
            throw new NoSuchIdInRepositoryException("No Such Value In Repository Exception!");
        }
    }

    public void clearRepository(){
        repository.clear();
        id = 0;
    }

    public Tetrahedron findTetrahedronById(Id id) throws NoSuchIdInRepositoryException {
        for(Map.Entry<Tetrahedron,Id> entry: repository.entrySet()){
            if(entry.getValue().equals(id)){
                return entry.getKey();
            }
        }
        LOGGER.error("No such id!!!");
        throw new NoSuchIdInRepositoryException("No such id!!!");
    }

    public List<Tetrahedron> findTetrahedronsById(int firstId, int quantity) throws NoSuchTetrahedronInRepositoryException, NoSuchIdInRepositoryException {
        List<Tetrahedron> result = new ArrayList<>();
        Id id = new Id(firstId);
        for(int i=0; i<quantity; i++){
                result.add(findTetrahedronById(id));
            id = new Id(++firstId);
        }
        if(!result.isEmpty()){
            return result;
        }else{
            LOGGER.error("No such tetrahedrons!!!");
            throw new NoSuchTetrahedronInRepositoryException("No such tetrahedrons!!!");
        }
    }

    public List<Tetrahedron> find(Specification<Tetrahedron> specification){
        List<Tetrahedron> result = new ArrayList<>();
        for(Map.Entry<Tetrahedron, Id> entry: repository.entrySet()){
            if(specification.isSpecified(entry.getKey())){
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public List<Map.Entry<Tetrahedron, Id>> sort(Comparator<Map.Entry<Tetrahedron,Id>> comparator){
        List<Map.Entry<Tetrahedron, Id>> sortedList = new ArrayList<>(repository.entrySet());
        sortedList.sort(comparator);
        return sortedList;
    }
}

