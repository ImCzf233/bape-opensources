// L Bape, Decompiled by ImCzf233

package mc.bape.legit.hud.mod.impl;

import mc.bape.legit.hud.mod.*;
import net.minecraft.entity.*;
import net.minecraft.client.gui.inventory.*;

public class TargetHUD extends HudMod
{
    EntityLivingBase target;
    
    public TargetHUD() {
        super("TargetHUD", 50, 50);
    }
    
    @Override
    public void draw() {
        this.renderTargetHud();
        super.draw();
    }
    
    @Override
    public void renderDummy(final int mouseX, final int mouseY) {
        this.mc.fontRendererObj.drawStringWithShadow(this.mc.thePlayer.getName(), (float)(this.getX() + 2), (float)(this.getY() + 2), -1);
        this.mc.fontRendererObj.drawStringWithShadow((int)this.mc.thePlayer.getHealth() + " \u2665", (float)(this.getX() + 2), (float)(this.getY() + 2 + this.mc.fontRendererObj.FONT_HEIGHT), -1);
        GuiInventory.drawEntityOnScreen(this.getX() + this.mc.fontRendererObj.getStringWidth(this.mc.thePlayer.getName()) + 30, this.getY() + 30, 20, 50.0f, 0.0f, (EntityLivingBase)this.mc.thePlayer);
        super.renderDummy(mouseX, mouseY);
    }
    
    private void renderTargetHud() {
        this.target = (EntityLivingBase)this.mc.pointedEntity;
        if (this.target != null) {
            this.mc.fontRendererObj.drawStringWithShadow(this.target.getName(), (float)(this.getX() + 2), (float)this.getY(), -1);
            this.mc.fontRendererObj.drawStringWithShadow((int)this.target.getHealth() + " \u2665", (float)(this.getX() + 2), (float)(this.getY() + 2 + this.mc.fontRendererObj.FONT_HEIGHT), -1);
            GuiInventory.drawEntityOnScreen(this.getX() + this.mc.fontRendererObj.getStringWidth(this.target.getName()) + 30, this.getY() + 30, 20, 50.0f, 0.0f, this.target);
        }
    }
    
    @Override
    public int getWidth() {
        return 100;
    }
}
