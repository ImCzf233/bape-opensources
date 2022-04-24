// L Bape, Decompiled by ImCzf233

package mc.bape.module.world;

import mc.bape.utils.*;
import mc.bape.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import java.lang.reflect.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class FastPlace extends Module
{
    private final TimerUtil timer;
    
    public FastPlace() {
        super("FastPlace", 0, ModuleType.World, "Make you place the blocks faster");
        this.timer = new TimerUtil();
        FastPlace.Chinese = "\u5feb\u901f\u653e\u7f6e";
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.PlayerTickEvent event) {
        try {
            final Field rightClickDelay = Minecraft.class.getDeclaredField("rightClickDelayTimer");
            rightClickDelay.setAccessible(true);
            rightClickDelay.set(FastPlace.mc, 0);
        }
        catch (Exception d) {
            try {
                final Field e = Minecraft.class.getDeclaredField("rightClickDelayTimer");
                e.setAccessible(true);
                e.set(FastPlace.mc, 0);
            }
            catch (Exception f) {
                this.disable();
            }
        }
    }
}
