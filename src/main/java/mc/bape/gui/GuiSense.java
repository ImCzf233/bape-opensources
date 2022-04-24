// L Bape, Decompiled by ImCzf233

package mc.bape.gui;

import net.minecraft.client.*;
import mc.bape.vapu.*;
import mc.bape.manager.*;
import java.awt.*;
import net.minecraft.util.*;
import mc.bape.utils.*;

public class GuiSense implements Manager
{
    @Override
    public void init() {
        final Minecraft mc = Minecraft.getMinecraft();
        final String text = Client.CLIENT_NAME;
        FontManager.C18.drawStringWithShadow(" | " + (mc.isSingleplayer() ? "localhost:25565" : (mc.getCurrentServerData().serverIP.contains(":") ? mc.getCurrentServerData().serverIP : (mc.getCurrentServerData().serverIP + ":25565"))) + " | " + Minecraft.getDebugFPS() + "fps", 63.0, 25.0, new Color(255, 255, 255).hashCode());
        RenderUtil.drawImage(new ResourceLocation("JAT/Bape.png"), 7, 9, (float)FontManager.C18.getStringWidth(text), 40.0f, 255.0f);
    }
}
