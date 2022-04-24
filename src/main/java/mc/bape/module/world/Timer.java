// L Bape, Decompiled by ImCzf233

package mc.bape.module.world;

import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Timer extends Module
{
    private Numbers<Double> timer;
    
    public Timer() {
        super("Timer", 0, ModuleType.World, "Make world quickly");
        this.timer = new Numbers<Double>("Speed", "Speed", 1.0, 1.0, 10.0, 1.0);
        this.addValues(this.timer);
        Timer.Chinese = "\u53d8\u901f\u9f7f\u8f6e";
    }
    
    @SubscribeEvent
    public boolean onUpdate(final TickEvent.PlayerTickEvent event) {
        if (Timer.mc.thePlayer == null) {}
        return false;
    }
}
