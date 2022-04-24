// L Bape, Decompiled by ImCzf233

package mc.bape.module.blatant;

import mc.bape.utils.*;
import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import mc.bape.event.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;

public class Criticals extends Module
{
    public int noBlockTimer;
    public static final Mode<Enum> mode;
    private final TimerUtil timer;
    public static Numbers<Double> delay;
    
    public Criticals() {
        super("Criticals", 0, ModuleType.Blatant, "Make you Criticals on Attack");
        this.noBlockTimer = 0;
        this.timer = new TimerUtil();
        this.addValues(Criticals.mode);
        Criticals.Chinese = "\u5200\u5200\u66b4\u51fb";
    }
    
    public static boolean canJump() {
        return !Criticals.mc.thePlayer.isOnLadder() && !Criticals.mc.thePlayer.isInWater() && !Criticals.mc.thePlayer.isInLava() && !Criticals.mc.thePlayer.isSneaking() && !Criticals.mc.thePlayer.isRiding();
    }
    
    public boolean canCrit() {
        return Criticals.mc.thePlayer.onGround && !Criticals.mc.thePlayer.isInWater();
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent event) {
        this.status = Criticals.mode.getValue().toString();
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent e) {
        if (e.getSide() == PacketEvent.Side.CLIENT) {
            if (e.getPacket() instanceof C02PacketUseEntity && this.canCrit() && Criticals.mode.getValue() == CriticalsMode.Jump) {
                Criticals.mc.thePlayer.jump();
            }
            if (e.getPacket() instanceof C02PacketUseEntity && this.canCrit() && Criticals.mode.getValue() == CriticalsMode.Watchdog) {
                Criticals.mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(Criticals.mc.thePlayer.posX, Criticals.mc.thePlayer.posY + 0.114514, Criticals.mc.thePlayer.posZ, false));
                Criticals.mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(Criticals.mc.thePlayer.posX, Criticals.mc.thePlayer.posY + 0.0114514, Criticals.mc.thePlayer.posZ, false));
                Criticals.mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(Criticals.mc.thePlayer.posX, Criticals.mc.thePlayer.posY + 0.014514, Criticals.mc.thePlayer.posZ, false));
            }
        }
    }
    
    public static void doWatchdogCirt() {
        Criticals.mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(Criticals.mc.thePlayer.posX, Criticals.mc.thePlayer.posY + 0.114514, Criticals.mc.thePlayer.posZ, false));
        Criticals.mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(Criticals.mc.thePlayer.posX, Criticals.mc.thePlayer.posY + 0.0114514, Criticals.mc.thePlayer.posZ, false));
        Criticals.mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(Criticals.mc.thePlayer.posX, Criticals.mc.thePlayer.posY + 0.014514, Criticals.mc.thePlayer.posZ, false));
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent event) {
        if (Criticals.mode.getValue() == CriticalsMode.Legit && Criticals.mc.gameSettings.keyBindAttack.isKeyDown() && Criticals.mc.objectMouseOver.entityHit != null && canJump() && Criticals.mc.thePlayer.onGround) {
            Criticals.mc.thePlayer.jump();
            Criticals.mc.playerController.attackEntity((EntityPlayer)Criticals.mc.thePlayer, Minecraft.getMinecraft().objectMouseOver.entityHit);
        }
    }
    
    static {
        mode = new Mode<Enum>("Mode", "mode", CriticalsMode.values(), CriticalsMode.Jump);
        Criticals.delay = new Numbers<Double>("Delay", "delay", 50.0, 0.0, 100.0, 10.0);
    }
    
    enum CriticalsMode
    {
        Legit, 
        Jump, 
        Watchdog;
    }
}
