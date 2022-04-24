// L Bape, Decompiled by ImCzf233

package mc.bape.module.combat;

import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.client.event.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.util.*;

public class Reach extends Module
{
    public Random rand;
    private Option<Boolean> RandomReach;
    private Option<Boolean> weaponOnly;
    private Option<Boolean> movingOnly;
    private Option<Boolean> sprintOnly;
    private Option<Boolean> hitThroughBlocks;
    public static Numbers<Double> MinReach;
    
    public Reach() {
        super("Reach", 0, ModuleType.Combat, "Make you can attack far target");
        this.RandomReach = new Option<Boolean>("RandomReach", "RandomReach", true);
        this.weaponOnly = new Option<Boolean>("WeaponOnly", "weaponOnly", false);
        this.movingOnly = new Option<Boolean>("MovingOnly", "movingOnly", false);
        this.sprintOnly = new Option<Boolean>("SprintOnly", "sprintOnly", false);
        this.hitThroughBlocks = new Option<Boolean>("HitThroughBlocks", "sprintOnly", false);
        this.addValues(this.weaponOnly, this.movingOnly, this.sprintOnly, this.hitThroughBlocks, Reach.MinReach);
        Reach.Chinese = "\u957f\u81c2\u733f";
    }
    
    @SubscribeEvent
    public void onMove(final MouseEvent ev) {
        if (this.weaponOnly.getValue()) {
            if (Reach.mc.thePlayer.getCurrentEquippedItem() == null) {
                return;
            }
            if (!(Reach.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword) && !(Reach.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemAxe)) {
                return;
            }
        }
        if (this.movingOnly.getValue() && Reach.mc.thePlayer.moveForward == 0.0 && Reach.mc.thePlayer.moveStrafing == 0.0) {
            return;
        }
        if (this.sprintOnly.getValue() && !Reach.mc.thePlayer.isSprinting()) {
            return;
        }
        if (!this.hitThroughBlocks.getValue() && Reach.mc.objectMouseOver != null) {
            final BlockPos blocksReach = Reach.mc.objectMouseOver.getBlockPos();
            if (blocksReach != null && Reach.mc.theWorld.getBlockState(blocksReach).getBlock() != Blocks.air) {
                return;
            }
        }
        final double Reach = MinReach.getValue();
        final Object[] reachs = doReach(Reach, 0.0, 0.0f);
        if (reachs == null) {
            return;
        }
        mc.objectMouseOver = new MovingObjectPosition((Entity)reachs[0], (Vec3)reachs[1]);
        mc.pointedEntity = (Entity)reachs[0];
    }
    
    public static Object[] doReach(final double reachValue, final double AABB, final float cwc) {
        final Entity target = Reach.mc.getRenderViewEntity();
        Entity entity = null;
        if (target == null || Reach.mc.theWorld == null) {
            return null;
        }
        Reach.mc.mcProfiler.startSection("pick");
        final Vec3 targetEyes = target.getPositionEyes(0.0f);
        final Vec3 targetLook = target.getLook(0.0f);
        final Vec3 targetVector = targetEyes.addVector(targetLook.xCoord * reachValue, targetLook.yCoord * reachValue, targetLook.zCoord * reachValue);
        Vec3 targetVec = null;
        final List targetHitbox = Reach.mc.theWorld.getEntitiesWithinAABBExcludingEntity(target, target.getEntityBoundingBox().addCoord(targetLook.xCoord * reachValue, targetLook.yCoord * reachValue, targetLook.zCoord * reachValue).expand(1.0, 1.0, 1.0));
        double reaching = reachValue;
        for (int i = 0; i < targetHitbox.size(); ++i) {
            final Entity targetEntity = (Entity) targetHitbox.get(i);
            if (targetEntity.canBeCollidedWith()) {
                final float targetCollisionBorderSize = targetEntity.getCollisionBorderSize();
                AxisAlignedBB targetAABB = targetEntity.getEntityBoundingBox().expand((double)targetCollisionBorderSize, (double)targetCollisionBorderSize, (double)targetCollisionBorderSize);
                targetAABB = targetAABB.expand(AABB, AABB, AABB);
                final MovingObjectPosition tagetPosition = targetAABB.calculateIntercept(targetEyes, targetVector);
                if (targetAABB.isVecInside(targetEyes)) {
                    if (0.0 < reaching || reaching == 0.0) {
                        entity = targetEntity;
                        targetVec = ((tagetPosition == null) ? targetEyes : tagetPosition.hitVec);
                        reaching = 0.0;
                    }
                }
                else if (tagetPosition != null) {
                    final double targetHitVec = targetEyes.distanceTo(tagetPosition.hitVec);
                    if (targetHitVec < reaching || reaching == 0.0) {
                        final boolean canRiderInteract = false;
                        if (targetEntity == target.ridingEntity) {
                            if (reaching == 0.0) {
                                entity = targetEntity;
                                targetVec = tagetPosition.hitVec;
                            }
                        }
                        else {
                            entity = targetEntity;
                            targetVec = tagetPosition.hitVec;
                            reaching = targetHitVec;
                        }
                    }
                }
            }
        }
        if (reaching < reachValue && !(entity instanceof EntityLivingBase) && !(entity instanceof EntityItemFrame)) {
            entity = null;
        }
        Reach.mc.mcProfiler.endSection();
        if (entity == null || targetVec == null) {
            return null;
        }
        return new Object[] { entity, targetVec };
    }
    
    static {
        Reach.MinReach = new Numbers<Double>("Reach", "Reach", 3.5, -2.0, 6.0, 1.0);
    }
}
