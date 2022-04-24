// L Bape, Decompiled by ImCzf233

package mc.bape.gui.MatrixClickGui;

import mc.bape.module.*;
import mc.bape.gui.font.*;
import mc.bape.manager.*;
import com.google.common.collect.*;
import mc.bape.values.*;
import java.util.*;
import mc.bape.utils.*;
import net.minecraft.client.gui.*;
import java.awt.*;

public class Button
{
    public Module cheat;
    CFontRenderer font;
    public Window parent;
    public int x;
    public int y;
    public int index;
    public int remander;
    public double opacity;
    public ArrayList<ValueButton> buttons;
    public boolean expand;
    
    public Button(final Module cheat, final int x, final int y) {
        this.font = FontManager.F18;
        this.opacity = 0.0;
        this.buttons = Lists.newArrayList();
        this.cheat = cheat;
        this.x = x;
        this.y = y;
        int y2 = y + 14;
        for (final Value v : cheat.getValues()) {
            this.buttons.add(new ValueButton(v, x + 5, y2));
            y2 += 15;
        }
        this.buttons.add(new KeyBindButton(cheat, x + 5, y2));
    }
    
    public void render(final int mouseX, final int mouseY) {
        final CFontRenderer font = FontManager.F20;
        if (this.index != 0) {
            final Button b2 = this.parent.buttons.get(this.index - 1);
            this.y = b2.y + 15 + (b2.expand ? (15 * b2.buttons.size()) : 0);
        }
        for (int i = 0; i < this.buttons.size(); ++i) {
            this.buttons.get(i).y = this.y + 14 + 15 * i;
            this.buttons.get(i).x = this.x + 5;
        }
        Gui.drawRect(this.x - 5, this.y - 5, this.x + 85, this.y + font.getStringHeight(this.cheat.getName()) + 2, ClientUtil.reAlpha(1, 0.5f));
        if (this.cheat.state) {
            Gui.drawRect(this.x - 5, this.y - 5, this.x + 85, this.y + font.getStringHeight(this.cheat.getName()) + 2, ClientUtil.reAlpha(1, 0.5f));
            font.drawString(this.cheat.getName(), (float)this.x, (float)this.y, new Color(47, 154, 241).getRGB());
        }
        else {
            font.drawString(this.cheat.getName(), (float)this.x, (float)this.y, new Color(184, 184, 184).getRGB());
        }
        if (this.expand || this.buttons.size() > 1) {}
        if (this.expand) {
            this.buttons.forEach(b -> b.render(mouseX, mouseY));
        }
    }
    
    public void key(final char typedChar, final int keyCode) {
        this.buttons.forEach(b -> b.key(typedChar, keyCode));
    }
    
    public void click(final int mouseX, final int mouseY, final int button) {
        if (mouseX > this.x - 7 && mouseX < this.x + 85 && mouseY > this.y - 6 && mouseY < this.y + this.font.getStringHeight(this.cheat.getName()) + 4) {
            if (button == 0) {
                this.cheat.setState(!this.cheat.getState());
            }
            if (button == 1 && !this.buttons.isEmpty()) {
                this.expand = !this.expand;
            }
        }
        if (this.expand) {
            this.buttons.forEach(b -> b.click(mouseX, mouseY, button));
        }
    }
    
    public void setParent(final Window parent) {
        this.parent = parent;
        for (int i = 0; i < this.parent.buttons.size(); ++i) {
            if (this.parent.buttons.get(i) == this) {
                this.index = i;
                this.remander = this.parent.buttons.size() - i;
                break;
            }
        }
    }
}
