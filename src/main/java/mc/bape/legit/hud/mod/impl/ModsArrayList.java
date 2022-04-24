// L Bape, Decompiled by ImCzf233

package mc.bape.legit.hud.mod.impl;

import mc.bape.legit.hud.mod.*;
import net.minecraft.client.gui.*;
import mc.bape.module.*;
import net.minecraft.util.*;
import java.awt.*;
import mc.bape.manager.*;
import mc.bape.utils.*;
import java.util.*;

public class ModsArrayList extends HudMod
{
    int width;
    
    public ModsArrayList() {
        super("ModsArrayList", 100, 100);
        this.width = new ScaledResolution(this.mc).getScaledWidth();
    }
    
    @Override
    public void draw() {
        final ArrayList<Module> enabledModules = new ArrayList<Module>();
        RenderUtils.drawImage((float)(this.getX() - 62), (float)(this.getY() - 27), 70, 23, new ResourceLocation("JAT/BapelogoR.png"), new Color(255, 255, 255));
        for (final Module m : ModuleManager.getModules()) {
            if (m.state) {
                enabledModules.add(m);
            }
        }
        enabledModules.sort(new Comparator<Module>() {
            @Override
            public int compare(final Module o1, final Module o2) {
                return FontManager.F20.getStringWidth(o2.getName()) - FontManager.F20.getStringWidth(o1.getName());
            }
        });
        int r = 0;
        int count = 0;
        for (final Module i : enabledModules) {
            if (i != null && i.getState()) {
                final int moduleWidth = FontManager.F20.getStringWidth(i.name);
                FontManager.F20.drawStringWithShadow(i.name, this.getX() - moduleWidth - 1, this.getY() + count * FontManager.F20.getHeight() + 2, ColorUtils.rainbow(1) + r);
                this.y += FontManager.F20.getHeight();
                r += 10;
            }
            ++count;
        }
        super.draw();
    }
    
    @Override
    public void renderDummy(final int mouseX, final int mouseY) {
        final ArrayList<Module> enabledModules = new ArrayList<Module>();
        RenderUtils.drawImage((float)(this.getX() - 62), (float)(this.getY() - 40), 85, 15, new ResourceLocation("JAT/TextGUI.png"), new Color(255, 255, 255));
        RenderUtils.drawImage((float)(this.getX() - 62), (float)(this.getY() - 27), 70, 23, new ResourceLocation("JAT/BapelogoR.png"), new Color(255, 255, 255));
        for (final Module m : ModuleManager.getModules()) {
            if (m.state) {
                enabledModules.add(m);
            }
        }
        enabledModules.sort(new Comparator<Module>() {
            @Override
            public int compare(final Module o1, final Module o2) {
                return FontManager.F20.getStringWidth(o2.getName()) - FontManager.F20.getStringWidth(o1.getName());
            }
        });
        int r = 0;
        int count = 0;
        for (final Module i : enabledModules) {
            if (i != null && i.getState()) {
                final int moduleWidth = FontManager.F20.getStringWidth(i.name);
                FontManager.F20.drawStringWithShadow(i.name, this.getX() - moduleWidth - 1, this.getY() + count * FontManager.F20.getHeight() + 2, ColorUtils.rainbow(1) + r);
                r += 10;
            }
            ++count;
        }
        super.renderDummy(mouseX, mouseY);
    }
}
