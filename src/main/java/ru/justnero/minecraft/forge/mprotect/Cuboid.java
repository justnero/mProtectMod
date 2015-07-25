package ru.justnero.minecraft.forge.mprotect;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Cuboid {
    
    private final Point3D point1;
    private final Point3D point2;
    
    public Cuboid(int x1, int y1, int z1, int x2, int y2, int z2) {
        point1 = new Point3D(min(x1,x2),min(y1,y2),min(z1,z2));
        point2 = new Point3D(max(x1,x2),max(y1,y2),max(z1,z2));
    }
    
    public Point3D getPoint(int i) {
        return i == 1 ? point1 : point2;
    }
    
    public boolean contains(Point3D point) {
        return  (point.x() >= point1.x() && point.x() <= point2.x()) &&
                (point.y() >= point1.y() && point.y() <= point2.y()) &&
                (point.z() >= point1.z() && point.z() <= point2.z());
    }
    
}
