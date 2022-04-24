// L Bape, Decompiled by ImCzf233

package mc.bape.utils.render;

import mc.bape.gui.font.*;
import mc.bape.utils.math.*;
import mc.bape.manager.*;
import com.google.common.collect.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import mc.bape.utils.*;
import net.minecraft.client.renderer.*;
import java.util.*;
import java.awt.*;
import java.util.List;

public class CompassUtil
{
    public float innerWidth;
    public float outerWidth;
    public boolean shadow;
    public float scale;
    public int accuracy;
    CFontRenderer yahei28U;
    CFontRenderer yahei18U;
    CFontRenderer yahei22U;
    public List<Degree> degrees;
    
    public CompassUtil(final float i, final float o, final float s, final int a, final boolean sh) {
        this.yahei28U = FontManager.F22;
        this.yahei18U = FontManager.F18;
        this.yahei22U = FontManager.F22;
        this.degrees = Lists.newArrayList();
        this.innerWidth = i;
        this.outerWidth = o;
        this.scale = s;
        this.accuracy = a;
        this.shadow = sh;
        this.degrees.add(new Degree("N", 1));
        this.degrees.add(new Degree("195", 2));
        this.degrees.add(new Degree("210", 2));
        this.degrees.add(new Degree("NE", 3));
        this.degrees.add(new Degree("240", 2));
        this.degrees.add(new Degree("255", 2));
        this.degrees.add(new Degree("E", 1));
        this.degrees.add(new Degree("285", 2));
        this.degrees.add(new Degree("300", 2));
        this.degrees.add(new Degree("SE", 3));
        this.degrees.add(new Degree("330", 2));
        this.degrees.add(new Degree("345", 2));
        this.degrees.add(new Degree("S", 1));
        this.degrees.add(new Degree("15", 2));
        this.degrees.add(new Degree("30", 2));
        this.degrees.add(new Degree("SW", 3));
        this.degrees.add(new Degree("60", 2));
        this.degrees.add(new Degree("75", 2));
        this.degrees.add(new Degree("W", 1));
        this.degrees.add(new Degree("105", 2));
        this.degrees.add(new Degree("120", 2));
        this.degrees.add(new Degree("NW", 3));
        this.degrees.add(new Degree("150", 2));
        this.degrees.add(new Degree("165", 2));
    }
    
    public void draw(final ScaledResolution sr) {
        this.preRender(sr);
        final float center = (float)(sr.getScaledWidth() / 2);
        int count = 0;
        int cardinals = 0;
        int subCardinals = 0;
        int markers = 0;
        final float offset = 0.0f;
        final float yaaahhrewindTime = Minecraft.getMinecraft().thePlayer.rotationYaw % 360.0f * 2.0f + 1080.0f;
        GL11.glPushMatrix();
        GL11.glEnable(3089);
        RenderUtil.doGlScissor((float)(sr.getScaledWidth() / 2 - 120), 25.0f, 220.0f, 25.0f);
        for (final Degree d : this.degrees) {
            final float location = center + count * 30 - yaaahhrewindTime;
            final float completeLocation = (d.type == 1) ? (location - this.yahei28U.getStringWidth(d.text) / 2) : ((d.type == 2) ? (location - this.yahei18U.getStringWidth(d.text) / 2) : (location - this.yahei22U.getStringWidth(d.text) / 2));
            final int opacity = this.opacity(sr, completeLocation);
            if (d.type == 1 && opacity != 16777215) {
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                this.yahei28U.drawString(d.text, completeLocation, 25.0, this.opacity(sr, completeLocation), false);
                ++cardinals;
            }
            if (d.type == 2 && opacity != 16777215) {
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                RenderUtil.drawRect(location - 0.5f, 29.0f, location + 0.5f, 34.0f, this.opacity(sr, completeLocation));
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                this.yahei18U.drawString(d.text, completeLocation, 37.5, this.opacity(sr, completeLocation), false);
                ++markers;
            }
            if (d.type == 3 && opacity != 16777215) {
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                this.yahei22U.drawString(d.text, completeLocation, 25 + this.yahei28U.getStringHeight("") / 2 - this.yahei22U.getStringHeight("") / 2, this.opacity(sr, completeLocation), false);
                ++subCardinals;
            }
            ++count;
        }
        for (final Degree d : this.degrees) {
            final float location = center + count * 30 - yaaahhrewindTime;
            final float completeLocation = (d.type == 1) ? (location - this.yahei28U.getStringWidth(d.text) / 2) : ((d.type == 2) ? (location - this.yahei18U.getStringWidth(d.text) / 2) : (location - this.yahei22U.getStringWidth(d.text) / 2));
            if (d.type == 1) {
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                this.yahei28U.drawString(d.text, completeLocation, 25.0, this.opacity(sr, completeLocation), false);
                ++cardinals;
            }
            if (d.type == 2) {
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                RenderUtil.drawRect(location - 0.5f, 29.0f, location + 0.5f, 34.0f, this.opacity(sr, completeLocation));
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                this.yahei18U.drawString(d.text, completeLocation, 37.5, this.opacity(sr, completeLocation), false);
                ++markers;
            }
            if (d.type == 3) {
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                this.yahei22U.drawString(d.text, completeLocation, 25 + this.yahei28U.getStringHeight("") / 2 - this.yahei22U.getStringHeight("") / 2, this.opacity(sr, completeLocation), false);
                ++subCardinals;
            }
            ++count;
        }
        for (final Degree d : this.degrees) {
            final float location = center + count * 30 - yaaahhrewindTime;
            final float completeLocation = (d.type == 1) ? (location - this.yahei28U.getStringWidth(d.text) / 2) : ((d.type == 2) ? (location - this.yahei18U.getStringWidth(d.text) / 2) : (location - this.yahei22U.getStringWidth(d.text) / 2));
            if (d.type == 1) {
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                this.yahei28U.drawString(d.text, completeLocation, 25.0, this.opacity(sr, completeLocation), false);
                ++cardinals;
            }
            if (d.type == 2) {
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                RenderUtil.drawRect(location - 0.5f, 29.0f, location + 0.5f, 34.0f, this.opacity(sr, completeLocation));
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                this.yahei18U.drawString(d.text, completeLocation, 37.5, this.opacity(sr, completeLocation), false);
                ++markers;
            }
            if (d.type == 3) {
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                this.yahei22U.drawString(d.text, completeLocation, 25 + this.yahei28U.getStringHeight("") / 2 - this.yahei22U.getStringHeight("") / 2, this.opacity(sr, completeLocation), false);
                ++subCardinals;
            }
            ++count;
        }
        GL11.glDisable(3089);
        GL11.glPopMatrix();
    }
    
    public void preRender(final ScaledResolution sr) {
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
    }
    
    public int opacity(final ScaledResolution sr, final float offset) {
        final int op = 0;
        final float offs = 255.0f - Math.abs(sr.getScaledWidth() / 2 - offset) * 1.8f;
        final Color c = new Color(255, 255, 255, (int)Math.min(Math.max(0.0f, offs), 255.0f));
        return c.getRGB();
    }
}
