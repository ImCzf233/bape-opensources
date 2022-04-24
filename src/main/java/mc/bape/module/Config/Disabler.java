// L Bape, Decompiled by ImCzf233

package mc.bape.module.Config;

import mc.bape.utils.*;
import java.util.*;
import net.minecraft.network.*;
import mc.bape.values.*;
import mc.bape.module.*;
import java.util.concurrent.*;
import mc.bape.api.miliblue.events.*;
import mc.bape.api.miliblue.*;
import mc.bape.event.*;
import net.minecraft.network.play.server.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Disabler extends Module
{
    public static float yawDiff;
    Queue<C0FPacketConfirmTransaction> confirmTransactionQueue;
    Queue<C00PacketKeepAlive> keepAliveQueue;
    public static TimerUtil timer;
    TimerUtil lastRelease;
    int lastUid;
    int cancelledPackets;
    public static boolean hasDisabled;
    private final TimerUtil blocksMCTimerUtils;
    private final LinkedList<Packet<?>> blocksMCPacketList;
    private final LinkedList<C0FPacketConfirmTransaction> minePlexPacketList;
    private Mode<Enum> mode;
    
    public Disabler() {
        super("Disabler", 0, ModuleType.Global, "Make Anticheat shutdown");
        this.confirmTransactionQueue = new ConcurrentLinkedQueue<C0FPacketConfirmTransaction>();
        this.keepAliveQueue = new ConcurrentLinkedQueue<C00PacketKeepAlive>();
        this.lastRelease = new TimerUtil();
        this.blocksMCTimerUtils = new TimerUtil();
        this.blocksMCPacketList = new LinkedList<Packet<?>>();
        this.minePlexPacketList = new LinkedList<C0FPacketConfirmTransaction>();
        this.mode = new Mode<Enum>("Mode", "mode", DisablerMode.values(), DisablerMode.Hypixel);
    }
    
    @Override
    public void disable() {
        if (this.mode.getValue() == DisablerMode.BlocksMc) {
            if (!this.blocksMCPacketList.isEmpty()) {
                Packet<?> packet;
                while ((packet = this.blocksMCPacketList.poll()) != null) {
                    Disabler.mc.getNetHandler().addToSendQueue((Packet)packet);
                }
            }
        }
        else if (this.mode.getValue() == DisablerMode.MinePlex && !this.minePlexPacketList.isEmpty()) {
            C0FPacketConfirmTransaction packet2;
            while ((packet2 = this.minePlexPacketList.poll()) != null) {
                Disabler.mc.getNetHandler().addToSendQueue((Packet)packet2);
            }
        }
    }
    
    @EventHandler
    public void onUpdate(final EventPreUpdate e) {
        if (this.mode.getValue() == DisablerMode.BlocksMc) {
            if (this.blocksMCTimerUtils.hasReached(490.0) && !this.blocksMCPacketList.isEmpty()) {
                Disabler.mc.getNetHandler().addToSendQueue((Packet)this.blocksMCPacketList.poll());
            }
            if (Disabler.mc.thePlayer.ticksExisted % 40 == 0) {
                Disabler.mc.getNetHandler().addToSendQueue((Packet)new C0CPacketInput());
                e.setY(e.getY() - 0.114514);
                e.setOnground(false);
            }
        }
        else if (this.mode.getValue() == DisablerMode.MinePlex && Disabler.mc.thePlayer.ticksExisted % 10 == 5) {
            Disabler.mc.getNetHandler().addToSendQueue((Packet)new C0FPacketConfirmTransaction(0, (short)(-1), false));
            Disabler.mc.getNetHandler().addToSendQueue((Packet)new C00PacketKeepAlive(-1));
            e.setY(e.getY() + 1.0E-4);
        }
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent e) {
        if (this.mode.getValue() == DisablerMode.BlocksMc) {
            if (e.getPacket() instanceof C0FPacketConfirmTransaction) {
                this.blocksMCPacketList.add(e.getPacket());
                e.setCanceled(true);
                if (this.blocksMCPacketList.size() > 300) {
                    Disabler.mc.getNetHandler().addToSendQueue((Packet)this.blocksMCPacketList.poll());
                }
            }
            else if (e.getPacket() instanceof S08PacketPlayerPosLook && Disabler.mc.thePlayer.ticksExisted >= 20) {
                final ItemStack stack = Disabler.mc.thePlayer.inventory.mainInventory[0];
                if (stack == null || stack.getItem() != Items.compass || !stack.getDisplayName().endsWith("Game Menu")) {
                    final S08PacketPlayerPosLook packet = (S08PacketPlayerPosLook)e.getPacket();
                    final double x = packet.getX() - Disabler.mc.thePlayer.posX;
                    final double y = packet.getY() - Disabler.mc.thePlayer.posY;
                    final double z = packet.getZ() - Disabler.mc.thePlayer.posZ;
                    final double diff = Math.sqrt(x * x + y * y + z * z);
                    if (diff <= 8.0) {
                        e.setCanceled(true);
                        Disabler.mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(packet.getX(), packet.getY(), packet.getZ(), packet.getYaw(), packet.getPitch(), true));
                    }
                }
            }
            if (Disabler.mc.thePlayer.ticksExisted <= 7) {
                this.blocksMCTimerUtils.reset();
                this.blocksMCPacketList.clear();
            }
        }
    }
    
    static {
        Disabler.timer = new TimerUtil();
    }
    
    enum DisablerMode
    {
        Hypixel, 
        BlocksMc, 
        MinePlex;
    }
}
