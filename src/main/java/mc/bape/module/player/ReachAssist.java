// L Bape, Decompiled by ImCzf233

package mc.bape.module.player;

import java.util.*;
import mc.bape.module.*;
import net.minecraftforge.client.event.*;
import mc.bape.vapu.*;
import net.minecraft.entity.*;
import mc.bape.manager.*;
import mc.bape.module.combat.*;
import java.awt.*;
import mc.bape.utils.*;
import net.minecraft.scoreboard.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ReachAssist extends Module
{
    public TimerUtil timer;
    public Queue<Nameplate> tags;
    
    public ReachAssist() {
        super("ReachAssist", 0, ModuleType.Player, "Display Reach");
        this.timer = new TimerUtil();
    }
    
    @SubscribeEvent
    public void onPreRender(final RenderPlayerEvent.Pre event) {
        if (Client.nullCheck()) {
            return;
        }
        double v = 0.3;
        final Scoreboard sb = event.entityPlayer.getWorldScoreboard();
        final ScoreObjective sbObj = sb.getObjectiveInDisplaySlot(2);
        if (sbObj != null && !event.entityPlayer.getDisplayNameString().equals(ReachAssist.mc.thePlayer.getDisplayNameString()) && event.entityPlayer.getDistanceSqToEntity((Entity)ReachAssist.mc.thePlayer) < 100.0) {
            v *= 2.0;
        }
        if (event.entityPlayer.isDead) {
            return;
        }
        if (AntiBot.isServerBot((Entity)event.entityPlayer)) {
            return;
        }
        if (event.entityPlayer == ReachAssist.mc.thePlayer) {
            return;
        }
        if (event.entityPlayer.isInvisible()) {
            return;
        }
        String str = (int)ReachAssist.mc.thePlayer.getDistanceSqToEntity((Entity)event.entityPlayer) + "L";
        if (ModuleManager.getModule("Reach").getState()) {
            if (ReachAssist.mc.thePlayer.getDistanceSqToEntity((Entity)event.entityPlayer) / 2.5 < Reach.MinReach.getValue()) {
                str = "¡ì6Attack Ready";
                if (ReachAssist.mc.objectMouseOver.entityHit != null && ReachAssist.mc.objectMouseOver.entityHit == event.entityPlayer) {
                    RenderHelper.drawESP((Entity)event.entityPlayer, new Color(34, 255, 0).getRGB(), true, 2);
                    str = "¡ìaOK";
                }
                else {
                    RenderHelper.drawESP((Entity)event.entityPlayer, new Color(255, 183, 0).getRGB(), true, 2);
                }
            }
        }
        else if (ReachAssist.mc.thePlayer.getDistanceSqToEntity((Entity)event.entityPlayer) / 2.5 < 3.0) {
            str = "¡ì6Attack Ready";
            if (ReachAssist.mc.objectMouseOver.entityHit != null && ReachAssist.mc.objectMouseOver.entityHit == event.entityPlayer) {
                RenderHelper.drawESP((Entity)event.entityPlayer, new Color(34, 255, 0).getRGB(), true, 2);
                str = "¡ìaOK";
            }
            else {
                RenderHelper.drawESP((Entity)event.entityPlayer, new Color(255, 183, 0).getRGB(), true, 2);
            }
        }
        final Nameplate np = new Nameplate(str, event.x - 1.0, event.y - 1.0, event.z + 0.1, event.entityLiving);
        np.renderNewPlate(new Color(150, 150, 150));
    }
}
