// L Bape, Decompiled by ImCzf233

package mc.bape.module.render;

import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.player.*;
import mc.bape.module.combat.*;
import net.minecraft.entity.*;
import mc.bape.module.player.*;
import java.awt.*;
import mc.bape.manager.*;
import mc.bape.utils.*;
import java.util.*;

public class Tracers extends Module
{
    private boolean states;
    private Numbers<Double> line;
    private Option<Boolean> invisible;
    
    public Tracers() {
        super("Tracers", 0, ModuleType.Render, "Tracers entitles location");
        this.line = new Numbers<Double>("Tracersline", "Tracersline", 0.5, 0.1, 5.0, 1.0);
        this.invisible = new Option<Boolean>("Invisible", "Invisible", false);
        this.addValues(this.line, this.invisible);
    }
    
    @Override
    public void enable() {
        this.states = Tracers.mc.gameSettings.viewBobbing;
        if (this.states) {
            Tracers.mc.gameSettings.viewBobbing = false;
        }
    }
    
    @SubscribeEvent
    public void update(final TickEvent.PlayerTickEvent event) {
        if (Tracers.mc.gameSettings.viewBobbing) {
            Tracers.mc.gameSettings.viewBobbing = false;
        }
    }
    
    @Override
    public void disable() {
        Tracers.mc.gameSettings.viewBobbing = this.states;
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent ev) {
        for (final EntityPlayer TargetEntity : Tracers.mc.theWorld.playerEntities) {
            if (TargetEntity != Tracers.mc.thePlayer && !AntiBot.isServerBot((Entity)TargetEntity)) {
                if (!this.invisible.getValue() && TargetEntity.isInvisible()) {
                    return;
                }
                int rgb = 0;
                if (Teams.isOnSameTeam((Entity)TargetEntity)) {
                    rgb = new Color(255, 255, 255).getRGB();
                }
                else if (FriendManager.isFriend(TargetEntity.getName())) {
                    rgb = new Color(34, 255, 0, 255).getRGB();
                }
                else {
                    rgb = new Color(255, 0, 0, 255).getRGB();
                }
                RenderHelper.drawTracers((Entity)TargetEntity, rgb, this.line.getValue().floatValue());
            }
        }
    }
}
