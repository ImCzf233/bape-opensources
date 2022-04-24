// L Bape, Decompiled by ImCzf233

package mc.bape.module.player;

import java.util.*;
import mc.bape.utils.*;
import mc.bape.module.*;
import net.minecraftforge.client.event.*;
import mc.bape.vapu.*;
import net.minecraft.entity.*;
import com.mojang.realmsclient.gui.*;
import java.awt.*;
import net.minecraft.scoreboard.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NameTags extends Module
{
    public TimerUtil timer;
    public Queue<Nameplate> tags;
    
    public NameTags() {
        super("NameTags", 0, ModuleType.Render, "Render facing NameTags for targets");
        this.timer = new TimerUtil();
        NameTags.Chinese = "NameTags";
    }
    
    @SubscribeEvent
    public void onPreRender(final RenderPlayerEvent.Pre event) {
        if (Client.nullCheck()) {
            return;
        }
        double v = 0.3;
        final Scoreboard sb = event.entityPlayer.getWorldScoreboard();
        final ScoreObjective sbObj = sb.getObjectiveInDisplaySlot(2);
        if (sbObj != null && !event.entityPlayer.getDisplayNameString().equals(NameTags.mc.thePlayer.getDisplayNameString()) && event.entityPlayer.getDistanceSqToEntity((Entity)NameTags.mc.thePlayer) < 100.0) {
            v *= 2.0;
        }
        if (!event.entityPlayer.getDisplayName().equals(NameTags.mc.thePlayer.getDisplayName())) {
            ChatFormatting Format = ChatFormatting.WHITE;
            if (event.entityPlayer.getHealth() > 15.0f) {
                Format = ChatFormatting.WHITE;
            }
            else if (event.entityPlayer.getHealth() > 8.0f && event.entityPlayer.getHealth() < 15.0f) {
                Format = ChatFormatting.YELLOW;
            }
            else {
                Format = ChatFormatting.RED;
            }
            final Nameplate np = new Nameplate(event.entityPlayer.getDisplayNameString(), event.x, event.y, event.z, event.entityLiving);
            np.renderNewPlate(new Color(150, 150, 150));
        }
    }
}
