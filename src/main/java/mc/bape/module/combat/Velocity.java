// L Bape, Decompiled by ImCzf233

package mc.bape.module.combat;

import mc.bape.module.*;
import mc.bape.values.*;
import mc.bape.event.*;
import mc.bape.vapu.*;
import org.lwjgl.input.*;
import mc.bape.utils.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Velocity extends Module
{
    private Numbers<Double> horizontal;
    private Numbers<Double> vertical;
    private Numbers<Double> chance;
    private Option<Boolean> Targeting;
    private Option<Boolean> NoSword;
    
    public Velocity() {
        super("Velocity", 0, ModuleType.Combat, "Reduces your knockback");
        this.horizontal = new Numbers<Double>("Horizontal", "Horizontal", 96.0, 0.0, 100.0, 1.0);
        this.vertical = new Numbers<Double>("Vertical", "Vertical", 100.0, 0.0, 100.0, 1.0);
        this.chance = new Numbers<Double>("Chance", "Chance", 100.0, 0.0, 100.0, 1.0);
        this.Targeting = new Option<Boolean>("On Targeting", "On Targeting", false);
        this.NoSword = new Option<Boolean>("No Sword", "No Sword", false);
        this.addValues(this.horizontal, this.vertical, this.chance, this.Targeting, this.NoSword);
        Velocity.Chinese = "\u53cd\u51fb\u9000";
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent e) {
        if (Client.nullCheck()) {
            return;
        }
        if (e.getPacket() instanceof S12PacketEntityVelocity) {
            if (this.Targeting.getValue() && (Velocity.mc.objectMouseOver == null || Velocity.mc.objectMouseOver.entityHit == null)) {
                return;
            }
            if (this.NoSword.getValue() && Keyboard.isKeyDown(Velocity.mc.gameSettings.keyBindBack.getKeyCode())) {
                return;
            }
            final S12PacketEntityVelocity packet = (S12PacketEntityVelocity)e.getPacket();
            if (packet.getEntityID() != Velocity.mc.thePlayer.getEntityId()) {
                return;
            }
            double motionX = packet.getMotionX();
            double motionY = packet.getMotionY();
            double motionZ = packet.getMotionZ();
            if (this.chance.getValue() != 100.0) {
                final double ch = Math.random();
                if (ch >= this.chance.getValue() / 100.0) {
                    return;
                }
            }
            if (this.horizontal.getValue() != 100.0) {
                motionX *= this.horizontal.getValue() / 100.0;
                motionZ *= this.horizontal.getValue() / 100.0;
            }
            if (this.vertical.getValue() != 100.0) {
                motionY *= this.vertical.getValue() / 100.0;
            }
            ReflectionUtil.setFieldValue(packet, (int)motionX, "motionX", "motionX");
            ReflectionUtil.setFieldValue(packet, (int)motionY, "motionY", "motionY");
            ReflectionUtil.setFieldValue(packet, (int)motionZ, "motionZ", "motionZ");
        }
        if (e.getPacket() instanceof S27PacketExplosion) {
            if (this.Targeting.getValue() && (Velocity.mc.objectMouseOver == null || Velocity.mc.objectMouseOver.entityHit == null)) {
                return;
            }
            if (this.NoSword.getValue() && Keyboard.isKeyDown(Velocity.mc.gameSettings.keyBindBack.getKeyCode())) {
                return;
            }
            final S27PacketExplosion packet2 = (S27PacketExplosion)e.getPacket();
            float motionX2 = packet2.func_149149_c();
            float motionY2 = packet2.func_149144_d();
            float motionZ2 = packet2.func_149147_e();
            if (this.chance.getValue() != 100.0) {
                final double ch2 = Math.random();
                if (ch2 >= this.chance.getValue() / 100.0) {
                    return;
                }
            }
            if (this.horizontal.getValue() != 100.0) {
                motionX2 *= (float)(this.horizontal.getValue() / 100.0);
                motionZ2 *= (float)(this.horizontal.getValue() / 100.0);
            }
            if (this.vertical.getValue() != 100.0) {
                motionY2 *= (float)(this.vertical.getValue() / 100.0);
            }
            ReflectionUtil.setFieldValue(packet2, motionX2, "field_149152_f");
            ReflectionUtil.setFieldValue(packet2, motionY2, "field_149153_g");
            ReflectionUtil.setFieldValue(packet2, motionZ2, "field_149159_h");
        }
    }
}
