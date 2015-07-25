package ru.justnero.minecraft.forge.mprotect;

public class Point3D {
    
    private final double pointX;
    private final double pointY;
    private final double pointZ;

    public Point3D() {
        this.pointX = 0;
        this.pointY = 0;
        this.pointZ = 0;
    }

    public Point3D(double pointX, double pointY, double pointZ) {
        this.pointX = pointX;
        this.pointY = pointY;
        this.pointZ = pointZ;
    }

    public double x() {
        return this.pointX;
    }

    public double y() {
        return this.pointY;
    }

    public double z() {
        return this.pointZ;
    }
}