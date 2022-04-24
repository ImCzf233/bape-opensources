// L Bape, Decompiled by ImCzf233

package mc.bape.module.render;

import mc.bape.module.*;
import net.minecraft.client.*;

public class FullBright extends Module
{
    private float old;
    
    public FullBright() {
        super("FullBright", 0, ModuleType.Render, "Make the bright for night and dark");
        FullBright.Chinese = "\u591c\u89c6";
    }
    
    @Override
    public void enable() {
        this.old = FullBright.mc.gameSettings.gammaSetting;
        Minecraft.getMinecraft().gameSettings.gammaSetting = 300.0f;
    }
    
    @Override
    public void disable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = this.old;
    }
}
