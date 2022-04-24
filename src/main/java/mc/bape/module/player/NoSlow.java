// L Bape, Decompiled by ImCzf233

package mc.bape.module.player;

import mc.bape.module.*;
import mc.bape.values.*;
import mc.bape.event.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoSlow extends Module
{
    private Mode<Enum> mode;
    
    public NoSlow() {
        super("NoSlow", 0, ModuleType.Player, "Make you no slow.");
        this.mode = new Mode<Enum>("Mode", "mode", NoSlowMode.values(), NoSlowMode.WatchDog);
        this.addValues(this.mode);
        NoSlow.Chinese = "\ufffd\u07bc\ufffd\ufffd\ufffd";
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent event) {
        if (event.getSide() == PacketEvent.Side.CLIENT) {
            if (event.getPacket() instanceof C07PacketPlayerDigging && this.mode.getValue() == NoSlowMode.WatchDog) {
                NoSlow.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(-1, -1, -1), EnumFacing.DOWN));
            }
            else {
                NoSlow.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, (ItemStack)null, 0.0f, 0.0f, 0.0f));
            }
        }
    }
    
    enum NoSlowMode
    {
        WatchDog;
    }
}
