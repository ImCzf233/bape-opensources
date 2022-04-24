package mc.bape.manager;

import net.minecraftforge.common.*;
import net.minecraft.network.*;
import mc.bape.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import io.netty.channel.*;

class PacketListener extends ChannelDuplexHandler
{
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        if (!MinecraftForge.EVENT_BUS.post((Event)new PacketEvent((Packet<?>)msg, PacketEvent.Side.SERVER))) {
            super.channelRead(ctx, msg);
        }
    }
    
    public void write(final ChannelHandlerContext ctx, final Object msg, final ChannelPromise promise) throws Exception {
        if (!MinecraftForge.EVENT_BUS.post((Event)new PacketEvent((Packet<?>)msg, PacketEvent.Side.CLIENT))) {
            super.write(ctx, msg, promise);
        }
    }
}
