// L Bape, Decompiled by ImCzf233

package mc.bape.vapu;

import org.lwjgl.opengl.*;
import org.apache.logging.log4j.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;

public class Render
{
    public static double zDepth;
    public static final double circleSteps = 30.0;
    
    public static void setColourWithAlphaPercent(final int colour, final int alphaPercent) {
        setColour((alphaPercent * 255 / 100 & 0xFF) << 24 | (colour & 0xFFFFFF));
    }
    
    public static void setColour(final int colour) {
        GL11.glColor4f((colour >> 16 & 0xFF) / 255.0f, (colour >> 8 & 0xFF) / 255.0f, (colour & 0xFF) / 255.0f, (colour >> 24 & 0xFF) / 255.0f);
    }
    
    public static void resetColour() {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static int multiplyColours(final int c1, final int c2) {
        final float c1A = (float)(c1 >> 24 & 0xFF);
        final float c1R = (float)(c1 >> 16 & 0xFF);
        final float c1G = (float)(c1 >> 8 & 0xFF);
        final float c1B = (float)(c1 >> 0 & 0xFF);
        final float c2A = (float)(c2 >> 24 & 0xFF);
        final float c2R = (float)(c2 >> 16 & 0xFF);
        final float c2G = (float)(c2 >> 8 & 0xFF);
        final float c2B = (float)(c2 >> 0 & 0xFF);
        final int r = (int)(c1R * c2R / 255.0f) & 0xFF;
        final int g = (int)(c1G * c2G / 255.0f) & 0xFF;
        final int b = (int)(c1B * c2B / 255.0f) & 0xFF;
        final int a = (int)(c1A * c2A / 255.0f) & 0xFF;
        return a << 24 | r << 16 | g << 8 | b;
    }
    
    public static int getAverageOfPixelQuad(final int[] pixels, final int offset, final int scanSize) {
        final int p00 = pixels[offset];
        final int p2 = pixels[offset + 1];
        final int p3 = pixels[offset + scanSize];
        final int p4 = pixels[offset + scanSize + 1];
        int r = (p00 >> 16 & 0xFF) + (p2 >> 16 & 0xFF) + (p3 >> 16 & 0xFF) + (p4 >> 16 & 0xFF);
        r >>= 2;
        int g = (p00 >> 8 & 0xFF) + (p2 >> 8 & 0xFF) + (p3 >> 8 & 0xFF) + (p4 >> 8 & 0xFF);
        g >>= 2;
        int b = (p00 & 0xFF) + (p2 & 0xFF) + (p3 & 0xFF) + (p4 & 0xFF);
        b >>= 2;
        return 0xFF000000 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | (b & 0xFF);
    }
    
    public static int getAverageColourOfArray(final int[] pixels) {
        int count = 0;
        double totalA = 0.0;
        double totalR = 0.0;
        double totalG = 0.0;
        double totalB = 0.0;
        for (final int pixel : pixels) {
            final double a = pixel >> 24 & 0xFF;
            final double r = pixel >> 16 & 0xFF;
            final double g = pixel >> 8 & 0xFF;
            final double b = pixel >> 0 & 0xFF;
            totalA += a;
            totalR += r * a / 255.0;
            totalG += g * a / 255.0;
            totalB += b * a / 255.0;
            ++count;
        }
        totalR = totalR * 255.0 / totalA;
        totalG = totalG * 255.0 / totalA;
        totalB = totalB * 255.0 / totalA;
        totalA /= count;
        return ((int)totalA & 0xFF) << 24 | ((int)totalR & 0xFF) << 16 | ((int)totalG & 0xFF) << 8 | ((int)totalB & 0xFF);
    }
    
    public static int adjustPixelBrightness(final int colour, final int brightness) {
        int r = colour >> 16 & 0xFF;
        int g = colour >> 8 & 0xFF;
        int b = colour >> 0 & 0xFF;
        r = Math.min(Math.max(0, r + brightness), 255);
        g = Math.min(Math.max(0, g + brightness), 255);
        b = Math.min(Math.max(0, b + brightness), 255);
        return (colour & 0xFF000000) | r << 16 | g << 8 | b;
    }
    
    public static int getTextureWidth() {
        return GL11.glGetTexLevelParameteri(3553, 0, 4096);
    }
    
    public static int getTextureHeight() {
        return GL11.glGetTexLevelParameteri(3553, 0, 4097);
    }
    
    public static int getBoundTextureId() {
        return GL11.glGetInteger(32873);
    }
    
    public static void printBoundTextureInfo(final int texture) {
        final int w = getTextureWidth();
        final int h = getTextureHeight();
        final int depth = GL11.glGetTexLevelParameteri(3553, 0, 32881);
        final int format = GL11.glGetTexLevelParameteri(3553, 0, 4099);
        LogManager.getLogger().log(Level.INFO, "texture %d parameters: width=%d, height=%d, depth=%d, format=%08x", new Object[] { texture, w, h, depth, format });
    }
    
    public static int getMaxTextureSize() {
        return GL11.glGetInteger(3379);
    }
    
    public static void drawTexturedRect(final double x, final double y, final double w, final double h) {
        drawTexturedRect(x, y, w, h, 0.0, 0.0, 1.0, 1.0);
    }
    
    public static void drawTexturedRect(final double x, final double y, final double w, final double h, final double u1, final double v1, final double u2, final double v2) {
        try {
            GL11.glEnable(3553);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            final Tessellator tes = Tessellator.getInstance();
            final WorldRenderer worlderRenderer = tes.getWorldRenderer();
            worlderRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
            worlderRenderer.pos(x + w, y, Render.zDepth).tex(u2, v1).endVertex();
            worlderRenderer.pos(x, y, Render.zDepth).tex(u1, v1).endVertex();
            worlderRenderer.pos(x, y + h, Render.zDepth).tex(u1, v2).endVertex();
            worlderRenderer.pos(x + w, y + h, Render.zDepth).tex(u2, v2).endVertex();
            tes.draw();
            GL11.glDisable(3042);
        }
        catch (NullPointerException e) {
            LogManager.getLogger().log(Level.INFO, "MwRender.drawTexturedRect: null pointer exception");
        }
    }
    
    public static void drawArrow(final double x, final double y, final double angle, final double length) {
        final double arrowBackAngle = 2.356194490192345;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        final Tessellator tes = Tessellator.getInstance();
        final WorldRenderer worlderRenderer = tes.getWorldRenderer();
        worlderRenderer.begin(6, DefaultVertexFormats.POSITION);
        worlderRenderer.pos(x + length * Math.cos(angle), y + length * Math.sin(angle), Render.zDepth).endVertex();
        worlderRenderer.pos(x + length * 0.5 * Math.cos(angle - arrowBackAngle), y + length * 0.5 * Math.sin(angle - arrowBackAngle), Render.zDepth).endVertex();
        worlderRenderer.pos(x + length * 0.3 * Math.cos(angle + 3.141592653589793), y + length * 0.3 * Math.sin(angle + 3.141592653589793), Render.zDepth).endVertex();
        worlderRenderer.pos(x + length * 0.5 * Math.cos(angle + arrowBackAngle), y + length * 0.5 * Math.sin(angle + arrowBackAngle), Render.zDepth).endVertex();
        tes.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawTriangle(final double x1, final double y1, final double x2, final double y2, final double x3, final double y3) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        final Tessellator tes = Tessellator.getInstance();
        final WorldRenderer worlderRenderer = tes.getWorldRenderer();
        worlderRenderer.begin(4, DefaultVertexFormats.POSITION);
        worlderRenderer.pos(x1, y1, Render.zDepth).endVertex();
        worlderRenderer.pos(x2, y2, Render.zDepth).endVertex();
        worlderRenderer.pos(x3, y3, Render.zDepth).endVertex();
        tes.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawRect(final double x, final double y, final double w, final double h) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        final Tessellator tes = Tessellator.getInstance();
        final WorldRenderer worlderRenderer = tes.getWorldRenderer();
        worlderRenderer.begin(7, DefaultVertexFormats.POSITION);
        worlderRenderer.pos(x + w, y, Render.zDepth).endVertex();
        worlderRenderer.pos(x, y, Render.zDepth).endVertex();
        worlderRenderer.pos(x, y + h, Render.zDepth).endVertex();
        worlderRenderer.pos(x + w, y + h, Render.zDepth).endVertex();
        tes.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawCircle(final double x, final double y, final double r) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        final Tessellator tes = Tessellator.getInstance();
        final WorldRenderer worlderRenderer = tes.getWorldRenderer();
        worlderRenderer.begin(6, DefaultVertexFormats.POSITION);
        worlderRenderer.pos(x, y, Render.zDepth);
        for (double end = 6.283185307179586, incr = end / 30.0, theta = -incr; theta < end; theta += incr) {
            worlderRenderer.pos(x + r * Math.cos(-theta), y + r * Math.sin(-theta), Render.zDepth).endVertex();
        }
        tes.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawCircleBorder(final double x, final double y, final double r, final double width) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        final Tessellator tes = Tessellator.getInstance();
        final WorldRenderer worlderRenderer = tes.getWorldRenderer();
        worlderRenderer.begin(5, DefaultVertexFormats.POSITION);
        final double end = 6.283185307179586;
        final double incr = end / 30.0;
        final double r2 = r + width;
        for (double theta = -incr; theta < end; theta += incr) {
            worlderRenderer.pos(x + r * Math.cos(-theta), y + r * Math.sin(-theta), Render.zDepth).endVertex();
            worlderRenderer.pos(x + r2 * Math.cos(-theta), y + r2 * Math.sin(-theta), Render.zDepth).endVertex();
        }
        tes.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawRectBorder(final double x, final double y, final double w, final double h, final double bw) {
        drawRect(x - bw, y - bw, w + bw + bw, bw);
        drawRect(x - bw, y + h, w + bw + bw, bw);
        drawRect(x - bw, y, bw, h);
        drawRect(x + w, y, bw, h);
    }
    
    public static void drawString(final int x, final int y, final int colour, final String formatString, final Object... args) {
        final Minecraft mc = Minecraft.getMinecraft();
        final FontRenderer fr = mc.fontRendererObj;
        final String s = String.format(formatString, args);
        fr.drawStringWithShadow(s, (float)x, (float)y, colour);
    }
    
    public static void drawCentredString(final int x, final int y, final int colour, final String formatString, final Object... args) {
        final Minecraft mc = Minecraft.getMinecraft();
        final FontRenderer fr = mc.fontRendererObj;
        final String s = String.format(formatString, args);
        final int w = fr.getStringWidth(s);
        fr.drawStringWithShadow(s, (float)(x - w / 2), (float)y, colour);
    }
    
    public static void setCircularStencil(final double x, final double y, final double r) {
        GL11.glEnable(2929);
        GL11.glColorMask(false, false, false, false);
        GL11.glDepthMask(true);
        GL11.glDepthFunc(519);
        setColour(-1);
        Render.zDepth = 1000.0;
        drawCircle(x, y, r);
        Render.zDepth = 0.0;
        GL11.glColorMask(true, true, true, true);
        GL11.glDepthMask(false);
        GL11.glDepthFunc(516);
    }
    
    public static void setRectStencil(final double x, final double y, final double w, final double h) {
        GL11.glEnable(2929);
        GL11.glColorMask(false, false, false, false);
        GL11.glDepthMask(true);
        GL11.glDepthFunc(519);
        setColour(-1);
        Render.zDepth = 1000.0;
        drawRect(x, y, w, h);
        Render.zDepth = 0.0;
        GL11.glColorMask(true, true, true, true);
        GL11.glDepthMask(false);
        GL11.glDepthFunc(516);
    }
    
    public static void disableStencil() {
        GL11.glDepthMask(true);
        GL11.glDepthFunc(515);
        GL11.glDisable(2929);
    }
    
    static {
        Render.zDepth = 0.0;
    }
}
