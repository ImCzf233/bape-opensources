// L Bape, Decompiled by ImCzf233

package mc.bape.legit.hud.mod;

import net.minecraft.client.*;
import mc.bape.legit.hud.*;
import java.awt.*;

public class HudMod
{
    public Minecraft mc;
    public String name;
    public boolean enabled;
    public DraggableComponent drag;
    public int x;
    public int y;
    
    public HudMod(final String name, final int x, final int y) {
        this.mc = Minecraft.getMinecraft();
        this.name = name;
        this.x = x;
        this.y = y;
        this.drag = new DraggableComponent(x, y, x + this.getWidth(), y + this.getHeight(), new Color(0, 0, 0, 0).getRGB());
    }
    
    public int getWidth() {
        return 50;
    }
    
    public int getHeight() {
        return 50;
    }
    
    public void draw() {
    }
    
    public void renderDummy(final int mouseX, final int mouseY) {
        this.drag.draw(mouseX, mouseY);
    }
    
    public int getX() {
        return this.drag.getxPosition();
    }
    
    public int getY() {
        return this.drag.getyPosition();
    }
}
