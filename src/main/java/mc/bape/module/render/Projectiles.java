// L Bape, Decompiled by ImCzf233

package mc.bape.module.render;

import mc.bape.module.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.*;
import com.google.common.base.*;
import net.minecraft.block.material.*;
import org.lwjgl.opengl.*;
import mc.bape.utils.*;
import java.awt.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.*;
import java.util.*;
import java.util.List;

import net.minecraftforge.fml.common.eventhandler.*;

public class Projectiles extends Module
{
    public Projectiles() {
        super("Projectiles", 0, ModuleType.Render, "Show a projectiles for bow and snowball");
        Projectiles.Chinese = "\u629b\u7269\u7ebf";
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        final RenderManager renderManager = Projectiles.mc.getRenderManager();
        try {
            final double renderPosX = (double)ReflectionUtil.getFieldValue(renderManager, "renderPosX");
            final double renderPosY = (double)ReflectionUtil.getFieldValue(renderManager, "renderPosY");
            final double renderPosZ = (double)ReflectionUtil.getFieldValue(renderManager, "renderPosZ");
            if (Module.mc.thePlayer.getHeldItem() == null) {
                return;
            }
            final Item item = Module.mc.thePlayer.getHeldItem().getItem();
            boolean isBow = false;
            float motionFactor = 1.5f;
            float motionSlowdown = 0.99f;
            float gravity = 0.0f;
            float size = 0.0f;
            if (item instanceof ItemBow) {
                if (!Module.mc.thePlayer.isUsingItem()) {
                    return;
                }
                isBow = true;
                gravity = 0.05f;
                size = 0.3f;
                float power = Module.mc.thePlayer.getItemInUseDuration() / 20.0f;
                if ((power = (power * power + power * 2.0f) / 3.0f) < 0.1f) {
                    return;
                }
                if (power > 1.0f) {
                    power = 1.0f;
                }
                motionFactor = power * 3.0f;
            }
            else if (item instanceof ItemFishingRod) {
                gravity = 0.04f;
                size = 0.25f;
                motionSlowdown = 0.92f;
            }
            else if (item instanceof ItemPotion && ItemPotion.isSplash(Module.mc.thePlayer.getHeldItem().getItemDamage())) {
                gravity = 0.05f;
                size = 0.25f;
                motionFactor = 0.5f;
            }
            else {
                if (!(item instanceof ItemSnowball) && !(item instanceof ItemEnderPearl) && !(item instanceof ItemEgg)) {
                    return;
                }
                gravity = 0.03f;
                size = 0.25f;
            }
            final float yaw = Module.mc.thePlayer.rotationYaw;
            final float pitch = Module.mc.thePlayer.rotationPitch;
            double posX = renderPosX - MathHelper.cos(yaw / 180.0f * 3.1415927f) * 0.16f;
            double posY = renderPosY + Module.mc.thePlayer.getEyeHeight() - 0.10000000149011612;
            double posZ = renderPosZ - MathHelper.sin(yaw / 180.0f * 3.1415927f) * 0.16f;
            double motionX = -MathHelper.sin(yaw / 180.0f * 3.1415927f) * MathHelper.cos(pitch / 180.0f * 3.1415927f) * (isBow ? 1.0 : 0.4);
            double motionY = -MathHelper.sin((pitch + ((item instanceof ItemPotion && ItemPotion.isSplash(Module.mc.thePlayer.getHeldItem().getItemDamage())) ? -20 : 0)) / 180.0f * 3.1415927f) * (isBow ? 1.0 : 0.4);
            double motionZ = MathHelper.cos(yaw / 180.0f * 3.1415927f) * MathHelper.cos(pitch / 180.0f * 3.1415927f) * (isBow ? 1.0 : 0.4);
            final float distance = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
            motionX /= distance;
            motionY /= distance;
            motionZ /= distance;
            motionX *= motionFactor;
            motionY *= motionFactor;
            motionZ *= motionFactor;
            MovingObjectPosition landingPosition = null;
            boolean hasLanded = false;
            boolean hitEntity = false;
            final Tessellator tessellator = Tessellator.getInstance();
            final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            final List<Vec3> pos = new ArrayList<Vec3>();
            while (!hasLanded && posY > 0.0) {
                Object posBefore = new Vec3(posX, posY, posZ);
                Vec3 posAfter = new Vec3(posX + motionX, posY + motionY, posZ + motionZ);
                landingPosition = Module.mc.theWorld.rayTraceBlocks((Vec3)posBefore, posAfter, false, true, false);
                posBefore = new Vec3(posX, posY, posZ);
                posAfter = new Vec3(posX + motionX, posY + motionY, posZ + motionZ);
                if (landingPosition != null) {
                    hasLanded = true;
                    posAfter = new Vec3(landingPosition.hitVec.xCoord, landingPosition.hitVec.yCoord, landingPosition.hitVec.zCoord);
                }
                final AxisAlignedBB arrowBox = new AxisAlignedBB(posX - size, posY - size, posZ - size, posX + size, posY + size, posZ + size).addCoord(motionX, motionY, motionZ).expand(1.0, 1.0, 1.0);
                final int chunkMinX = MathHelper.floor_double((arrowBox.minX - 2.0) / 16.0);
                final int chunkMaxX = MathHelper.floor_double((arrowBox.maxX + 2.0) / 16.0);
                final int chunkMinZ = MathHelper.floor_double((arrowBox.minZ - 2.0) / 16.0);
                final int chunkMaxZ = MathHelper.floor_double((arrowBox.maxZ + 2.0) / 16.0);
                final List<Entity> collidedEntities = new ArrayList<Entity>();
                int n = chunkMinX;
                if (n <= chunkMaxX) {
                    int x;
                    do {
                        x = n++;
                        int n2 = chunkMinZ;
                        if (n2 > chunkMaxZ) {
                            continue;
                        }
                        int z;
                        do {
                            z = n2++;
                            Module.mc.theWorld.getChunkFromChunkCoords(x, z).getEntitiesWithinAABBForEntity((Entity)Module.mc.thePlayer, arrowBox, (List)collidedEntities, (Predicate)null);
                        } while (z != chunkMaxZ);
                    } while (x != chunkMaxX);
                }
                for (final Entity possibleEntity : collidedEntities) {
                    if (possibleEntity.canBeCollidedWith() && possibleEntity != Module.mc.thePlayer) {
                        final AxisAlignedBB possibleEntityBoundingBox;
                        final MovingObjectPosition movingObjectPosition;
                        if ((movingObjectPosition = (possibleEntityBoundingBox = possibleEntity.getEntityBoundingBox().expand((double)size, (double)size, (double)size)).calculateIntercept((Vec3)posBefore, posAfter)) == null) {
                            continue;
                        }
                        final MovingObjectPosition possibleEntityLanding = movingObjectPosition;
                        hitEntity = true;
                        hasLanded = true;
                        landingPosition = possibleEntityLanding;
                    }
                }
                if (Module.mc.theWorld.getBlockState(new BlockPos(posX += motionX, posY += motionY, posZ += motionZ)).getBlock().getMaterial() == Material.water) {
                    motionX *= 0.6;
                    motionY *= 0.6;
                    motionZ *= 0.6;
                }
                else {
                    motionX *= motionSlowdown;
                    motionY *= motionSlowdown;
                    motionZ *= motionSlowdown;
                }
                motionY -= gravity;
                pos.add(new Vec3(posX - renderPosX, posY - renderPosY, posZ - renderPosZ));
            }
            GL11.glDepthMask(false);
            Object posBefore = new int[]{3042, 2848};
            RenderUtils.enableGlCap((int[])posBefore);
            posBefore = new int[] { 2929, 3008, 3553 };
            RenderUtils.disableGlCap((int[])posBefore);
            GL11.glBlendFunc(770, 771);
            GL11.glHint(3154, 4354);
            final Color color = hitEntity ? new Color(255, 140, 140) : new Color(140, 255, 140);
            RenderUtils.glColor(color);
            GL11.glLineWidth(2.0f);
            worldRenderer.begin(3, DefaultVertexFormats.POSITION);
            final boolean $i$f$forEach = false;
            for (final Object element$iv : pos) {
                final Vec3 it = (Vec3)element$iv;
                final boolean bl = false;
                worldRenderer.pos(it.xCoord, it.yCoord, it.zCoord).endVertex();
            }
            tessellator.draw();
            GL11.glPushMatrix();
            GL11.glTranslated(posX - renderPosX, posY - renderPosY, posZ - renderPosZ);
            if (landingPosition != null) {
                switch (landingPosition.sideHit.getAxis().ordinal()) {
                    case 0: {
                        GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                        break;
                    }
                    case 2: {
                        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                        break;
                    }
                }
                RenderUtils.drawAxisAlignedBB(new AxisAlignedBB(-0.5, 0.0, -0.5, 0.5, 0.1, 0.5), color, true, true, 3.0f);
            }
            GL11.glPopMatrix();
            GL11.glDepthMask(true);
            RenderUtils.resetCaps();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
        catch (NullPointerException ex) {}
    }
}
