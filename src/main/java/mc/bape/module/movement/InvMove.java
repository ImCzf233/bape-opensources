// L Bape, Decompiled by ImCzf233

package mc.bape.module.movement;

import mc.bape.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraftforge.fml.common.eventhandler.*;
import mc.bape.utils.*;
import net.minecraft.util.*;
import net.minecraft.client.entity.*;
import org.lwjgl.input.*;

public class InvMove extends Module
{
    public InvMove() {
        super("InvMove", 0, ModuleType.Movement, "Make you can move when you open inventory");
        InvMove.Chinese = "\u80cc\u5305\u8d70\u8def";
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (!(InvMove.mc.currentScreen instanceof GuiContainer)) {
            return;
        }
        double speed = 0.05;
        if (!InvMove.mc.thePlayer.onGround) {
            speed /= 4.0;
        }
        this.handleJump();
        this.handleForward(speed);
        if (!InvMove.mc.thePlayer.onGround) {
            speed /= 2.0;
        }
        this.handleBack(speed);
        this.handleLeft(speed);
        this.handleRight(speed);
    }
    
    void moveForward(final double speed) {
        final float direction = ClientUtil.getDirection();
        final EntityPlayerSP thePlayer = InvMove.mc.thePlayer;
        thePlayer.motionX -= MathHelper.sin(direction) * speed;
        final EntityPlayerSP thePlayer2 = InvMove.mc.thePlayer;
        thePlayer2.motionZ += MathHelper.cos(direction) * speed;
    }
    
    void moveBack(final double speed) {
        final float direction = ClientUtil.getDirection();
        final EntityPlayerSP thePlayer = InvMove.mc.thePlayer;
        thePlayer.motionX += MathHelper.sin(direction) * speed;
        final EntityPlayerSP thePlayer2 = InvMove.mc.thePlayer;
        thePlayer2.motionZ -= MathHelper.cos(direction) * speed;
    }
    
    void moveLeft(final double speed) {
        final float direction = ClientUtil.getDirection();
        final EntityPlayerSP thePlayer = InvMove.mc.thePlayer;
        thePlayer.motionZ += MathHelper.sin(direction) * speed;
        final EntityPlayerSP thePlayer2 = InvMove.mc.thePlayer;
        thePlayer2.motionX += MathHelper.cos(direction) * speed;
    }
    
    void moveRight(final double speed) {
        final float direction = ClientUtil.getDirection();
        final EntityPlayerSP thePlayer = InvMove.mc.thePlayer;
        thePlayer.motionZ -= MathHelper.sin(direction) * speed;
        final EntityPlayerSP thePlayer2 = InvMove.mc.thePlayer;
        thePlayer2.motionX -= MathHelper.cos(direction) * speed;
    }
    
    void handleForward(final double speed) {
        if (!Keyboard.isKeyDown(InvMove.mc.gameSettings.keyBindForward.getKeyCode())) {
            return;
        }
        this.moveForward(speed);
    }
    
    void handleBack(final double speed) {
        if (!Keyboard.isKeyDown(InvMove.mc.gameSettings.keyBindBack.getKeyCode())) {
            return;
        }
        this.moveBack(speed);
    }
    
    void handleLeft(final double speed) {
        if (!Keyboard.isKeyDown(InvMove.mc.gameSettings.keyBindLeft.getKeyCode())) {
            return;
        }
        this.moveLeft(speed);
    }
    
    void handleRight(final double speed) {
        if (!Keyboard.isKeyDown(InvMove.mc.gameSettings.keyBindRight.getKeyCode())) {
            return;
        }
        this.moveRight(speed);
    }
    
    void handleJump() {
        if (InvMove.mc.thePlayer.onGround && Keyboard.isKeyDown(InvMove.mc.gameSettings.keyBindJump.getKeyCode())) {
            InvMove.mc.thePlayer.jump();
        }
    }
}
