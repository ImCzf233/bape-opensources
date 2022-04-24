// L Bape, Decompiled by ImCzf233

package mc.bape.utils;

import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import java.awt.*;

public class GuiRenderUtils
{
    public static Minecraft mc;
    private static float scissorX;
    private static float scissorY;
    private static float scissorWidth;
    private static float scissorHeight;
    private static float scissorSF;
    private static boolean isScissoring;
    
    public static float[] getScissor() {
        if (GuiRenderUtils.isScissoring) {
            return new float[] { GuiRenderUtils.scissorX, GuiRenderUtils.scissorY, GuiRenderUtils.scissorWidth, GuiRenderUtils.scissorHeight, GuiRenderUtils.scissorSF };
        }
        return new float[] { -1.0f };
    }
    
    public static void beginCrop(final float x, final float y, final float width, final float height) {
        final float scaleFactor = getScaleFactor();
        GL11.glEnable(3089);
        GL11.glScissor((int)(x * scaleFactor), (int)(Display.getHeight() - y * scaleFactor), (int)(width * scaleFactor), (int)(height * scaleFactor));
        GuiRenderUtils.isScissoring = true;
        GuiRenderUtils.scissorX = x;
        GuiRenderUtils.scissorY = y;
        GuiRenderUtils.scissorWidth = width;
        GuiRenderUtils.scissorHeight = height;
        GuiRenderUtils.scissorSF = scaleFactor;
    }
    
    public static void beginCropFixed(final float x, final float y, final float width, final float height) {
        final float scaleFactor = getScaleFactor();
        GL11.glEnable(3089);
        GL11.glScissor((int)(x * scaleFactor), (int)(Display.getHeight() - y * scaleFactor), (int)(width * scaleFactor), (int)(height * scaleFactor));
        GuiRenderUtils.isScissoring = true;
        GuiRenderUtils.scissorX = x;
        GuiRenderUtils.scissorY = y;
        GuiRenderUtils.scissorWidth = width;
        GuiRenderUtils.scissorHeight = height;
        GuiRenderUtils.scissorSF = scaleFactor;
    }
    
    public static void beginCrop(final float x, final float y, final float width, final float height, final float scaleFactor) {
        GL11.glEnable(3089);
        GL11.glScissor((int)(x * scaleFactor), (int)(Display.getHeight() - y * scaleFactor), (int)(width * scaleFactor), (int)(height * scaleFactor));
        GuiRenderUtils.isScissoring = true;
        GuiRenderUtils.scissorX = x;
        GuiRenderUtils.scissorY = y;
        GuiRenderUtils.scissorWidth = width;
        GuiRenderUtils.scissorHeight = height;
        GuiRenderUtils.scissorSF = scaleFactor;
    }
    
    public static void endCrop() {
        GL11.glDisable(3089);
        GuiRenderUtils.isScissoring = false;
    }
    
    public static void drawImageSpread(final ResourceLocation image, final float x, final float y, final float width, final float height, final float alpha) {
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, alpha);
        GuiRenderUtils.mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture((int)x, (int)y, 0.0f, 0.0f, (int)width, (int)height, 25.0f, 25.0f);
        GL11.glDepthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
        GlStateManager.resetColor();
    }
    
    public static void doGlScissor(final int x, final int y, final float width, final float height, final float scale) {
        int scaleFactor;
        for (scaleFactor = 1; scaleFactor < scale && GuiRenderUtils.mc.displayWidth / (scaleFactor + 1) >= 320 && GuiRenderUtils.mc.displayHeight / (scaleFactor + 1) >= 240; ++scaleFactor) {}
        GL11.glScissor(x * scaleFactor, (int)(GuiRenderUtils.mc.displayHeight - (y + height) * scaleFactor), (int)(width * scaleFactor), (int)(height * scaleFactor));
    }
    
    public static void drawLine3D(final double x1, final double y1, final double z1, final double x2, final double y2, final double z2, final int color) {
        drawLine3D(x1, y1, z1, x2, y2, z2, color, true);
    }
    
    public static void drawLine3D(final double x1, final double y1, final double z1, final double x2, final double y2, final double z2, final int color, final boolean disableDepth) {
        enableRender3D(disableDepth);
        setColor(color);
        GL11.glBegin(1);
        GL11.glVertex3d(x1, y1, z1);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glEnd();
        disableRender3D(disableDepth);
    }
    
    public static void drawLine2D(final double x1, final double y1, final double x2, final double y2, final float width, final int color) {
        enableRender2D();
        setColor(color);
        GL11.glLineWidth(width);
        GL11.glBegin(1);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        disableRender2D();
    }
    
    public static void drawPoint(final int x, final int y, final float size, final int color) {
        enableRender2D();
        setColor(color);
        GL11.glPointSize(size);
        GL11.glEnable(2832);
        GL11.glBegin(0);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glEnd();
        GL11.glDisable(2832);
        disableRender2D();
    }
    
    public static float getScaleFactor() {
        final ScaledResolution scaledResolution = new ScaledResolution(GuiRenderUtils.mc);
        return (float)scaledResolution.getScaleFactor();
    }
    
    public static void drawOutlinedBox(final AxisAlignedBB boundingBox, final int color) {
        drawOutlinedBox(boundingBox, color, true);
    }
    
    public static void drawOutlinedBox(final AxisAlignedBB boundingBox, final int color, final boolean disableDepth) {
        if (boundingBox != null) {
            enableRender3D(disableDepth);
            setColor(color);
            GL11.glBegin(3);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glEnd();
            GL11.glBegin(3);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glEnd();
            GL11.glBegin(1);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glEnd();
            disableRender3D(disableDepth);
        }
    }
    
    public static void drawBox(final AxisAlignedBB boundingBox, final int color) {
        drawBox(boundingBox, color, true);
    }
    
    public static void drawBox(final AxisAlignedBB boundingBox, final int color, final boolean disableDepth) {
        if (boundingBox != null) {
            enableRender3D(disableDepth);
            setColor(color);
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
            GL11.glEnd();
            disableRender3D(disableDepth);
        }
    }
    
    public static void enableRender3D(final boolean disableDepth) {
        if (disableDepth) {
            GL11.glDepthMask(false);
            GL11.glDisable(2929);
        }
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(1.0f);
    }
    
    public static void disableRender3D(final boolean enableDepth) {
        if (enableDepth) {
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
        }
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glDisable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void enableRender2D() {
        GL11.glEnable(3042);
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(1.0f);
    }
    
    public static void disableRender2D() {
        GL11.glDisable(3042);
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }
    
    public static void setColor(final int colorHex) {
        final float alpha = (colorHex >> 24 & 0xFF) / 255.0f;
        final float red = (colorHex >> 16 & 0xFF) / 255.0f;
        final float green = (colorHex >> 8 & 0xFF) / 255.0f;
        final float blue = (colorHex & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }
    
    public static void drawBorderedRect(final float x, final float y, final float width, final float height, final float borderWidth, final Color rectColor, final Color borderColor) {
        drawBorderedRect(x, y, width, height, borderWidth, rectColor.getRGB(), borderColor.getRGB());
    }
    
    public static void drawBorderedRect(final float x, final float y, final float width, final float height, final float borderWidth, final int rectColor, final int borderColor) {
        drawRect(x + borderWidth, y + borderWidth, width - borderWidth * 2.0f, height - borderWidth * 2.0f, rectColor);
        drawRect(x, y, width, borderWidth, borderColor);
        drawRect(x, y + borderWidth, borderWidth, height - borderWidth, borderColor);
        drawRect(x + width - borderWidth, y + borderWidth, borderWidth, height - borderWidth, borderColor);
        drawRect(x + borderWidth, y + height - borderWidth, width - borderWidth * 2.0f, borderWidth, borderColor);
    }
    
    public static void drawBorder(final float x, final float y, final float width, final float height, final float borderWidth, final int borderColor) {
        drawRect(x + borderWidth, y + borderWidth, width - borderWidth * 2.0f, borderWidth, borderColor);
        drawRect(x, y + borderWidth, borderWidth, height - borderWidth, borderColor);
        drawRect(x + width - borderWidth, y + borderWidth, borderWidth, height - borderWidth, borderColor);
        drawRect(x + borderWidth, y + height - borderWidth, width - borderWidth * 2.0f, borderWidth, borderColor);
    }
    
    public static void drawRect(final float x, final float y, final float width, final float height, final Color color) {
        drawRect(x, y, width, height, color.getRGB());
    }
    
    public static void drawRect(final float x, final float y, final float width, final float height, final int color) {
        enableRender2D();
        setColor(color);
        GL11.glBegin(7);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)(x + width), (double)y);
        GL11.glVertex2d((double)(x + width), (double)(y + height));
        GL11.glVertex2d((double)x, (double)(y + height));
        GL11.glEnd();
        disableRender2D();
    }
    
    public static void drawRoundedRect(final float x, final float y, final float width, final float height, float edgeRadius, int color, final float borderWidth, int borderColor) {
        if (color == 16777215) {
            color = 4095;
        }
        if (borderColor == 16777215) {
            borderColor = 4095;
        }
        if (edgeRadius < 0.0f) {
            edgeRadius = 0.0f;
        }
        if (edgeRadius > width / 2.0f) {
            edgeRadius = width / 2.0f;
        }
        if (edgeRadius > height / 2.0f) {
            edgeRadius = height / 2.0f;
        }
        drawRect(x + edgeRadius, y + edgeRadius, width - edgeRadius * 2.0f, height - edgeRadius * 2.0f, color);
        drawRect(x + edgeRadius, y, width - edgeRadius * 2.0f, edgeRadius, color);
        drawRect(x + edgeRadius, y + height - edgeRadius, width - edgeRadius * 2.0f, edgeRadius, color);
        drawRect(x, y + edgeRadius, edgeRadius, height - edgeRadius * 2.0f, color);
        drawRect(x + width - edgeRadius, y + edgeRadius, edgeRadius, height - edgeRadius * 2.0f, color);
        enableRender2D();
        RenderUtil.color(color);
        GL11.glBegin(6);
        float centerX = x + edgeRadius;
        float centerY = y + edgeRadius;
        GL11.glVertex2d((double)centerX, (double)centerY);
        for (int vertices = (int)Math.min(Math.max(edgeRadius, 10.0f), 90.0f), i = 0; i < vertices + 1; ++i) {
            final double angleRadians = 6.283185307179586 * (i + 180) / (vertices * 4);
            GL11.glVertex2d(centerX + Math.sin(angleRadians) * edgeRadius, centerY + Math.cos(angleRadians) * edgeRadius);
        }
        GL11.glEnd();
        GL11.glBegin(6);
        centerX = x + width - edgeRadius;
        centerY = y + edgeRadius;
        GL11.glVertex2d((double)centerX, (double)centerY);
        for (int vertices = (int)Math.min(Math.max(edgeRadius, 10.0f), 90.0f), i = 0; i < vertices + 1; ++i) {
            final double angleRadians = 6.283185307179586 * (i + 90) / (vertices * 4);
            GL11.glVertex2d(centerX + Math.sin(angleRadians) * edgeRadius, centerY + Math.cos(angleRadians) * edgeRadius);
        }
        GL11.glEnd();
        GL11.glBegin(6);
        centerX = x + edgeRadius;
        centerY = y + height - edgeRadius;
        GL11.glVertex2d((double)centerX, (double)centerY);
        for (int vertices = (int)Math.min(Math.max(edgeRadius, 10.0f), 90.0f), i = 0; i < vertices + 1; ++i) {
            final double angleRadians = 6.283185307179586 * (i + 270) / (vertices * 4);
            GL11.glVertex2d(centerX + Math.sin(angleRadians) * edgeRadius, centerY + Math.cos(angleRadians) * edgeRadius);
        }
        GL11.glEnd();
        GL11.glBegin(6);
        centerX = x + width - edgeRadius;
        centerY = y + height - edgeRadius;
        GL11.glVertex2d((double)centerX, (double)centerY);
        for (int vertices = (int)Math.min(Math.max(edgeRadius, 10.0f), 90.0f), i = 0; i < vertices + 1; ++i) {
            final double angleRadians = 6.283185307179586 * i / (vertices * 4);
            GL11.glVertex2d(centerX + Math.sin(angleRadians) * edgeRadius, centerY + Math.cos(angleRadians) * edgeRadius);
        }
        GL11.glEnd();
        RenderUtil.color(borderColor);
        GL11.glLineWidth(borderWidth);
        GL11.glBegin(3);
        centerX = x + edgeRadius;
        centerY = y + edgeRadius;
        int vertices;
        int i;
        for (vertices = (i = (int)Math.min(Math.max(edgeRadius, 10.0f), 90.0f)); i >= 0; --i) {
            final double angleRadians = 6.283185307179586 * (i + 180) / (vertices * 4);
            GL11.glVertex2d(centerX + Math.sin(angleRadians) * edgeRadius, centerY + Math.cos(angleRadians) * edgeRadius);
        }
        GL11.glVertex2d((double)(x + edgeRadius), (double)y);
        GL11.glVertex2d((double)(x + width - edgeRadius), (double)y);
        centerX = x + width - edgeRadius;
        centerY = y + edgeRadius;
        for (i = vertices; i >= 0; --i) {
            final double angleRadians = 6.283185307179586 * (i + 90) / (vertices * 4);
            GL11.glVertex2d(centerX + Math.sin(angleRadians) * edgeRadius, centerY + Math.cos(angleRadians) * edgeRadius);
        }
        GL11.glVertex2d((double)(x + width), (double)(y + edgeRadius));
        GL11.glVertex2d((double)(x + width), (double)(y + height - edgeRadius));
        centerX = x + width - edgeRadius;
        centerY = y + height - edgeRadius;
        for (i = vertices; i >= 0; --i) {
            final double angleRadians = 6.283185307179586 * i / (vertices * 4);
            GL11.glVertex2d(centerX + Math.sin(angleRadians) * edgeRadius, centerY + Math.cos(angleRadians) * edgeRadius);
        }
        GL11.glVertex2d((double)(x + width - edgeRadius), (double)(y + height));
        GL11.glVertex2d((double)(x + edgeRadius), (double)(y + height));
        centerX = x + edgeRadius;
        centerY = y + height - edgeRadius;
        for (i = vertices; i >= 0; --i) {
            final double angleRadians = 6.283185307179586 * (i + 270) / (vertices * 4);
            GL11.glVertex2d(centerX + Math.sin(angleRadians) * edgeRadius, centerY + Math.cos(angleRadians) * edgeRadius);
        }
        GL11.glVertex2d((double)x, (double)(y + height - edgeRadius));
        GL11.glVertex2d((double)x, (double)(y + edgeRadius));
        GL11.glEnd();
        disableRender2D();
    }
    
    public static int getDisplayWidth() {
        final ScaledResolution scaledResolution = new ScaledResolution(GuiRenderUtils.mc);
        final int displayWidth = scaledResolution.getScaledWidth();
        return displayWidth;
    }
    
    public static int getDisplayHeight() {
        final ScaledResolution scaledResolution = new ScaledResolution(GuiRenderUtils.mc);
        final int displayHeight = scaledResolution.getScaledHeight();
        return displayHeight;
    }
    
    public static void drawCircle(final float x, final float y, final float radius, final float lineWidth, final int color) {
        enableRender2D();
        setColor(color);
        GL11.glLineWidth(lineWidth);
        final int vertices = (int)Math.min(Math.max(radius, 45.0f), 360.0f);
        GL11.glBegin(2);
        for (int i = 0; i < vertices; ++i) {
            final double angleRadians = 6.283185307179586 * i / vertices;
            GL11.glVertex2d(x + Math.sin(angleRadians) * radius, y + Math.cos(angleRadians) * radius);
        }
        GL11.glEnd();
        disableRender2D();
    }
    
    public static void drawFilledCircle(final float x, final float y, final float radius, final int color) {
        enableRender2D();
        setColor(color);
        final int vertices = (int)Math.min(Math.max(radius, 45.0f), 360.0f);
        GL11.glBegin(9);
        for (int i = 0; i < vertices; ++i) {
            final double angleRadians = 6.283185307179586 * i / vertices;
            GL11.glVertex2d(x + Math.sin(angleRadians) * radius, y + Math.cos(angleRadians) * radius);
        }
        GL11.glEnd();
        disableRender2D();
        drawCircle(x, y, radius, 1.5f, 16777215);
    }
    
    public static void drawFilledCircleNoBorder(final float x, final float y, final float radius, final int color) {
        enableRender2D();
        setColor(color);
        final int vertices = (int)Math.min(Math.max(radius, 45.0f), 360.0f);
        GL11.glBegin(9);
        for (int i = 0; i < vertices; ++i) {
            final double angleRadians = 6.283185307179586 * i / vertices;
            GL11.glVertex2d(x + Math.sin(angleRadians) * radius, y + Math.cos(angleRadians) * radius);
        }
        GL11.glEnd();
        disableRender2D();
    }
    
    public static int darker(final int hexColor, final int factor) {
        final float alpha = (float)(hexColor >> 24 & 0xFF);
        final float red = Math.max((hexColor >> 16 & 0xFF) - (hexColor >> 16 & 0xFF) / (100.0f / factor), 0.0f);
        final float green = Math.max((hexColor >> 8 & 0xFF) - (hexColor >> 8 & 0xFF) / (100.0f / factor), 0.0f);
        final float blue = Math.max((hexColor & 0xFF) - (hexColor & 0xFF) / (100.0f / factor), 0.0f);
        return (int)(((int)alpha << 24) + ((int)red << 16) + ((int)green << 8) + blue);
    }
    
    public static int opacity(final int hexColor, final int factor) {
        final float alpha = Math.max((hexColor >> 24 & 0xFF) - (hexColor >> 24 & 0xFF) / (100.0f / factor), 0.0f);
        final float red = (float)(hexColor >> 16 & 0xFF);
        final float green = (float)(hexColor >> 8 & 0xFF);
        final float blue = (float)(hexColor & 0xFF);
        return (int)(((int)alpha << 24) + ((int)red << 16) + ((int)green << 8) + blue);
    }
    
    static {
        GuiRenderUtils.mc = Minecraft.getMinecraft();
    }
}
