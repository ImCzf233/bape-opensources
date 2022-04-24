// L Bape, Decompiled by ImCzf233

package mc.bape.module.combat;

import mc.bape.utils.*;
import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.realms.*;
import java.util.*;
import net.minecraft.entity.item.*;

public class Aimbot extends Module
{
    private final TimerUtil timer;
    private Numbers<Double> Reach;
    private Option<Boolean> magnetism;
    private Numbers<Double> yaw;
    private Numbers<Double> pitch;
    public EntityLivingBase target;
    
    public Aimbot() {
        super("Aimbot", 0, ModuleType.Combat, "Automatically aim at your target");
        this.timer = new TimerUtil();
        this.Reach = new Numbers<Double>("Reach", "Reach", 4.5, 3.0, 6.0, 1.0);
        this.magnetism = new Option<Boolean>("Magnetism", "Magnetism", true);
        this.yaw = new Numbers<Double>("Yaw", "Yaw", 15.0, 1.0, 50.0, 1.0);
        this.pitch = new Numbers<Double>("Pitch", "Pitch", 15.0, 1.0, 50.0, 1.0);
        this.addValues(this.Reach, this.magnetism);
        Aimbot.Chinese = "\u81ea\u7784";
    }
    
    @Override
    public void disable() {
        this.target = null;
        super.disable();
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent event) {
        this.status = this.Reach.getValue().toString();
        if (this.magnetism.getValue()) {
            if (Aimbot.mc.gameSettings.keyBindAttack.isKeyDown()) {
                this.updateTarget();
                assistFaceEntity((Entity)this.target, this.yaw.getValue().floatValue(), this.pitch.getValue().floatValue());
                this.target = null;
            }
        }
        else {
            this.updateTarget();
            assistFaceEntity((Entity)this.target, this.yaw.getValue().floatValue(), this.pitch.getValue().floatValue());
            this.target = null;
        }
    }
    
    public static void assistFaceEntity(final Entity entity, final float yaw, final float pitch) {
        if (entity == null) {
            return;
        }
        final double diffX = entity.posX - Aimbot.mc.thePlayer.posX;
        final double diffZ = entity.posZ - Aimbot.mc.thePlayer.posZ;
        double yDifference;
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            yDifference = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Aimbot.mc.thePlayer.posY + Aimbot.mc.thePlayer.getEyeHeight());
        }
        else {
            yDifference = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0 - (Aimbot.mc.thePlayer.posY + Aimbot.mc.thePlayer.getEyeHeight());
        }
        final double dist = RealmsMth.sqrt(diffX * diffX + diffZ * diffZ);
        final float rotationYaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        final float rotationPitch = (float)(-(Math.atan2(yDifference, dist) * 180.0 / 3.141592653589793));
        if (yaw > 0.0f) {
            Aimbot.mc.thePlayer.rotationYaw = updateRotation(Aimbot.mc.thePlayer.rotationYaw, rotationYaw, yaw / 4.0f);
        }
        if (pitch > 0.0f) {
            Aimbot.mc.thePlayer.rotationPitch = updateRotation(Aimbot.mc.thePlayer.rotationPitch, rotationPitch, pitch / 4.0f);
        }
    }
    
    public static float updateRotation(final float p_70663_1_, final float p_70663_2_, final float p_70663_3_) {
        float var4 = RealmsMth.wrapDegrees(p_70663_2_ - p_70663_1_);
        if (var4 > p_70663_3_) {
            var4 = p_70663_3_;
        }
        if (var4 < -p_70663_3_) {
            var4 = -p_70663_3_;
        }
        return p_70663_1_ + var4;
    }
    
    void updateTarget() {
        try {
            for (final Entity object : getEntityList()) {
                if (object instanceof EntityLivingBase) {
                    final EntityLivingBase entity;
                    if (!this.check(entity = (EntityLivingBase)object)) {
                        continue;
                    }
                    this.target = entity;
                }
            }
        }
        catch (NullPointerException ex) {}
    }
    
    public static List<Entity> getEntityList() {
        return (List<Entity>)Aimbot.mc.theWorld.getLoadedEntityList();
    }
    
    public boolean check(final EntityLivingBase entity) {
        return !(entity instanceof EntityArmorStand) && entity != Aimbot.mc.thePlayer && !entity.isDead && !AntiBot.isServerBot((Entity)entity) && entity.getDistanceToEntity((Entity)Aimbot.mc.thePlayer) <= this.Reach.getValue() && Aimbot.mc.thePlayer.canEntityBeSeen((Entity)entity);
    }
}
