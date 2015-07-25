package ru.justnero.minecraft.forge.mprotect;

public class Protect {
    private final String  name;
    private final Cuboid  cuboid;
    private final int     priority;
    private final boolean canBuild;
    private final boolean canUse;
    private final boolean isOwner;
    private final boolean isMember;

    public Protect(String name, Cuboid cuboid, int priority, boolean canBuild, boolean canUse, boolean isOwner, boolean isMember) {
        this.name     = name;
        this.cuboid   = cuboid;
        this.priority = priority;
        this.canBuild = canBuild;
        this.canUse   = canUse;
        this.isOwner  = isOwner;
        this.isMember = isMember;
    }
    
    public boolean contains(Point3D point) {
        return cuboid.contains(point);
    }

    public String getName() {
        return name;
    }

    public Cuboid getCuboid() {
        return cuboid;
    }

    public int getPriority() {
        return priority;
    }

    public boolean canBuild() {
        return canBuild;
    }

    public boolean canUse() {
        return canUse;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public boolean isMember() {
        return isMember;
    }
    
}
