// L Bape, Decompiled by ImCzf233

package mc.bape.module.movement;

import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import mc.bape.vapu.*;

public class Sprint extends Module
{
    private Option<Boolean> Sprint;
    
    public Sprint() {
        super("Sprint", 0, ModuleType.Movement, "Force sprint when you moving");
        this.Sprint = new Option<Boolean>("KeepSprint", "KeepSprint", false);
        this.addValues(this.Sprint);
        Chinese = "\u5f3a\u5236\u75be\u8dd1";
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent event) {
        if (!mc.thePlayer.isCollidedHorizontally && mc.thePlayer.moveForward > 0.0f) {
            mc.thePlayer.setSprinting(true);
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent event) {
        if (Client.nullCheck()) {
            return;
        }
        if (this.Sprint.getValue() && !mc.thePlayer.isSprinting()) {
            mc.thePlayer.setSprinting(true);
        }
    }
}
