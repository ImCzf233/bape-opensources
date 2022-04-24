package mc.bape.module.other;

import mc.bape.module.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Chams extends Module
{
    public Chams() {
        super("Chams", 0, ModuleType.Other, "Allows you to Xray other players");
    }
    
    @SubscribeEvent
    public void onRenderPlayer(final RenderPlayerEvent.Pre e) {
        GL11.glEnable(32823);
        GL11.glPolygonOffset(1.0f, -1100000.0f);
    }
    
    @SubscribeEvent
    public void onRenderPlayer(final RenderPlayerEvent.Post e) {
        GL11.glDisable(32823);
        GL11.glPolygonOffset(1.0f, 1100000.0f);
    }
}
