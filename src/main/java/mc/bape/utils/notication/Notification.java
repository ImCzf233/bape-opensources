// L Bape, Decompiled by ImCzf233

package mc.bape.utils.notication;

import net.minecraft.util.*;
import net.minecraft.client.*;
import mc.bape.manager.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import mc.bape.utils.math.*;
import mc.bape.utils.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;

public class Notification
{
    private String message;
    private TimeHelper timer;
    private double lastY;
    private double posY;
    private double width;
    private double height;
    private double animationX;
    private int color;
    private int imageWidth;
    private ResourceLocation image;
    private long stayTime;
    Minecraft mc;
    
    public Notification(final String message, final Type type) {
        this.mc = Minecraft.getMinecraft();
        this.message = message;
        (this.timer = new TimeHelper()).reset();
        this.width = FontManager.F16.getStringWidth(message) + 35;
        this.height = 20.0;
        this.animationX = 0.0;
        this.stayTime = 1500L;
        this.imageWidth = 16;
        this.posY = -1.0;
        this.image = new ResourceLocation("notification/" + type.name().toUpperCase() + ".png");
        if (type.equals(Type.INFO)) {
            this.color = Colors.DARKGREY.c;
        }
        else if (type.equals(Type.ERROR)) {
            this.color = new Color(36, 36, 36).getRGB();
        }
        else if (type.equals(Type.SUCCESS)) {
            this.color = new Color(36, 36, 36).getRGB();
        }
        else if (type.equals(Type.WARNING)) {
            this.color = Colors.DARKGREY.c;
        }
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void drawBlack2(final double getY, final double lastY) {
        this.width = FontManager.F16.getStringWidth(this.message) + 45;
        this.height = 22.0;
        this.imageWidth = 11;
        if (this.animationX < this.width && !this.timer.isDelayComplete(this.stayTime)) {
            final double animationX = this.animationX;
            final float n = 240.0f;
            final Minecraft mc = this.mc;
            this.animationX = animationX + n / Minecraft.getDebugFPS();
        }
        if (this.timer.isDelayComplete(this.stayTime)) {
            final double animationX2 = this.animationX;
            final float n2 = 240.0f;
            final Minecraft mc2 = this.mc;
            this.animationX = animationX2 - n2 / Minecraft.getDebugFPS();
        }
        if (this.posY == -1.0) {
            this.posY = getY;
        }
        else {
            this.posY = getY;
        }
        final ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        final int x1 = (int)(res.getScaledWidth() - this.animationX);
        final int x2 = (int)(res.getScaledWidth() + this.animationX);
        int y1 = (int)this.posY - 22;
        final int y2 = (int)(y1 + this.height);
        GL11.glPushMatrix();
        RenderUtil.drawRect((float)x1, (float)y1, (float)x2, (float)y2, MathUtil.reAlpha(this.color, 0.8f));
        this.drawImage(this.image, (int)(x1 + (this.height - this.imageWidth) / 2.0) - 1, y1 + (int)((this.height - this.imageWidth) / 2.0), this.imageWidth, this.imageWidth);
        if (!this.timer.isDelayComplete(this.stayTime)) {
            RenderUtil.drawRect((float)x1, (float)(y2 - 1.5), x2 - (x2 - x1) * ((System.currentTimeMillis() - this.timer.lastMs) / (float)this.stayTime), (float)y2, MathUtil.reAlpha(Colors.BLUE.c, 1.0f));
        }
        ++y1;
        if (this.message.contains(" Enabled")) {
            FontManager.F16.drawString(this.message.replace(" Enabled", ""), (float)(x1 + 19), (float)(y1 + this.height / 4.0), -1);
            FontManager.F16.drawString(" Enabled", (float)(x1 + 20 + FontManager.F16.getStringWidth(this.message.replace(" Enabled", ""))), (float)(y1 + this.height / 4.0), Colors.GREY.c);
        }
        else if (this.message.contains(" Disabled")) {
            FontManager.F16.drawString(this.message.replace(" Disabled", ""), (float)(x1 + 19), (float)(y1 + this.height / 4.0), -1);
            FontManager.F16.drawString(" Disabled", (float)(x1 + 20 + FontManager.F16.getStringWidth(this.message.replace(" Disabled", ""))), (float)(y1 + this.height / 4.0), Colors.GREY.c);
        }
        else {
            FontManager.F16.drawString(this.message, (float)(x1 + 20), (float)(y1 + this.height / 4.0), -1);
        }
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public void drawBlack1(final double getY, final double lastY) {
        this.width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.message) + 25;
        this.height = 22.0;
        this.imageWidth = 11;
        final ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        if (this.animationX < this.width && !this.timer.isDelayComplete(this.stayTime)) {
            final double animationX = this.animationX;
            final float n = 240.0f;
            final Minecraft mc = this.mc;
            this.animationX = animationX + n / Minecraft.getDebugFPS();
        }
        if (this.timer.isDelayComplete(this.stayTime)) {
            final double animationX2 = this.animationX;
            final float n2 = 240.0f;
            final Minecraft mc2 = this.mc;
            this.animationX = animationX2 - n2 / Minecraft.getDebugFPS();
        }
        this.posY = getY;
        final int x1 = (int)(res.getScaledWidth() - this.animationX);
        final int x2 = (int)(res.getScaledWidth() + this.animationX);
        int y1 = (int)this.posY - 22;
        final int y2 = (int)(y1 + this.height);
        GL11.glPushMatrix();
        RenderUtil.drawRect((float)x1, (float)y1, (float)x2, (float)y2, new Color(30, 30, 30, 230).getRGB());
        this.drawImage(this.image, (int)(x1 + (this.height - this.imageWidth) / 2.0) - 1, y1 + (int)((this.height - this.imageWidth) / 2.0), this.imageWidth, this.imageWidth, new Color(Colors.WHITE.c));
        if (!this.timer.isDelayComplete(this.stayTime)) {
            RenderUtil.drawRect((float)x1, (float)(y2 - 1.5), x2 - (x2 - x1) * ((System.currentTimeMillis() - this.timer.lastMs) / (float)this.stayTime), (float)y2, new Color(107, 157, 255).getRGB());
        }
        ++y1;
        if (this.message.contains(" Enabled")) {
            FontManager.F20.drawString(this.message.replace(" Enabled", ""), (float)(x1 + 19), (float)(int)(y1 + this.height / 4.0), new Color(107, 157, 255).getRGB());
            FontManager.F20.drawString(" Enabled", (float)(x1 + 20 + FontManager.F22.getStringWidth(this.message.replace(" Enabled", ""))), (float)(int)(y1 + this.height / 4.0), Colors.WHITE.c);
        }
        else if (this.message.contains(" Disabled")) {
            FontManager.F20.drawString(this.message.replace(" Disabled", ""), (float)(x1 + 19), (float)(int)(y1 + this.height / 4.0), new Color(107, 157, 255).getRGB());
            FontManager.F20.drawString(" Disabled", (float)(x1 + 20 + FontManager.F22.getStringWidth(this.message.replace(" Disabled", ""))), (float)(int)(y1 + this.height / 4.0), Colors.WHITE.c);
        }
        else {
            FontManager.F20.drawString(this.message, (float)(x1 + 20), (float)(int)(y1 + this.height / 4.0), -1);
        }
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public boolean shouldDelete() {
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        return this.timer.isDelayComplete(this.stayTime) && this.animationX <= 0.0;
    }
    
    private boolean isFinished() {
        final ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        return this.timer.isDelayComplete(this.stayTime);
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public double getAnimationState(double animation, final double finalState, final double speed) {
        final float add = (float)(RenderUtil.delta * speed);
        if (animation < finalState) {
            if (animation + add < finalState) {
                animation += add;
            }
            else {
                animation = finalState;
            }
        }
        else if (animation - add > finalState) {
            animation -= add;
        }
        else {
            animation = finalState;
        }
        return animation;
    }
    
    public void drawImage(final ResourceLocation image, final int x, final int y, final int width, final int height) {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, (float)width, (float)height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }
    
    public void drawImage(final ResourceLocation image, final int x, final int y, final int width, final int height, final Color color) {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        RenderUtil.color(color.getRGB());
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, (float)width, (float)height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }
    
    public enum Type
    {
        SUCCESS, 
        INFO, 
        WARNING, 
        ERROR;
    }
}
