package mc.bape.utils.math;

public class Vec4f
{
    public float x;
    public float y;
    public float w;
    public float h;
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public float getW() {
        return this.w;
    }
    
    public float getH() {
        return this.h;
    }
    
    public void setX(final float x2) {
        this.x = x2;
    }
    
    public void setY(final float y2) {
        this.y = y2;
    }
    
    public void setW(final float w2) {
        this.w = w2;
    }
    
    public void setH(final float h2) {
        this.h = h2;
    }
    
    public Vec4f(final float x2, final float y2, final float w2, final float h2) {
        this.x = x2;
        this.y = y2;
        this.w = w2;
        this.h = h2;
    }
}
