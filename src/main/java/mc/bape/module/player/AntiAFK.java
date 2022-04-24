// L Bape, Decompiled by ImCzf233

package mc.bape.module.player;

import mc.bape.utils.*;
import mc.bape.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AntiAFK extends Module
{
    public TimerUtil timer;
    
    public AntiAFK() {
        super("AntiAFK", 0, ModuleType.Player, "Prevent you kicked of AFK");
        this.timer = new TimerUtil();
        AntiAFK.Chinese = "AntiAFK";
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (this.timer.delay(10.0f)) {
            if (AntiAFK.mc.thePlayer.onGround) {
                AntiAFK.mc.thePlayer.jump();
            }
            this.timer.reset();
        }
    }
}
