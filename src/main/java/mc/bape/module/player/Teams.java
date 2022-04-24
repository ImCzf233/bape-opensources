// L Bape, Decompiled by ImCzf233

package mc.bape.module.player;

import mc.bape.module.*;
import net.minecraft.entity.*;
import mc.bape.manager.*;
import java.util.*;
import net.minecraft.entity.player.*;

public class Teams extends Module
{
    public Teams() {
        super("Teams", 0, ModuleType.Other, "Not attack player you team");
        Teams.Chinese = "\u56e2\u961f";
    }
    
    public static boolean isOnSameTeam(final Entity entity) {
        if (!Objects.requireNonNull(ModuleManager.getModule("Teams").getState())) {
            return false;
        }
        if (Teams.mc.thePlayer == null) {
            return false;
        }
        if (!(entity instanceof EntityPlayer)) {
            return false;
        }
        final EntityPlayer entity2 = (EntityPlayer)entity;
        if (Teams.mc.thePlayer.getTeam() != null && entity2.getTeam() != null && Teams.mc.thePlayer.getTeam().isSameTeam(entity2.getTeam())) {
            return true;
        }
        if (Teams.mc.thePlayer.getDisplayName() != null && entity2.getDisplayName() != null) {
            final String targetName = entity2.getDisplayName().getFormattedText().replace("¡ìr", "");
            final String clientName = Teams.mc.thePlayer.getDisplayName().getFormattedText().replace("¡ìr", "");
            if (targetName.startsWith("T") && clientName.startsWith("T")) {
                return targetName.charAt(1) == clientName.charAt(1);
            }
        }
        if (Teams.mc.thePlayer.getDisplayName() != null && entity2.getDisplayName() != null) {
            final String targetName = entity2.getDisplayName().getFormattedText().replace("¡ìr", "");
            final String clientName = Teams.mc.thePlayer.getDisplayName().getFormattedText().replace("¡ìr", "");
            return targetName.startsWith("¡ì" + clientName.charAt(1));
        }
        return false;
    }
}
