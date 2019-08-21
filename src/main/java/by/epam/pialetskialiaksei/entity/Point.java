package by.epam.pialetskialiaksei.entity;


public class Point {
    private double x=0;
    private double y=0;
    private double z=0;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(Point point) {
        x = point.getX();
        y = point.getY();
        z = point.getZ();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0 &&
                Double.compare(point.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(31*x + y + z);
    }

    public int compareTo(Point circle) {
        if(this.equals(circle)){
            return 0;
        }else{
            int value = Double.compare(this.getX(), circle.getX());
            if(value != 0){
                return value;
            }else{
                value = Double.compare(this.getY(), circle.getY());
                if(value!=0){
                    return value;
                }else{
                    value = Double.compare(this.getZ(), circle.getZ());
                    if(value !=0){
                        return value;
                    }else{
                        return 0;
                    }
                }
            }
        }
    }
}
