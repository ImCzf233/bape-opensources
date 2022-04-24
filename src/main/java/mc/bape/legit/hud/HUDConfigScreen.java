// L Bape, Decompiled by ImCzf233

package mc.bape.legit.hud;

import net.minecraft.client.gui.*;
import mc.bape.vapu.*;
import mc.bape.legit.hud.mod.*;
import java.util.*;

public class HUDConfigScreen extends GuiScreen
{
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        for (final HudMod m : Client.INSTANCE.hudManager.hudMods) {
            m.renderDummy(mouseX, mouseY);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
