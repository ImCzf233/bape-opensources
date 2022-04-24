package mc.bape.api.miliblue;

public class EventUpdate extends Event
{
    private boolean isPre;
    private float yaw;
    private float pitch;
    private double x;
    private double y;
    private double z;
    private boolean onground;
    private boolean alwaysSend;
    private boolean sneaking;
    public static float YAW;
    public static float PITCH;
    public static float PREVYAW;
    public static float PREVPITCH;
    public static boolean SNEAKING;
    
    public void fire(final double x, final double y, final double z, final float yaw, final float pitch, final boolean sneaking, final boolean ground) {
        this.isPre = true;
        this.yaw = yaw;
        this.pitch = pitch;
        this.y = y;
        this.x = x;
        this.z = z;
        this.onground = ground;
        this.sneaking = sneaking;
        super.fire();
    }
    
    @Override
    public void fire() {
        EventUpdate.PREVYAW = EventUpdate.YAW;
        EventUpdate.PREVPITCH = EventUpdate.PITCH;
        EventUpdate.YAW = this.yaw;
        EventUpdate.PITCH = this.pitch;
        EventUpdate.SNEAKING = this.sneaking;
        this.isPre = false;
        super.fire();
    }
    
    public boolean isPre() {
        return this.isPre;
    }
    
    public boolean isPost() {
        return !this.isPre;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public boolean isSneaking() {
        return this.sneaking;
    }
    
    public boolean isOnground() {
        return this.onground;
    }
    
    public void setSneaking(final boolean sneaking) {
        this.sneaking = sneaking;
    }
    
    public boolean shouldAlwaysSend() {
        return this.alwaysSend;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public void setGround(final boolean ground) {
        this.onground = ground;
    }
    
    public void setAlwaysSend(final boolean alwaysSend) {
        this.alwaysSend = alwaysSend;
    }
    
    public void setOnGround(final boolean onground) {
        this.onground = onground;
    }
}
