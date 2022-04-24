package mc.bape.legit.hud.mod.impl;

import mc.bape.legit.hud.mod.*;
import mc.bape.manager.*;

public class TestMod extends HudMod
{
    public TestMod() {
        super("testMod", 5, 5);
    }
    
    @Override
    public void draw() {
        FontManager.F18.drawString(this.name, (float)this.getX(), (float)this.getY(), -1);
        super.draw();
    }
    
    @Override
    public void renderDummy(final int mouseX, final int mouseY) {
        FontManager.F18.drawString(this.name, (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
    
    @Override
    public int getWidth() {
        return FontManager.F18.getStringWidth(this.name);
    }
    
    @Override
    public int getHeight() {
        return FontManager.F18.getStringWidth(this.name);
    }
}
