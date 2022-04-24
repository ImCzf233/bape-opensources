// L Bape, Decompiled by ImCzf233

package mc.bape.utils.gl;

import java.nio.*;
import net.minecraft.client.renderer.*;
import mc.bape.utils.math.*;
import org.lwjgl.util.glu.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public final class GLUtils
{
    public static final FloatBuffer MODELVIEW;
    public static final FloatBuffer PROJECTION;
    public static final IntBuffer VIEWPORT;
    public static final FloatBuffer TO_SCREEN_BUFFER;
    public static final FloatBuffer TO_WORLD_BUFFER;
    
    private GLUtils() {
    }
    
    public static void init() {
    }
    
    public static float[] getColor(final int hex) {
        return new float[] { (hex >> 16 & 0xFF) / 255.0f, (hex >> 8 & 0xFF) / 255.0f, (hex & 0xFF) / 255.0f, (hex >> 24 & 0xFF) / 255.0f };
    }
    
    public static void glColor(final int hex) {
        final float[] color = getColor(hex);
        GlStateManager.color(color[0], color[1], color[2], color[3]);
    }
    
    public static void rotateX(final float angle, final double x2, final double y2, final double z2) {
        GlStateManager.translate(x2, y2, z2);
        GlStateManager.rotate(angle, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(-x2, -y2, -z2);
    }
    
    public static void rotateY(final float angle, final double x2, final double y2, final double z2) {
        GlStateManager.translate(x2, y2, z2);
        GlStateManager.rotate(angle, 0.0f, 1.0f, 0.0f);
        GlStateManager.translate(-x2, -y2, -z2);
    }
    
    public static void rotateZ(final float angle, final double x2, final double y2, final double z2) {
        GlStateManager.translate(x2, y2, z2);
        GlStateManager.rotate(angle, 0.0f, 0.0f, 1.0f);
        GlStateManager.translate(-x2, -y2, -z2);
    }
    
    public static Vec3f toScreen(final Vec3f pos) {
        return toScreen(pos.getX(), pos.getY(), pos.getZ());
    }
    
    public static void startSmooth() {
        GL11.glEnable(2848);
        GL11.glEnable(2881);
        GL11.glEnable(2832);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glHint(3153, 4354);
    }
    
    public static void endSmooth() {
        GL11.glDisable(2848);
        GL11.glDisable(2881);
        GL11.glEnable(2832);
    }
    
    public static Vec3f toScreen(final double x2, final double y2, final double z2) {
        final boolean result = GLU.gluProject((float)x2, (float)y2, (float)z2, GLUtils.MODELVIEW, GLUtils.PROJECTION, GLUtils.VIEWPORT, (FloatBuffer)GLUtils.TO_SCREEN_BUFFER.clear());
        if (result) {
            return new Vec3f(GLUtils.TO_SCREEN_BUFFER.get(0), Display.getHeight() - GLUtils.TO_SCREEN_BUFFER.get(1), GLUtils.TO_SCREEN_BUFFER.get(2));
        }
        return null;
    }
    
    public static Vec3f toWorld(final Vec3f pos) {
        return toWorld(pos.getX(), pos.getY(), pos.getZ());
    }
    
    public static Vec3f toWorld(final double x2, final double y2, final double z2) {
        final boolean result = GLU.gluUnProject((float)x2, (float)y2, (float)z2, GLUtils.MODELVIEW, GLUtils.PROJECTION, GLUtils.VIEWPORT, (FloatBuffer)GLUtils.TO_WORLD_BUFFER.clear());
        if (result) {
            return new Vec3f(GLUtils.TO_WORLD_BUFFER.get(0), GLUtils.TO_WORLD_BUFFER.get(1), GLUtils.TO_WORLD_BUFFER.get(2));
        }
        return null;
    }
    
    public static FloatBuffer getModelview() {
        return GLUtils.MODELVIEW;
    }
    
    public static FloatBuffer getProjection() {
        return GLUtils.PROJECTION;
    }
    
    public static IntBuffer getViewport() {
        return GLUtils.VIEWPORT;
    }
    
    static {
        MODELVIEW = BufferUtils.createFloatBuffer(16);
        PROJECTION = BufferUtils.createFloatBuffer(16);
        VIEWPORT = BufferUtils.createIntBuffer(16);
        TO_SCREEN_BUFFER = BufferUtils.createFloatBuffer(3);
        TO_WORLD_BUFFER = BufferUtils.createFloatBuffer(3);
    }
}
