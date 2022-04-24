// L Bape, Decompiled by ImCzf233

package mc.bape.api.miliblue.events;

import mc.bape.api.miliblue.*;
import java.util.*;
import net.minecraft.client.*;

public class EventPreUpdate extends Event
{
    public static Random rd;
    private float yaw;
    public double x;
    public double z;
    private float pitch;
    public double y;
    public static boolean ground;
    
    public EventPreUpdate(final float yaw, final float pitch, final double y, final boolean ground) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.y = y;
        EventPreUpdate.ground = ground;
    }
    
    public void setRotations(final float[] rotations, final boolean random) {
        if (random) {
            this.yaw = rotations[0] + (float)(EventPreUpdate.rd.nextBoolean() ? Math.random() : (-Math.random()));
            this.pitch = rotations[1] + (float)(EventPreUpdate.rd.nextBoolean() ? Math.random() : (-Math.random()));
        }
        else {
            this.yaw = rotations[0];
            this.pitch = rotations[1];
        }
        Minecraft.getMinecraft().thePlayer.rotationYawHead = this.yaw;
        Minecraft.getMinecraft().thePlayer.renderYawOffset = this.yaw;
        this.setPitch(this.pitch);
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public boolean isOnground() {
        return EventPreUpdate.ground;
    }
    
    public void setOnground(final boolean ground) {
        EventPreUpdate.ground = ground;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    static {
        EventPreUpdate.rd = new Random();
    }
}
