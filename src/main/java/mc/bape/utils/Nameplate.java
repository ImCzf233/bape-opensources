// L Bape, Decompiled by ImCzf233

package mc.bape.utils;

import net.minecraft.client.*;
import net.minecraft.client.renderer.entity.*;
import java.awt.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;

public class Nameplate
{
    private double y;
    private int width;
    private String name;
    private EntityLivingBase owner;
    private Minecraft mc;
    private RenderManager rm;
    private double z;
    private double x;
    
    public void renderNewPlate(final Color col) {
        final float distance = this.mc.thePlayer.getDistanceToEntity((Entity)this.owner);
        final float absDistance = Math.abs(distance / 4.0f);
        final float lllllIIlIIlIlll = 1.6f;
        final float lllllIIlIIlIllI = 0.022133334f;
        final float lllllIIlIIlIlIl = 2.0f;
        final float v = -this.width - 2.0f;
        final float lllllIIlIIlIIll = -2.0f;
        final float v2 = this.width + 2.0f;
        final float lllllIIlIIlIIIl = 10.0f;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)this.x + 0.0f, (float)this.y + this.owner.height + 0.5f, (float)this.z);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-this.rm.playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(this.rm.playerViewX, 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-0.022133334f, -0.022133334f, 0.022133334f);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        final boolean lllllIIlIIIlllI = false;
        final int width = this.rm.getFontRenderer().getStringWidth(this.name) / 2;
        GlStateManager.disableTexture2D();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos((double)(-width - 1), -1.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        worldRenderer.pos((double)(-width - 1), 8.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        worldRenderer.pos((double)(width + 1), 8.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        worldRenderer.pos((double)(width + 1), -1.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        tessellator.draw();
        GL11.glTranslated(0.0, (double)(-(absDistance * 7.0f)), 0.0);
        GL11.glScaled((double)absDistance, (double)absDistance, (double)absDistance);
        Gui.drawRect((int)v, -2, (int)v2, 10, 1056964608);
        GlStateManager.enableTexture2D();
        this.rm.getFontRenderer().drawString(this.name, -this.rm.getFontRenderer().getStringWidth(this.name) / 2, 0, 553648127);
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        this.rm.getFontRenderer().drawString(this.name, -this.rm.getFontRenderer().getStringWidth(this.name) / 2, 0, -1);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.popMatrix();
    }
    
    public Nameplate(final String Name, final double X, final double Y, final double Z, final EntityLivingBase livingBase) {
        this.mc = Minecraft.getMinecraft();
        this.name = Name;
        this.x = X;
        this.y = Y;
        this.z = Z;
        this.owner = livingBase;
        this.width = this.mc.fontRendererObj.getStringWidth(this.name) / 2;
        this.rm = this.mc.getRenderManager();
    }
}
