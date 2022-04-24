// L Bape, Decompiled by ImCzf233

package mc.bape.legit.hud.mod.impl;

import mc.bape.legit.hud.mod.*;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.opengl.*;
import java.awt.*;
import mc.bape.utils.*;
import mc.bape.manager.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;

public class InventoryHUD extends HudMod
{
    public InventoryHUD() {
        super("InventoryHUD", 10, 20);
    }
    
    @Override
    public void draw() {
        GL11.glPushMatrix();
        RenderUtil.drawRect((float)this.getX(), (float)(this.getY() - 10), (float)(this.getX() + 182), (float)(this.getY() + 1), new Color(49, 147, 255, 255).getRGB());
        FontManager.F18.drawStringWithShadow("Inventory HUD", this.getX() + 1, this.getY() - 7, new Color(255, 255, 255, 255).getRGB());
        RenderUtil.drawRect((float)this.getX(), (float)this.getY(), (float)(this.getX() + 180 + 2), (float)(this.getY() + 60 + 2), new Color(0, 0, 0, 160).getRGB());
        RenderHelper.enableGUIStandardItemLighting();
        for (int i = 0; i < 27; ++i) {
            final ItemStack[] itemStack = this.mc.thePlayer.inventory.mainInventory;
            final int offsetX = this.getX() + 2 + i % 9 * 20;
            final int offsetY = this.getY() + i / 9 * 20;
            this.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack[i + 9], offsetX, offsetY);
            this.mc.getRenderItem().renderItemOverlayIntoGUI(this.mc.fontRendererObj, itemStack[i + 9], offsetX, offsetY, (String)null);
        }
        GL11.glPopMatrix();
        super.draw();
    }
    
    @Override
    public void renderDummy(final int mouseX, final int mouseY) {
        GL11.glPushMatrix();
        RenderUtil.drawRect((float)this.getX(), (float)(this.getY() - 9), (float)(this.getX() + 182), (float)(this.getY() + 1), new Color(49, 147, 255, 255).getRGB());
        FontManager.F18.drawStringWithShadow("Inventory HUD", this.getX() + 1, this.getY() - 7, new Color(255, 255, 255, 255).getRGB());
        RenderUtil.drawRect((float)this.getX(), (float)this.getY(), (float)(this.getX() + 180 + 2), (float)(this.getY() + 60 + 2), new Color(0, 0, 0, 160).getRGB());
        RenderHelper.enableGUIStandardItemLighting();
        for (int i = 0; i < 27; ++i) {
            final ItemStack[] itemStack = this.mc.thePlayer.inventory.mainInventory;
            final int offsetX = this.getX() + 2 + i % 9 * 20;
            final int offsetY = this.getY() + i / 9 * 20;
            this.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack[i + 9], offsetX, offsetY);
            this.mc.getRenderItem().renderItemOverlayIntoGUI(this.mc.fontRendererObj, itemStack[i + 9], offsetX, offsetY, (String)null);
        }
        GL11.glPopMatrix();
        super.renderDummy(mouseX, mouseY);
    }
}
