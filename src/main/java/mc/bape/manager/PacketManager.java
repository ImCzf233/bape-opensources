// L Bape, Decompiled by ImCzf233

package mc.bape.manager;

import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.*;
import net.minecraft.client.*;
import net.minecraft.network.*;
import mc.bape.utils.*;
import net.minecraftforge.fml.common.network.*;
import io.netty.channel.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PacketManager
{
    public static final PacketManager INSTANCE;
    
    private PacketManager() {
    }
    
    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
    }
    
    public void uninject() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        final Minecraft mc = Minecraft.getMinecraft();
        final NetworkManager netMgr = (NetworkManager)ReflectionUtil.getFieldValue(mc, "myNetworkManager", "myNetworkManager");
        if (netMgr != null) {
            netMgr.channel().pipeline().remove("Bape");
        }
    }
    
    @SubscribeEvent
    public void onJoinServer(final FMLNetworkEvent.ClientConnectedToServerEvent e) {
        final Minecraft mc = Minecraft.getMinecraft();
        NetworkManager netMgr = null;
        if (e == null) {
            netMgr = (NetworkManager)ReflectionUtil.getFieldValue(mc, "myNetworkManager", "myNetworkManager");
        }
        else {
            netMgr = e.manager;
        }
        if (netMgr != null) {
            netMgr.channel().pipeline().addBefore("packet_handler", "Bape", (ChannelHandler)new PacketListener());
        }
    }
    
    static {
        INSTANCE = new PacketManager();
    }
}
