// L Bape, Decompiled by ImCzf233

package mc.bape.module.movement.ScaffoldUtils;

import net.minecraft.client.*;
import net.minecraft.entity.*;
import mc.bape.utils.*;
import com.google.common.base.*;
import java.util.*;
import net.minecraft.util.*;

public class RaycastUtil
{
    static Minecraft mc;
    
    public static Entity raycastEntity(final double range, final IEntityFilter entityFilter) {
        return raycastEntity(range, RotationUtils.serverRotation.getYaw(), RotationUtils.serverRotation.getPitch(), entityFilter);
    }
    
    private static Entity raycastEntity(final double range, final float yaw, final float pitch, final IEntityFilter entityFilter) {
        final Entity renderViewEntity = RaycastUtil.mc.getRenderViewEntity();
        if (renderViewEntity != null && RaycastUtil.mc.theWorld != null) {
            double blockReachDistance = range;
            final Vec3 eyePosition = renderViewEntity.getPositionEyes(1.0f);
            final float yawCos = MathHelper.cos(-yaw * 0.017453292f - 3.1415927f);
            final float yawSin = MathHelper.sin(-yaw * 0.017453292f - 3.1415927f);
            final float pitchCos = -MathHelper.cos(-pitch * 0.017453292f);
            final float pitchSin = MathHelper.sin(-pitch * 0.017453292f);
            final Vec3 entityLook = new Vec3((double)(yawSin * pitchCos), (double)pitchSin, (double)(yawCos * pitchCos));
            final Vec3 vector = eyePosition.addVector(entityLook.xCoord * blockReachDistance, entityLook.yCoord * blockReachDistance, entityLook.zCoord * blockReachDistance);
            final List<Entity> entityList = (List<Entity>)RaycastUtil.mc.theWorld.getEntitiesInAABBexcluding(renderViewEntity, renderViewEntity.getEntityBoundingBox().addCoord(entityLook.xCoord * blockReachDistance, entityLook.yCoord * blockReachDistance, entityLook.zCoord * blockReachDistance).expand(1.0, 1.0, 1.0), Predicates.and(EntitySelectors.NOT_SPECTATING, Entity::canBeCollidedWith));
            Entity pointedEntity = null;
            for (final Entity entity : entityList) {
                if (!entityFilter.canRaycast(entity)) {
                    continue;
                }
                final float collisionBorderSize = entity.getCollisionBorderSize();
                final AxisAlignedBB axisAlignedBB = entity.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
                final MovingObjectPosition movingObjectPosition = axisAlignedBB.calculateIntercept(eyePosition, vector);
                if (axisAlignedBB.isVecInside(eyePosition)) {
                    if (blockReachDistance < 0.0) {
                        continue;
                    }
                    pointedEntity = entity;
                    blockReachDistance = 0.0;
                }
                else {
                    if (movingObjectPosition == null) {
                        continue;
                    }
                    final double eyeDistance = eyePosition.distanceTo(movingObjectPosition.hitVec);
                    if (eyeDistance >= blockReachDistance && blockReachDistance != 0.0) {
                        continue;
                    }
                    pointedEntity = entity;
                    blockReachDistance = eyeDistance;
                }
            }
            return pointedEntity;
        }
        return null;
    }
    
    public static Vec3 getVectorForRotation(final float pitch, final float yaw) {
        final float f = MathHelper.cos(-yaw * 0.017453292f - 3.1415927f);
        final float f2 = MathHelper.sin(-yaw * 0.017453292f - 3.1415927f);
        final float f3 = -MathHelper.cos(-pitch * 0.017453292f);
        final float f4 = MathHelper.sin(-pitch * 0.017453292f);
        return new Vec3((double)(f2 * f3), (double)f4, (double)(f * f3));
    }
    
    public static MovingObjectPosition rayTrace(final Entity view, final double blockReachDistance, final float partialTick, final float yaw, final float pitch) {
        final Vec3 vec3 = view.getPositionEyes(1.0f);
        final Vec3 vec4 = getVectorForRotation(pitch, yaw);
        final Vec3 vec5 = vec3.addVector(vec4.xCoord * blockReachDistance, vec4.yCoord * blockReachDistance, vec4.zCoord * blockReachDistance);
        return view.worldObj.rayTraceBlocks(vec3, vec5, false, false, true);
    }
    
    public static MovingObjectPosition getMouseOver(final Entity entity, final float yaw, final float pitch, final double range) {
        if (entity != null && RaycastUtil.mc.theWorld != null) {
            Entity pointedEntity = null;
            RaycastUtil.mc.pointedEntity = null;
            MovingObjectPosition objectMouseOver = rayTrace(entity, range, 1.0f, yaw, pitch);
            double d1 = range;
            final Vec3 vec3 = entity.getPositionEyes(1.0f);
            final boolean flag = false;
            final boolean flag2 = true;
            if (objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                d1 = objectMouseOver.hitVec.distanceTo(vec3);
            }
            final Vec3 vec4 = getVectorForRotation(pitch, yaw);
            final Vec3 vec5 = vec3.addVector(vec4.xCoord * range, vec4.yCoord * range, vec4.zCoord * range);
            Vec3 vec6 = null;
            final float f = 1.0f;
            final List list = RaycastUtil.mc.theWorld.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().addCoord(vec4.xCoord * range, vec4.yCoord * range, vec4.zCoord * range).expand((double)f, (double)f, (double)f), Predicates.and(EntitySelectors.NOT_SPECTATING, Entity::canBeCollidedWith));
            double d2 = d1;
            for (int i = 0; i < list.size(); ++i) {
                final Entity entity2 = (Entity) list.get(i);
                final float f2 = entity2.getCollisionBorderSize();
                final AxisAlignedBB axisalignedbb = entity2.getEntityBoundingBox().expand((double)f2, (double)f2, (double)f2);
                final MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec5);
                if (axisalignedbb.isVecInside(vec3)) {
                    if (d2 >= 0.0) {
                        pointedEntity = entity2;
                        vec6 = ((movingobjectposition == null) ? vec3 : movingobjectposition.hitVec);
                        d2 = 0.0;
                    }
                }
                else if (movingobjectposition != null) {
                    final double d3 = vec3.distanceTo(movingobjectposition.hitVec);
                    if (d3 < d2 || d2 == 0.0) {
                        final boolean flag3 = false;
                        if (entity2 == entity.ridingEntity && !flag3) {
                            if (d2 == 0.0) {
                                pointedEntity = entity2;
                                vec6 = movingobjectposition.hitVec;
                            }
                        }
                        else {
                            pointedEntity = entity2;
                            vec6 = movingobjectposition.hitVec;
                            d2 = d3;
                        }
                    }
                }
            }
            if (pointedEntity != null && flag && vec3.distanceTo(vec6) > range) {
                objectMouseOver = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec6, (EnumFacing)null, new BlockPos(vec6));
            }
            if (pointedEntity != null && (d2 < d1 || objectMouseOver == null)) {
                objectMouseOver = new MovingObjectPosition(pointedEntity, vec6);
            }
            return objectMouseOver;
        }
        return null;
    }
    
    static {
        RaycastUtil.mc = Minecraft.getMinecraft();
    }
    
    public interface IEntityFilter
    {
        boolean canRaycast(final Entity p0);
    }
}
