// L Bape, Decompiled by ImCzf233

package mc.bape.module.blatant;

import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraft.util.*;
import mc.bape.vapu.*;
import java.util.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.potion.*;

public class Speed extends Module
{
    private int i;
    private static Mode<Enum> mode;
    public static double movementSpeed;
    
    public Speed() {
        super("Speed", 0, ModuleType.Blatant, "Make you move quickly");
        this.addValues(Speed.mode);
        Speed.Chinese = "\u901f\u5ea6";
    }
    
    @Override
    public void disable() {
        Objects.requireNonNull(Client.getTimer()).timerSpeed = 1.0f;
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent event) {
        this.status = Speed.mode.getValue().toString();
        if (Speed.mode.getValue() == speedmode.Boosting) {
            if (this.i == 0) {
                this.i = Speed.mc.thePlayer.ticksExisted;
            }
            Objects.requireNonNull(Client.getTimer()).timerSpeed = 2.0f;
        }
        if (Speed.mode.getValue() == speedmode.FastHop) {
            if (!Speed.mc.thePlayer.isCollidedHorizontally && Speed.mc.thePlayer.moveForward > 0.0f && Speed.mc.thePlayer.onGround) {
                Speed.mc.thePlayer.setSprinting(true);
                Speed.mc.thePlayer.jump();
                Objects.requireNonNull(Client.getTimer()).timerSpeed = 1.0f;
            }
        }
        else if (Speed.mode.getValue() == speedmode.SlowHop) {
            if (!Speed.mc.thePlayer.isCollidedHorizontally && Speed.mc.thePlayer.moveForward > 0.0f && Speed.mc.thePlayer.onGround) {
                Speed.mc.thePlayer.jump();
                setSpeed(0.26 + getNormalSpeedEffect() * 0.05);
            }
            else {
                setSpeed(0.0);
            }
            Speed.movementSpeed = 0.26;
        }
        else if (Speed.mode.getValue() == speedmode.BunnyHop && this.isToJump() && Minecraft.getMinecraft().thePlayer.moveForward != 0.0f && Minecraft.getMinecraft().thePlayer.posY % 1.0 == 0.0) {
            Minecraft.getMinecraft().thePlayer.jump();
        }
    }
    
    public boolean isToJump() {
        return Speed.mc.thePlayer != null && !Speed.mc.thePlayer.isInWater() && !Speed.mc.thePlayer.isOnLadder();
    }
    
    public static double getNormalSpeedEffect() {
        if (Speed.mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
            return Speed.mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1;
        }
        return 0.0;
    }
    
    public static void setSpeed(final double speed) {
        Speed.mc.thePlayer.motionX = -Math.sin(getDirection()) * speed;
        Speed.mc.thePlayer.motionZ = Math.cos(getDirection()) * speed;
    }
    
    public static float getDirection() {
        float yaw = Speed.mc.thePlayer.rotationYaw;
        if (Speed.mc.thePlayer.movementInput.moveForward < 0.0f) {
            yaw += 180.0f;
        }
        float forward = 1.0f;
        if (Speed.mc.thePlayer.movementInput.moveForward < 0.0f) {
            forward = -0.5f;
        }
        else if (Speed.mc.thePlayer.movementInput.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (Speed.mc.thePlayer.movementInput.moveStrafe > 0.0f) {
            yaw -= 90.0f * forward;
        }
        if (Speed.mc.thePlayer.movementInput.moveStrafe < 0.0f) {
            yaw += 90.0f * forward;
        }
        return yaw * 0.017453292f;
    }
    
    public static double getMotionX(final double speed) {
        return -Math.sin(getDirection()) * speed;
    }
    
    public static double getMotionZ(final double speed) {
        return Math.cos(getDirection()) * speed;
    }
    
    static {
        Speed.mode = new Mode<Enum>("Mode", "mode", speedmode.values(), speedmode.FastHop);
    }
    
    enum speedmode
    {
        FastHop, 
        Boosting, 
        BunnyHop, 
        SlowHop;
    }
}
