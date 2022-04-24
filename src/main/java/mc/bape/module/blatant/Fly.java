// L Bape, Decompiled by ImCzf233

package mc.bape.module.blatant;

import net.minecraft.network.*;
import mc.bape.utils.*;
import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraft.util.*;
import mc.bape.vapu.*;
import java.util.*;

import net.minecraft.util.Timer;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.network.play.client.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Fly extends Module
{
    private float Flying;
    private int time;
    private int i;
    private float stage;
    private int ticks;
    private boolean doFly;
    private double x;
    private double y;
    private double z;
    private ArrayList<Packet> packets;
    private boolean hasClipped;
    private double speedStage;
    private boolean t;
    private final TimerUtil timer;
    private Mode<Enum> mode;
    
    public Fly() {
        super("Fly", 0, ModuleType.Blatant, "Make you can flying");
        this.packets = new ArrayList<Packet>();
        this.timer = new TimerUtil();
        this.mode = new Mode<Enum>("Mode", "mode", FlyModes.values(), FlyModes.Zoom);
        this.addValues(this.mode);
    }
    
    @Override
    public void enable() {
        this.Flying = Fly.mc.thePlayer.stepHeight;
    }
    
    @Override
    public void disable() {
        this.i = 0;
        if (Objects.requireNonNull(Client.getTimer()).timerSpeed != 1.0f) {
            Client.getTimer().timerSpeed = 1.0f;
        }
        Client.getTimer().timerSpeed = 1.0f;
        Fly.mc.thePlayer.stepHeight = this.Flying;
        if (Fly.mc.thePlayer.capabilities.isFlying) {
            Fly.mc.thePlayer.capabilities.isFlying = false;
        }
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent Event) {
        if (this.mode.getValue() != FlyModes.Vanilla && Fly.mc.thePlayer.capabilities.isFlying) {
            Fly.mc.thePlayer.capabilities.isFlying = false;
        }
        if (this.mode.getValue() == FlyModes.FastTimer) {
            final EntityPlayerSP thePlayer = Fly.mc.thePlayer;
            final EntityPlayerSP thePlayer2 = Fly.mc.thePlayer;
            final float n = 0.05f;
            thePlayer2.cameraPitch = n;
            thePlayer.cameraYaw = n;
            Fly.mc.thePlayer.posY = this.y;
            if (Fly.mc.thePlayer.onGround && this.stage == 0.0f) {
                Fly.mc.thePlayer.motionY = 0.09;
            }
            ++this.stage;
            if (Fly.mc.thePlayer.onGround && this.stage > 2.0f && !this.hasClipped) {
                Fly.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(Fly.mc.thePlayer.posX, Fly.mc.thePlayer.posY - 0.15, Fly.mc.thePlayer.posZ, false));
                Fly.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(Fly.mc.thePlayer.posX, Fly.mc.thePlayer.posY + 0.15, Fly.mc.thePlayer.posZ, true));
                this.hasClipped = true;
            }
            if (this.doFly) {
                Fly.mc.thePlayer.motionY = 0.0;
                Fly.mc.thePlayer.onGround = true;
                Objects.requireNonNull(Client.getTimer()).timerSpeed = 2.0f;
            }
            else {
                Client.getTimer().timerSpeed = 5.0f;
            }
        }
        if (this.mode.getValue() == FlyModes.OldWatchdog) {
            Fly.mc.thePlayer.motionY = 0.0;
            Fly.mc.thePlayer.motionX = 0.0;
            Fly.mc.thePlayer.motionZ = 0.0;
            final double yaw = Math.toRadians(Fly.mc.thePlayer.rotationYaw);
            final double x = -Math.sin(yaw) * 6.0;
            final double z = Math.cos(yaw) * 6.0;
            if (this.timer.hasReached(1500.0)) {
                Fly.mc.thePlayer.setPosition(Fly.mc.thePlayer.posX + x, Fly.mc.thePlayer.posY - 2.0, Fly.mc.thePlayer.posZ + z);
                this.timer.reset();
            }
        }
        if (this.mode.getValue() == FlyModes.Zoom) {
            Fly.mc.thePlayer.motionY = 0.0;
            Fly.mc.thePlayer.onGround = true;
            for (int i = 0; i < 3; ++i) {
                Fly.mc.thePlayer.setPosition(Fly.mc.thePlayer.posX, Fly.mc.thePlayer.posY + 3.0E-12, Fly.mc.thePlayer.posZ);
                if (Fly.mc.thePlayer.ticksExisted % 3 == 0) {
                    Fly.mc.thePlayer.setPosition(Fly.mc.thePlayer.posX, Fly.mc.thePlayer.posY - 3.0E-12, Fly.mc.thePlayer.posZ);
                }
            }
        }
        else if (this.mode.getValue() == FlyModes.Guardian) {
            if (Fly.mc.gameSettings.keyBindForward.isKeyDown()) {
                Fly.mc.thePlayer.stepHeight = 0.0f;
                ++this.time;
                if (this.time == 2) {
                    Fly.mc.thePlayer.setPosition(Fly.mc.thePlayer.posX, Fly.mc.thePlayer.posY + 1.0E-10, Fly.mc.thePlayer.posZ);
                    this.time = 0;
                }
                Fly.mc.thePlayer.motionY = 0.0;
                Fly.mc.thePlayer.onGround = true;
            }
            if (Fly.mc.gameSettings.keyBindJump.isPressed()) {
                Fly.mc.thePlayer.setPosition(Fly.mc.thePlayer.posX, Fly.mc.thePlayer.posY + 0.4, Fly.mc.thePlayer.posZ);
            }
        }
        else if (this.mode.getValue() == FlyModes.Timer) {
            if (Objects.requireNonNull(Client.getTimer()).timerSpeed >= 2.0f) {
                Objects.requireNonNull(Client.getTimer()).timerSpeed = 1.0f;
            }
            Fly.mc.thePlayer.motionY = 0.0;
            Fly.mc.thePlayer.setPosition(Fly.mc.thePlayer.posX, Fly.mc.thePlayer.posY + 0.005, Fly.mc.thePlayer.posZ);
            Fly.mc.thePlayer.setPosition(Fly.mc.thePlayer.posX, Fly.mc.thePlayer.posY - 0.005, Fly.mc.thePlayer.posZ);
            if (Fly.mc.thePlayer.ticksExisted % 3 == 0) {
                Fly.mc.thePlayer.setPosition(Fly.mc.thePlayer.posX, Fly.mc.thePlayer.posY + 0.001, Fly.mc.thePlayer.posZ);
                Fly.mc.thePlayer.setPosition(Fly.mc.thePlayer.posX, Fly.mc.thePlayer.posY + 1.0E-9, Fly.mc.thePlayer.posZ);
            }
            if (Fly.mc.thePlayer.ticksExisted % 5 == 0) {
                if (Fly.mc.thePlayer.moveForward != 0.0f || Fly.mc.thePlayer.moveStrafing != 0.0f) {
                    final Timer vaputimer = Client.getTimer();
                    assert vaputimer != null;
                    final Timer timer = vaputimer;
                    timer.timerSpeed += 0.05f;
                }
                else {
                    Objects.requireNonNull(Client.getTimer()).timerSpeed = 1.0f;
                }
            }
            Fly.mc.thePlayer.onGround = true;
        }
        else if (this.mode.getValue() == FlyModes.Vanilla) {
            Fly.mc.thePlayer.capabilities.isFlying = true;
            if (Fly.mc.gameSettings.keyBindJump.isPressed()) {
                final EntityPlayerSP thePlayer3;
                final EntityPlayerSP thePlayer = thePlayer3 = Fly.mc.thePlayer;
                thePlayer3.motionY += 0.2;
            }
            if (Fly.mc.gameSettings.keyBindSneak.isPressed()) {
                final EntityPlayerSP thePlayer4;
                final EntityPlayerSP thePlayer2 = thePlayer4 = Fly.mc.thePlayer;
                thePlayer4.motionY -= 0.2;
            }
            if (Fly.mc.gameSettings.keyBindForward.isPressed()) {
                Fly.mc.thePlayer.capabilities.setFlySpeed(0.1f);
            }
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent e) {
        if (e.phase.equals((Object)TickEvent.Phase.END) && this.mode.getValue() == FlyModes.Zoom && Fly.mc.gameSettings.keyBindJump.isPressed()) {
            Fly.mc.thePlayer.jump();
            Fly.mc.thePlayer.motionY = 0.41999998688697815;
            Fly.mc.thePlayer.onGround = true;
        }
    }
    
    enum FlyModes
    {
        Zoom, 
        FastTimer, 
        Guardian, 
        Timer, 
        OldWatchdog, 
        Vanilla;
    }
}
