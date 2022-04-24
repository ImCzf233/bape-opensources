package mc.bape.event;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.*;

@Cancelable
public class PacketEvent extends Event
{
    private final Packet<?> packet;
    private final Side side;
    
    public PacketEvent(final Packet<?> packet, final Side side) {
        this.packet = packet;
        this.side = side;
    }
    
    public Side getSide() {
        return this.side;
    }
    
    public Packet<?> getPacket() {
        return this.packet;
    }
    
    public enum Side
    {
        CLIENT, 
        SERVER;
    }
}
