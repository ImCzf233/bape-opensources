// L Bape, Decompiled by ImCzf233

package mc.bape.utils;

import java.text.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.util.*;
import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import java.util.function.*;
import net.minecraft.client.renderer.*;
import mc.bape.api.*;
import java.util.*;

public class RenderUtils
{
    private static final AxisAlignedBB DEFAULT_AABB;
    private static final Map<Integer, Boolean> glCapMap;
    public static TimerUtil splashTimer;
    public static int splashTickPos;
    public static boolean isSplash;
    
    public static String DF(final double value, final int maxvalue) {
        final DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(maxvalue);
        return df.format(value);
    }
    
    public static void drawOutlinedEntityESP(final double x, final double y, final double z, final double width, final double height, final float red, final float green, final float blue, final float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1.5f);
        GL11.glColor4f(red, green, blue, alpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawOutlinedBoundingBox(final AxisAlignedBB aa) {
        final Tessellator tessellator = Tessellator.getInstance();
        GlStateManager.alphaFunc(516, 0.05f);
        final WorldRenderer bufferBuilder = tessellator.getWorldRenderer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        bufferBuilder.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        tessellator.draw();
    }
    
    public static void drawBlockESP(final BlockPos pos, final float red, final float green, final float blue) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        final double renderPosX = Helper.mc.getRenderManager().viewerPosX;
        final double renderPosY = Helper.mc.getRenderManager().viewerPosY;
        final double renderPosZ = Helper.mc.getRenderManager().viewerPosZ;
        GL11.glTranslated(-renderPosX, -renderPosY, -renderPosZ);
        GL11.glTranslated((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        GL11.glColor4f(red, green, blue, 0.3f);
        drawSolidBox();
        GL11.glColor4f(red, green, blue, 0.7f);
        drawOutlinedBox();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static void drawSolidBox() {
        drawSolidBox(RenderUtils.DEFAULT_AABB);
    }
    
    public static void drawSolidBox(final AxisAlignedBB bb) {
        GL11.glBegin(7);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glEnd();
    }
    
    public static void drawOutlinedBox() {
        drawOutlinedBox(RenderUtils.DEFAULT_AABB);
    }
    
    public static void drawOutlinedBox(final AxisAlignedBB bb) {
        GL11.glBegin(1);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glEnd();
    }
    
    public static void drawImage(final float x, final float y, final int width, final int height, final ResourceLocation image, final Color color) {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, 1.0f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture((int)x, (int)y, 0.0f, 0.0f, width, height, (float)width, (float)height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }
    
    public static void drawRoundRect(final float x, final float y, final float x1, final float y1, final int color) {
        RenderUtil.drawRoundedRect(x, y, x1, y1, color, color);
        GlStateManager.color(1.0f, 1.0f, 1.0f);
    }
    
    public static void drawCircle(final double x, final double y, final double radius, final int c) {
        GL11.glEnable(32925);
        GL11.glEnable(2881);
        final float alpha = (c >> 24 & 0xFF) / 255.0f;
        final float red = (c >> 16 & 0xFF) / 255.0f;
        final float green = (c >> 8 & 0xFF) / 255.0f;
        final float blue = (c & 0xFF) / 255.0f;
        final boolean blend = GL11.glIsEnabled(3042);
        final boolean line = GL11.glIsEnabled(2848);
        final boolean texture = GL11.glIsEnabled(3553);
        if (!blend) {
            GL11.glEnable(3042);
        }
        if (!line) {
            GL11.glEnable(2848);
        }
        if (texture) {
            GL11.glDisable(3553);
        }
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(9);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d(x + Math.sin(i * 3.141526 / 180.0) * radius, y + Math.cos(i * 3.141526 / 180.0) * radius);
        }
        GL11.glEnd();
        if (texture) {
            GL11.glEnable(3553);
        }
        if (!line) {
            GL11.glDisable(2848);
        }
        if (!blend) {
            GL11.glDisable(3042);
        }
        GL11.glDisable(2881);
        GL11.glClear(0);
    }
    
    public static void glColor(final Color color) {
        final float red = color.getRed() / 255.0f;
        final float green = color.getGreen() / 255.0f;
        final float blue = color.getBlue() / 255.0f;
        final float alpha = color.getAlpha() / 255.0f;
        GlStateManager.color(red, green, blue, alpha);
    }
    
    public static void glColor(final int red, final int green, final int blue, final int alpha) {
        GlStateManager.color(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);
    }
    
    public static void enableGlCap(final int... caps) {
        for (final int cap : caps) {
            setGlCap(cap, true);
        }
    }
    
    public static void disableGlCap(final int... caps) {
        for (final int cap : caps) {
            setGlCap(cap, false);
        }
    }
    
    public static void setGlCap(final int cap, final boolean state) {
        RenderUtils.glCapMap.put(cap, GL11.glGetBoolean(cap));
        setGlState(cap, state);
    }
    
    public static void setGlState(final int cap, final boolean state) {
        if (state) {
            GL11.glEnable(cap);
        }
        else {
            GL11.glDisable(cap);
        }
    }
    
    public static void resetCaps() {
        RenderUtils.glCapMap.forEach(RenderUtils::setGlState);
        RenderUtils.glCapMap.clear();
    }
    
    public static void drawAxisAlignedBB(final AxisAlignedBB axisAlignedBB, final Color color, final boolean outline, final boolean box, final float outlineWidth) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(outlineWidth);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        glColor(color);
        if (outline) {
            GL11.glLineWidth(outlineWidth);
            enableGlCap(2848);
            glColor(color.getRed(), color.getGreen(), color.getBlue(), 95);
            RenderGlobal.drawSelectionBoundingBox(axisAlignedBB);
        }
        if (box) {
            glColor(color.getRed(), color.getGreen(), color.getBlue(), outline ? 26 : 35);
            WorldRenderUtils.drawFilledBox(axisAlignedBB);
        }
        GlStateManager.resetColor();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    static {
        DEFAULT_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        glCapMap = new HashMap<Integer, Boolean>();
        RenderUtils.splashTimer = new TimerUtil();
        RenderUtils.splashTickPos = 0;
        RenderUtils.isSplash = false;
    }
}
