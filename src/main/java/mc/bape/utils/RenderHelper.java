// L Bape, Decompiled by ImCzf233

package mc.bape.utils;

import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.*;
import java.lang.reflect.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class RenderHelper
{
    static Minecraft mc;
    
    public static void drawBlockESP(final BlockPos targetBlockPos, final int color) {
        if (targetBlockPos == null) {
            return;
        }
        final double x = targetBlockPos.getX() - RenderHelper.mc.getRenderManager().viewerPosX;
        final double y = targetBlockPos.getY() - RenderHelper.mc.getRenderManager().viewerPosY;
        final double z = targetBlockPos.getZ() - RenderHelper.mc.getRenderManager().viewerPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        GL11.glColor4d((double)r, (double)g, (double)b, (double)a);
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        drawAABB(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), r, g, b);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static Timer getTimer() {
        try {
            final Class<Minecraft> c = Minecraft.class;
            final Field f = c.getDeclaredField(new String(new char[] { 't', 'i', 'm', 'e', 'r' }));
            f.setAccessible(true);
            return (Timer)f.get(RenderHelper.mc);
        }
        catch (Exception er) {
            try {
                final Class<Minecraft> c2 = Minecraft.class;
                final Field f2 = c2.getDeclaredField(new String(new char[] { 'f', 'i', 'e', 'l', 'd', '_', '7', '1', '4', '2', '8', '_', 'T' }));
                f2.setAccessible(true);
                return (Timer)f2.get(RenderHelper.mc);
            }
            catch (Exception er2) {
                return null;
            }
        }
    }
    
    public static void drawESP(final Entity e, int color, final boolean damage, final int type) {
        if (e == null) {
            return;
        }
        final double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * getTimer().renderPartialTicks - RenderHelper.mc.getRenderManager().viewerPosX;
        final double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * getTimer().renderPartialTicks - RenderHelper.mc.getRenderManager().viewerPosY;
        final double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * getTimer().renderPartialTicks - RenderHelper.mc.getRenderManager().viewerPosZ;
        if (e instanceof EntityPlayer && damage && ((EntityPlayer)e).hurtTime != 0) {
            color = Color.RED.getRGB();
        }
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        if (type == 1) {
            GlStateManager.pushMatrix();
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glLineWidth(3.0f);
            GL11.glColor4f(r, g, b, a);
            RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(e.getEntityBoundingBox().minX - 0.05 - e.posX + (e.posX - RenderHelper.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().minY - e.posY + (e.posY - RenderHelper.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().minZ - 0.05 - e.posZ + (e.posZ - RenderHelper.mc.getRenderManager().viewerPosZ), e.getEntityBoundingBox().maxX + 0.05 - e.posX + (e.posX - RenderHelper.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().maxY + 0.1 - e.posY + (e.posY - RenderHelper.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().maxZ + 0.05 - e.posZ + (e.posZ - RenderHelper.mc.getRenderManager().viewerPosZ)));
            drawAABB(new AxisAlignedBB(e.getEntityBoundingBox().minX - 0.05 - e.posX + (e.posX - RenderHelper.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().minY - e.posY + (e.posY - RenderHelper.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().minZ - 0.05 - e.posZ + (e.posZ - RenderHelper.mc.getRenderManager().viewerPosZ), e.getEntityBoundingBox().maxX + 0.05 - e.posX + (e.posX - RenderHelper.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().maxY + 0.1 - e.posY + (e.posY - RenderHelper.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().maxZ + 0.05 - e.posZ + (e.posZ - RenderHelper.mc.getRenderManager().viewerPosZ)), r, g, b);
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glDisable(3042);
            GlStateManager.popMatrix();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
        else if (type == 2 || type == 3) {
            final boolean mode = type == 2;
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glLineWidth(3.0f);
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glColor4d((double)r, (double)g, (double)b, (double)a);
            if (mode) {
                RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(e.getEntityBoundingBox().minX - 0.05 - e.posX + (e.posX - RenderHelper.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().minY - e.posY + (e.posY - RenderHelper.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().minZ - 0.05 - e.posZ + (e.posZ - RenderHelper.mc.getRenderManager().viewerPosZ), e.getEntityBoundingBox().maxX + 0.05 - e.posX + (e.posX - RenderHelper.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().maxY + 0.1 - e.posY + (e.posY - RenderHelper.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().maxZ + 0.05 - e.posZ + (e.posZ - RenderHelper.mc.getRenderManager().viewerPosZ)));
            }
            else {
                drawAABB(new AxisAlignedBB(e.getEntityBoundingBox().minX - 0.05 - e.posX + (e.posX - RenderHelper.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().minY - e.posY + (e.posY - RenderHelper.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().minZ - 0.05 - e.posZ + (e.posZ - RenderHelper.mc.getRenderManager().viewerPosZ), e.getEntityBoundingBox().maxX + 0.05 - e.posX + (e.posX - RenderHelper.mc.getRenderManager().viewerPosX), e.getEntityBoundingBox().maxY + 0.1 - e.posY + (e.posY - RenderHelper.mc.getRenderManager().viewerPosY), e.getEntityBoundingBox().maxZ + 0.05 - e.posZ + (e.posZ - RenderHelper.mc.getRenderManager().viewerPosZ)), r, g, b);
            }
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glDisable(3042);
        }
        else if (type == 4) {
            GL11.glPushMatrix();
            GL11.glTranslated(x, y - 0.2, z);
            GL11.glScalef(0.03f, 0.03f, 0.03f);
            GL11.glRotated((double)(-RenderHelper.mc.getRenderManager().playerViewY), 0.0, 1.0, 0.0);
            GlStateManager.disableDepth();
            Gui.drawRect(-20, -1, -26, 75, Color.black.getRGB());
            Gui.drawRect(-21, 0, -25, 74, color);
            Gui.drawRect(20, -1, 26, 75, Color.black.getRGB());
            Gui.drawRect(21, 0, 25, 74, color);
            Gui.drawRect(-20, -1, 21, 5, Color.black.getRGB());
            Gui.drawRect(-21, 0, 24, 4, color);
            Gui.drawRect(-20, 70, 21, 75, Color.black.getRGB());
            Gui.drawRect(-21, 71, 25, 74, color);
            GlStateManager.enableDepth();
            GL11.glPopMatrix();
        }
    }
    
    public static void drawAABB(final AxisAlignedBB aabb, final float r, final float g, final float b) {
        final float a = 0.25f;
        final Tessellator ts = Tessellator.getInstance();
        final WorldRenderer vb = ts.getWorldRenderer();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(aabb.minX, aabb.minY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.maxY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.minY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.maxY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.minY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.maxY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.minY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.maxY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(aabb.maxX, aabb.maxY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.minY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.maxY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.minY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.maxY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.minY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.maxY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.minY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(aabb.minX, aabb.maxY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.maxY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.maxY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.maxY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.maxY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.maxY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.maxY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.maxY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(aabb.minX, aabb.minY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.minY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.minY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.minY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.minY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.minY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.minY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.minY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(aabb.minX, aabb.minY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.maxY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.minY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.maxY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.minY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.maxY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.minY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.maxY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(aabb.minX, aabb.maxY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.minY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.maxY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.minX, aabb.minY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.maxY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.minY, aabb.minZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.maxY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        vb.pos(aabb.maxX, aabb.minY, aabb.maxZ).color(r, g, b, 0.25f).endVertex();
        ts.draw();
    }
    
    public static void drawTracers(final Entity e, final int color, final float lw) {
        if (e == null) {
            return;
        }
        final double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * getTimer().renderPartialTicks - RenderHelper.mc.getRenderManager().viewerPosX;
        final double y = e.getEyeHeight() + e.lastTickPosY + (e.posY - e.lastTickPosY) * getTimer().renderPartialTicks - RenderHelper.mc.getRenderManager().viewerPosY;
        final double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * getTimer().renderPartialTicks - RenderHelper.mc.getRenderManager().viewerPosZ;
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(lw);
        GL11.glColor4f(r, g, b, a);
        GL11.glBegin(2);
        GL11.glVertex3d(0.0, 0.0 + RenderHelper.mc.thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    static {
        RenderHelper.mc = Minecraft.getMinecraft();
    }
}
