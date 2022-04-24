// L Bape, Decompiled by ImCzf233

package mc.bape.module.combat;

import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;
import mc.bape.manager.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class AntiBot extends Module
{
    private static Mode<Enum> mode;
    
    public AntiBot() {
        super("AntiBot", 0, ModuleType.Combat, "Make target exclude the bots");
        this.addValues(AntiBot.mode);
        AntiBot.Chinese = "\u53cd\u673a\u5668\u4eba";
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent e) {
        this.status = AntiBot.mode.getValue().toString();
    }
    
    public static double getEntitySpeed(final Entity entity) {
        final double xDif = entity.posX - entity.prevPosX;
        final double zDif = entity.posZ - entity.prevPosZ;
        return Math.sqrt(xDif * xDif + zDif * zDif) * 20.0;
    }
    
    public static boolean isServerBot(final Entity entity) {
        if (Objects.requireNonNull(ModuleManager.getModule("AntiBot")).getState()) {
            if (AntiBot.mode.getValue() == antibotmode.Hypixel) {
                for (final Entity entitys : AntiBot.mc.theWorld.loadedEntityList) {
                    if (!entity.getDisplayName().getFormattedText().startsWith("¡ì") || entity.isInvisible() || entity.getDisplayName().getFormattedText().toLowerCase().contains("npc")) {
                        return true;
                    }
                }
            }
            else if (AntiBot.mode.getValue() == antibotmode.Mineplex) {
                for (final Object object : AntiBot.mc.theWorld.playerEntities) {
                    final EntityPlayer entityPlayer = (EntityPlayer)object;
                    if (entityPlayer != null && entityPlayer != AntiBot.mc.thePlayer) {
                        if (!entityPlayer.getName().startsWith("Body #") && entityPlayer.getMaxHealth() != 20.0f) {
                            continue;
                        }
                        return true;
                    }
                }
            }
            else if (AntiBot.mode.getValue() == antibotmode.Syuu) {
                for (final Entity entitys : AntiBot.mc.theWorld.loadedEntityList) {
                    if (entity == AntiBot.mc.thePlayer) {
                        continue;
                    }
                    if (!(entity instanceof EntityPlayer)) {
                        continue;
                    }
                    final EntityPlayer entityPlayer = (EntityPlayer)entity;
                    if (entityPlayer.isInvisible() && entityPlayer.getHealth() > 1000.0f && getEntitySpeed((Entity)entityPlayer) > 20.0) {
                        return true;
                    }
                }
            }
            else if (AntiBot.mode.getValue() == antibotmode.Vanilla && (!entity.getDisplayName().getFormattedText().startsWith("¡ì") || entity.isInvisible() || entity.getDisplayName().getFormattedText().toLowerCase().contains("npc"))) {
                return true;
            }
        }
        return false;
    }
    
    static {
        AntiBot.mode = new Mode<Enum>("Mode", "mode", antibotmode.values(), antibotmode.Hypixel);
    }
    
    enum antibotmode
    {
        Hypixel, 
        Mineplex, 
        Syuu, 
        Vanilla;
    }
}
