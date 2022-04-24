// L Bape, Decompiled by ImCzf233

package mc.bape.module.movement;

import mc.bape.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Spider extends Module
{
    public Spider() {
        super("Spider", 0, ModuleType.Movement, "");
        Spider.Chinese = "\u8718\u86db\u4fa0";
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent event) {
        if (!Spider.mc.thePlayer.isOnLadder() && Spider.mc.thePlayer.motionY < 0.2) {
            Spider.mc.thePlayer.motionY = 0.2;
        }
    }
}
