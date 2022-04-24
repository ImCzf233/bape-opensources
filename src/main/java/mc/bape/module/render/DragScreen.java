// L Bape, Decompiled by ImCzf233

package mc.bape.module.render;

import mc.bape.module.*;
import mc.bape.legit.hud.*;
import net.minecraft.client.gui.*;

public class DragScreen extends Module
{
    public DragScreen() {
        super("DragScreen", 56, ModuleType.Render, "A DragScreen");
        DragScreen.Chinese = "\u53ef\u79fb\u52a8HUD";
    }
    
    @Override
    public void enable() {
        this.setState(false);
        DragScreen.mc.thePlayer.closeScreen();
        DragScreen.mc.displayGuiScreen((GuiScreen)new HUDConfigScreen());
    }
}
