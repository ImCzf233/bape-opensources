package mc.bape.utils.math;

import mc.bape.utils.gl.*;

public final class Vec3f
{
    private double x;
    private double y;
    private double z;
    
    public Vec3f() {
        this(0.0, 0.0, 0.0);
    }
    
    public Vec3f(final Vec3f vec) {
        this(vec.x, vec.y, vec.z);
    }
    
    public Vec3f(final double x2, final double y2, final double z2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
    }
    
    public final Vec3f setX(final double x2) {
        this.x = x2;
        return this;
    }
    
    public final Vec3f setY(final double y2) {
        this.y = y2;
        return this;
    }
    
    public final Vec3f setZ(final double z2) {
        this.z = z2;
        return this;
    }
    
    public final double getX() {
        return this.x;
    }
    
    public final double getY() {
        return this.y;
    }
    
    public final double getZ() {
        return this.z;
    }
    
    public final Vec3f add(final Vec3f vec) {
        return this.add(vec.x, vec.y, vec.z);
    }
    
    public final Vec3f add(final double x2, final double y2, final double z2) {
        return new Vec3f(this.x + x2, this.y + y2, this.z + z2);
    }
    
    public final Vec3f sub(final Vec3f vec) {
        return new Vec3f(this.x - vec.x, this.y - vec.y, this.z - vec.z);
    }
    
    public final Vec3f sub(final double x2, final double y2, final double z2) {
        return new Vec3f(this.x - x2, this.y - y2, this.z - z2);
    }
    
    public final Vec3f scale(final float scale) {
        return new Vec3f(this.x * scale, this.y * scale, this.z * scale);
    }
    
    public final Vec3f copy() {
        return new Vec3f(this);
    }
    
    public final Vec3f transfer(final Vec3f vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
        return this;
    }
    
    public final double distanceTo(final Vec3f vec) {
        final double dx2 = this.x - vec.x;
        final double dy2 = this.y - vec.y;
        final double dz2 = this.z - vec.z;
        return Math.sqrt(dx2 * dx2 + dy2 * dy2 + dz2 * dz2);
    }
    
    public final Vec2f rotationsTo(final Vec3f vec) {
        final double[] diff = { vec.x - this.x, vec.y - this.y, vec.z - this.z };
        final double hDist = Math.sqrt(diff[0] * diff[0] + diff[2] * diff[2]);
        return new Vec2f(Math.toDegrees(Math.atan2(diff[2], diff[0])) - 90.0, -Math.toDegrees(Math.atan2(diff[1], hDist)));
    }
    
    public final Vec3f toScreen() {
        return GLUtils.toScreen(this);
    }
    
    @Override
    public String toString() {
        return "Vec3{x=" + this.x + ", y=" + this.y + ", z=" + this.z + '}';
    }
}
