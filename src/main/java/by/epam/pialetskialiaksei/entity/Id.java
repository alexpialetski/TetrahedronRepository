package by.epam.pialetskialiaksei.entity;


public class Id {
    private int id;
    public Id(int id){
        this.id = id;
    }

    public static int compare(Id o1, Id o2) {
        if(o1.id>o2.id){
            return 1;
        }else if(o1.id == o2.id){
            return 0;
        }else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Id id1 = (Id) o;
        return id == id1.id;
    }

    @Override
    public int hashCode() {
        return 31*id;
    }

}
