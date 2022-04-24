// L Bape, Decompiled by ImCzf233

package mc.bape.gui.MatrixClickGui;

import mc.bape.module.*;
import mc.bape.values.*;
import mc.bape.manager.*;
import net.minecraft.client.gui.*;
import mc.bape.utils.*;
import java.awt.*;
import org.lwjgl.input.*;
import mc.bape.gui.font.*;

public class KeyBindButton extends ValueButton
{
    public Module cheat;
    public double opacity;
    public boolean bind;
    
    public KeyBindButton(final Module cheat, final int x, final int y) {
        super(null, x, y);
        this.opacity = 0.0;
        this.custom = true;
        this.bind = false;
        this.cheat = cheat;
    }
    
    @Override
    public void render(final int mouseX, final int mouseY) {
        final CFontRenderer mfont = FontManager.F16;
        Gui.drawRect(0, 0, 0, 0, 0);
        Gui.drawRect(this.x - 10, this.y - 4, this.x + 80, this.y + 11, ClientUtil.reAlpha(1, 0.3f));
        mfont.drawString("Bind", (float)(this.x - 5), (float)(this.y + 2), new Color(184, 184, 184).getRGB());
        mfont.drawString(String.valueOf(this.bind ? "" : "") + Keyboard.getKeyName(this.cheat.getKey()), (float)(this.x + 76 - mfont.getStringWidth(Keyboard.getKeyName(this.cheat.getKey()))), (float)(this.y + 2), new Color(184, 184, 184).getRGB());
    }
    
    @Override
    public void key(final char typedChar, final int keyCode) {
        if (this.bind) {
            this.cheat.setKey(keyCode);
            if (keyCode == 1) {
                this.cheat.setKey(0);
            }
            ClickUi.binding = false;
            this.bind = false;
        }
        super.key(typedChar, keyCode);
    }
    
    @Override
    public void click(final int mouseX, final int mouseY, final int button) {
        if (mouseX > this.x - 7 && mouseX < this.x + 85 && mouseY > this.y - 6 && mouseY < this.y + FontManager.F18.getStringHeight(this.cheat.getName()) + 5 && button == 0) {
            final boolean b = !this.bind;
            this.bind = b;
            ClickUi.binding = b;
        }
        super.click(mouseX, mouseY, button);
    }
}
