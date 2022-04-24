// L Bape, Decompiled by ImCzf233

package mc.bape.utils;

import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;

public class RenderUtil
{
    public static Minecraft mc;
    public static float delta;
    
    public static void drawRoundedRect(final float left, final float top, final float right, final float bottom, final int color) {
        drawRect(left - 0.5f, top + 0.5f, left, bottom - 0.5f, color);
        drawRect(right, top + 0.5f, right + 0.5f, bottom - 0.5f, color);
        drawRect(left + 0.5f, top - 0.5f, right - 0.5f, top, color);
        drawRect(left + 0.5f, bottom, right - 0.5f, bottom + 0.5f, color);
        drawRect(left, top, right, bottom, color);
    }
    
    public static void drawBorderedRect(final float left, final float top, final float right, final float bottom, final float thickness, final int color) {
        drawRect(left - thickness, top, left, bottom, color);
        drawRect(right, top, right + thickness, bottom, color);
        drawRect(left, top + thickness, right, top, color);
        drawRect(left, bottom, right, bottom + thickness, color);
    }
    
    public static void drawImage(final ResourceLocation image, final int x, final int y, final float width, final float height, final float alpha) {
        GL11.glPushMatrix();
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0f, 0.0f, 0.0f, alpha);
        RenderUtil.mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, (int)width, (int)height, width, height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static int reAlpha(final int color, final float alpha) {
        try {
            final Color c = new Color(color);
            final float r = 0.003921569f * c.getRed();
            final float g = 0.003921569f * c.getGreen();
            final float b = 0.003921569f * c.getBlue();
            return new Color(r, g, b, alpha).getRGB();
        }
        catch (Throwable e) {
            e.printStackTrace();
            return color;
        }
    }
    
    public static boolean isHovering(final float mouseX, final float mouseY, final float xLeft, final float yUp, final float xRight, final float yBottom) {
        return mouseX > xLeft && mouseX < xRight && mouseY > yUp && mouseY < yBottom;
    }
    
    public static boolean isHoveringBound(final float mouseX, final float mouseY, final float xLeft, final float yUp, final float width, final float height) {
        return mouseX > xLeft && mouseX < xLeft + width && mouseY > yUp && mouseY < yUp + height;
    }
    
    public static void drawRoundedRect(float n, float n2, float n3, float n4, final int n5, final int n6) {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawVLine(n *= 2.0f, (n2 *= 2.0f) + 1.0f, (n4 *= 2.0f) - 2.0f, n5);
        drawVLine((n3 *= 2.0f) - 1.0f, n2 + 1.0f, n4 - 2.0f, n5);
        drawHLine(n + 2.0f, n3 - 3.0f, n2, n5);
        drawHLine(n + 2.0f, n3 - 3.0f, n4 - 1.0f, n5);
        drawHLine(n + 1.0f, n + 1.0f, n2 + 1.0f, n5);
        drawHLine(n3 - 2.0f, n3 - 2.0f, n2 + 1.0f, n5);
        drawHLine(n3 - 2.0f, n3 - 2.0f, n4 - 2.0f, n5);
        drawHLine(n + 1.0f, n + 1.0f, n4 - 2.0f, n5);
        drawRect(n + 1.0f, n2 + 1.0f, n3 - 1.0f, n4 - 1.0f, n6);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public static void drawRoundRect(final float x, final float y, final float x1, final float y1, final int color) {
        drawRoundedRect(x, y, x1, y1, color, color);
        GlStateManager.color(1.0f, 1.0f, 1.0f);
    }
    
    public static void drawHLine(float x, float y, final float right, final int bottom) {
        if (y < x) {
            final float var5 = x;
            x = y;
            y = var5;
        }
        drawRect(x, right, y + 1.0f, right + 1.0f, bottom);
    }
    
    public static void drawVLine(final float x, float y, float right, final int bottom) {
        if (right < y) {
            final float var5 = y;
            y = right;
            right = var5;
        }
        drawRect(x, y + 1.0f, x + 1.0f, right, bottom);
    }
    
    public static void drawRect(float left, float top, float right, float bottom, final int color) {
        if (left < right) {
            final float e = left;
            left = right;
            right = e;
        }
        if (top < bottom) {
            final float e = top;
            top = bottom;
            bottom = e;
        }
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float b = (color >> 16 & 0xFF) / 255.0f;
        final float c = (color >> 8 & 0xFF) / 255.0f;
        final float d = (color & 0xFF) / 255.0f;
        final WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(b, c, d, a);
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos((double)left, (double)bottom, 0.0).endVertex();
        worldRenderer.pos((double)right, (double)bottom, 0.0).endVertex();
        worldRenderer.pos((double)right, (double)top, 0.0).endVertex();
        worldRenderer.pos((double)left, (double)top, 0.0).endVertex();
        Tessellator.getInstance().draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void drawOutlinedRect(final float x, final float y, final float width, final float height, final float lineSize, final int lineColor) {
        drawRect(x, y, width, y + lineSize, lineColor);
        drawRect(x, height - lineSize, width, height, lineColor);
        drawRect(x, y + lineSize, x + lineSize, height - lineSize, lineColor);
        drawRect(width - lineSize, y + lineSize, width, height - lineSize, lineColor);
    }
    
    public static void drawFastRoundedRect(final int left, final float top, final int right, final float bottom, final float radius, final int color) {
        final int semicircle = 18;
        final float f = 5.0f;
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        color(color);
        GL11.glBegin(5);
        GL11.glVertex2f(left + radius, top);
        GL11.glVertex2f(left + radius, bottom);
        GL11.glVertex2f(right - radius, top);
        GL11.glVertex2f(right - radius, bottom);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f((float)left, top + radius);
        GL11.glVertex2f(left + radius, top + radius);
        GL11.glVertex2f((float)left, bottom - radius);
        GL11.glVertex2f(left + radius, bottom - radius);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f((float)right, top + radius);
        GL11.glVertex2f(right - radius, top + radius);
        GL11.glVertex2f((float)right, bottom - radius);
        GL11.glVertex2f(right - radius, bottom - radius);
        GL11.glEnd();
        GL11.glBegin(6);
        float f2 = right - radius;
        float f3 = top + radius;
        GL11.glVertex2f(f2, f3);
        int j;
        float f4;
        for (j = 0, j = 0; j <= 18; ++j) {
            f4 = j * 5.0f;
            GL11.glVertex2f((float)(f2 + radius * Math.cos(Math.toRadians(f4))), (float)(f3 - radius * Math.sin(Math.toRadians(f4))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f2 = left + radius;
        f3 = top + radius;
        GL11.glVertex2f(f2, f3);
        for (j = 0; j <= 18; ++j) {
            final float f5 = j * 5.0f;
            GL11.glVertex2f((float)(f2 - radius * Math.cos(Math.toRadians(f5))), (float)(f3 - radius * Math.sin(Math.toRadians(f5))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f2 = left + radius;
        f3 = bottom - radius;
        GL11.glVertex2f(f2, f3);
        for (j = 0; j <= 18; ++j) {
            final float f6 = j * 5.0f;
            GL11.glVertex2f((float)(f2 - radius * Math.cos(Math.toRadians(f6))), (float)(f3 + radius * Math.sin(Math.toRadians(f6))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        f2 = right - radius;
        f3 = bottom - radius;
        GL11.glVertex2f(f2, f3);
        for (j = 0; j <= 18; ++j) {
            final float f7 = j * 5.0f;
            GL11.glVertex2f((float)(f2 + radius * Math.cos(Math.toRadians(f7))), (float)(f3 + radius * Math.sin(Math.toRadians(f7))));
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(3042);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static int width() {
        return new ScaledResolution(RenderUtil.mc).getScaledWidth();
    }
    
    public static int height() {
        return new ScaledResolution(RenderUtil.mc).getScaledHeight();
    }
    
    public static void drawRoundedRect(float x, float y, float x2, float y2, final float round, final int color) {
        x += (float)(round / 2.0f + 0.5);
        y += (float)(round / 2.0f + 0.5);
        x2 -= (float)(round / 2.0f + 0.5);
        y2 -= (float)(round / 2.0f + 0.5);
        drawRect(x, y, x2, y2, color);
        circle(x2 - round / 2.0f, y + round / 2.0f, round, color);
        circle(x + round / 2.0f, y2 - round / 2.0f, round, color);
        circle(x + round / 2.0f, y + round / 2.0f, round, color);
        circle(x2 - round / 2.0f, y2 - round / 2.0f, round, color);
        drawRect(x - round / 2.0f - 0.5f, y + round / 2.0f, x2, y2 - round / 2.0f, color);
        drawRect(x, y + round / 2.0f, x2 + round / 2.0f + 0.5f, y2 - round / 2.0f, color);
        drawRect(x + round / 2.0f, y - round / 2.0f - 0.5f, x2 - round / 2.0f, y2 - round / 2.0f, color);
        drawRect(x + round / 2.0f, y, x2 - round / 2.0f, y2 + round / 2.0f + 0.5f, color);
    }
    
    public static void circle(final float x, final float y, final float radius, final int fill) {
        arc(x, y, 0.0f, 360.0f, radius, fill);
    }
    
    public static void arc(final float x, final float y, final float start, final float end, final float radius, final int color) {
        arcEllipse(x, y, start, end, radius, radius, color);
    }
    
    public static void color(final int color) {
        final float f = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, f);
    }
    
    public static void arcEllipse(final float x, final float y, float start, float end, final float w, final float h, final int color) {
        GlStateManager.color(0.0f, 0.0f, 0.0f);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        float temp = 0.0f;
        if (start > end) {
            temp = end;
            end = start;
            start = temp;
        }
        final float var11 = (color >> 24 & 0xFF) / 255.0f;
        final float var12 = (color >> 16 & 0xFF) / 255.0f;
        final float var13 = (color >> 8 & 0xFF) / 255.0f;
        final float var14 = (color & 0xFF) / 255.0f;
        final Tessellator var15 = Tessellator.getInstance();
        final WorldRenderer var16 = var15.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var12, var13, var14, var11);
        if (var11 > 0.5f) {
            GL11.glEnable(2848);
            GL11.glLineWidth(2.0f);
            GL11.glBegin(3);
            for (float i = end; i >= start; i -= 4.0f) {
                final float ldx = (float)Math.cos(i * 3.141592653589793 / 180.0) * (w * 1.001f);
                final float ldy = (float)Math.sin(i * 3.141592653589793 / 180.0) * (h * 1.001f);
                GL11.glVertex2f(x + ldx, y + ldy);
            }
            GL11.glEnd();
            GL11.glDisable(2848);
        }
        GL11.glBegin(6);
        for (float i = end; i >= start; i -= 4.0f) {
            final float ldx = (float)Math.cos(i * 3.141592653589793 / 180.0) * w;
            final float ldy = (float)Math.sin(i * 3.141592653589793 / 180.0) * h;
            GL11.glVertex2f(x + ldx, y + ldy);
        }
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawCircle(final float x, final float y, final int start, final int end, final float radius, final int color) {
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glEnable(2881);
        GL11.glBlendFunc(770, 771);
        color(color);
        GL11.glBegin(9);
        for (int i = start; i <= end; ++i) {
            final double x2 = Math.sin(i * 3.141592653589793 / 180.0) * radius;
            final double y2 = Math.cos(i * 3.141592653589793 / 180.0) * radius;
            GL11.glVertex3d(x + x2, y + y2, 0.0);
        }
        GL11.glEnd();
        GL11.glDisable(2881);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawCircleWithTexture(final float cX, final float cY, final int start, final int end, final float radius, final ResourceLocation res, final int color) {
        GL11.glPushMatrix();
        GL11.glEnable(3553);
        RenderUtil.mc.getTextureManager().bindTexture(res);
        GL11.glEnable(2881);
        GL11.glBlendFunc(770, 771);
        color(color);
        GL11.glBegin(9);
        for (int i = start; i < end; ++i) {
            final double radian = i * 0.017453292519943295;
            final double xsin = Math.sin(radian);
            final double ycos = Math.cos(radian);
            final double x = xsin * radius;
            final double y = ycos * radius;
            final double tx = xsin * 0.5 + 0.5;
            final double ty = ycos * 0.5 + 0.5;
            GL11.glTexCoord2d(cX + tx, cY + ty);
            GL11.glVertex2d(cX + x, cY + y);
        }
        GL11.glEnd();
        GL11.glDisable(2881);
        GL11.glDisable(3553);
        GL11.glPopMatrix();
    }
    
    public static void layeredRect(final float right, final float bottom, final float x2, final float y2, final int outline, final int inline, final int background) {
        drawRect(right, bottom, x2, y2, outline);
        drawRect(right + 0.5f, bottom + 0.5f, x2 - 0.5f, y2 - 0.5f, inline);
        drawRect(right + 1.0f, bottom + 1.0f, x2 - 1.0f, y2 - 1.0f, background);
    }
    
    public static void drawOutlinedBoundingBox(final AxisAlignedBB aa) {
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(1, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        tessellator.draw();
    }
    
    public static void drawBoundingBox(final AxisAlignedBB aa) {
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        tessellator.draw();
    }
    
    public static void drawEntityESP(final double x, final double y, final double z, final double width, final double height, final float red, final float green, final float blue, final float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1.0f);
        GL11.glColor4f(red, green, blue, 1.0f);
        drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawEntityESP(final double x, final double y, final double z, final double x1, final double y1, final double z1, final float red, final float green, final float blue, final float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1.0f);
        GL11.glColor4f(red, green, blue, 1.0f);
        drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x1, y1, z1));
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(new AxisAlignedBB(x, y, z, x1, y1, z1));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawEntityESP(final AxisAlignedBB axisAlignedBB, final float red, final float green, final float blue, final float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1.0f);
        GL11.glColor4f(red, green, blue, 1.0f);
        drawOutlinedBoundingBox(axisAlignedBB);
        GL11.glColor4f(red, green, blue, alpha);
        drawBoundingBox(axisAlignedBB);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void doGlScissor(final float x, final float y, final float windowWidth2, final float windowHeight2) {
        int scaleFactor = 1;
        float k = (float)RenderUtil.mc.gameSettings.guiScale;
        if (k == 0.0f) {
            k = 1000.0f;
        }
        while (scaleFactor < k && RenderUtil.mc.displayWidth / (scaleFactor + 1) >= 320 && RenderUtil.mc.displayHeight / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        GL11.glScissor((int)(x * scaleFactor), (int)(RenderUtil.mc.displayHeight - (y + windowHeight2) * scaleFactor), (int)(windowWidth2 * scaleFactor), (int)(windowHeight2 * scaleFactor));
    }
    
    public static void glColor(final float alpha, final int redRGB, final int greenRGB, final int blueRGB) {
        final float red = 0.003921569f * redRGB;
        final float green = 0.003921569f * greenRGB;
        final float blue = 0.003921569f * blueRGB;
        GL11.glColor4f(red, green, blue, alpha);
    }
    
    public static void drawGradientRect(final float x, final float y, final float x1, final float y1, final int topColor, final int bottomColor) {
        R2DUtils.enableGL2D();
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        glColor(topColor);
        GL11.glVertex2f(x, y1);
        GL11.glVertex2f(x1, y1);
        glColor(bottomColor);
        GL11.glVertex2f(x1, y);
        GL11.glVertex2f(x, y);
        GL11.glEnd();
        GL11.glShadeModel(7424);
        R2DUtils.disableGL2D();
    }
    
    public static void glColor(final int hex) {
        final float alpha = (hex >> 24 & 0xFF) / 255.0f;
        final float red = (hex >> 16 & 0xFF) / 255.0f;
        final float green = (hex >> 8 & 0xFF) / 255.0f;
        final float blue = (hex & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }
    
    static {
        RenderUtil.mc = Minecraft.getMinecraft();
    }
    
    public static class R2DUtils
    {
        public static void enableGL2D() {
            GL11.glDisable(2929);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glBlendFunc(770, 771);
            GL11.glDepthMask(true);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glHint(3155, 4354);
        }
        
        public static void disableGL2D() {
            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glHint(3154, 4352);
            GL11.glHint(3155, 4352);
        }
        
        public static void drawRoundedRect(float x, float y, float x1, float y1, final int borderC, final int insideC) {
            enableGL2D();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            drawVLine(x *= 2.0f, (y *= 2.0f) + 1.0f, (y1 *= 2.0f) - 2.0f, borderC);
            drawVLine((x1 *= 2.0f) - 1.0f, y + 1.0f, y1 - 2.0f, borderC);
            drawHLine(x + 2.0f, x1 - 3.0f, y, borderC);
            drawHLine(x + 2.0f, x1 - 3.0f, y1 - 1.0f, borderC);
            drawHLine(x + 1.0f, x + 1.0f, y + 1.0f, borderC);
            drawHLine(x1 - 2.0f, x1 - 2.0f, y + 1.0f, borderC);
            drawHLine(x1 - 2.0f, x1 - 2.0f, y1 - 2.0f, borderC);
            drawHLine(x + 1.0f, x + 1.0f, y1 - 2.0f, borderC);
            drawRect(x + 1.0f, y + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
            GL11.glScalef(2.0f, 2.0f, 2.0f);
            disableGL2D();
            Gui.drawRect(0, 0, 0, 0, 0);
        }
        
        public static void drawRect(final double x2, final double y2, final double x1, final double y1, final int color) {
            enableGL2D();
            glColor(color, color, color, color);
            drawRect(x2, y2, x1, y1);
            disableGL2D();
        }
        
        private static void glColor(final int color, final int color1, final int color2, final int color3) {
        }
        
        private static void drawRect(final double x2, final double y2, final double x1, final double y1) {
            GL11.glBegin(7);
            GL11.glVertex2d(x2, y1);
            GL11.glVertex2d(x1, y1);
            GL11.glVertex2d(x1, y2);
            GL11.glVertex2d(x2, y2);
            GL11.glEnd();
        }
        
        public static void drawHLine(float x, float y, final float x1, final int y1) {
            if (y < x) {
                final float var5 = x;
                x = y;
                y = var5;
            }
            drawRect(x, x1, y + 1.0f, x1 + 1.0f, y1);
        }
        
        public static void drawVLine(final float x, float y, float x1, final int y1) {
            if (x1 < y) {
                final float var5 = y;
                y = x1;
                x1 = var5;
            }
            drawRect(x, y + 1.0f, x + 1.0f, x1, y1);
        }
        
        public static void drawHLine(float x, float y, final float x1, final int y1, final int y2) {
            if (y < x) {
                final float var5 = x;
                x = y;
                y = var5;
            }
            drawGradientRect(x, x1, y + 1.0f, x1 + 1.0f, y1, y2);
        }
        
        public static void drawGradientRect(final float x, final float y, final float x1, final float y1, final int topColor, final int bottomColor) {
            enableGL2D();
            GL11.glShadeModel(7425);
            GL11.glBegin(7);
            RenderUtil.glColor(topColor);
            GL11.glVertex2f(x, y1);
            GL11.glVertex2f(x1, y1);
            RenderUtil.glColor(bottomColor);
            GL11.glVertex2f(x1, y);
            GL11.glVertex2f(x, y);
            GL11.glEnd();
            GL11.glShadeModel(7424);
            disableGL2D();
        }
        
        public static void glColor(final int hex) {
            final float alpha = (hex >> 24 & 0xFF) / 255.0f;
            final float red = (hex >> 16 & 0xFF) / 255.0f;
            final float green = (hex >> 8 & 0xFF) / 255.0f;
            final float blue = (hex & 0xFF) / 255.0f;
            GL11.glColor4f(red, green, blue, alpha);
        }
    }
}
