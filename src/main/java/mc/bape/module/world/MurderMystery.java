// L Bape, Decompiled by ImCzf233

package mc.bape.module.world;

import net.minecraft.entity.player.*;
import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.client.event.*;
import mc.bape.module.combat.*;
import net.minecraft.entity.*;
import mc.bape.utils.*;
import java.awt.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.*;
import java.util.List;

public class MurderMystery extends Module
{
    public static boolean a1;
    public static int a2;
    private static List<EntityPlayer> m;
    private static List<EntityPlayer> bw;
    private Option<Boolean> Murder;
    private Option<Boolean> Bow;
    
    public MurderMystery() {
        super("MurderMystery", 0, ModuleType.World, "Detection Murders in Murder game");
        this.Murder = new Option<Boolean>("Tell Everyone Murder", "Tell Everyone Murder", false);
        this.Bow = new Option<Boolean>("Tell Everyone Bow", "Tell Everyone Bow", false);
        this.addValues(this.Murder, this.Bow);
        MurderMystery.Chinese = "\u6740\u624b\u68c0\u67e5";
    }
    
    @Override
    public void disable() {
        MurderMystery.a1 = false;
        MurderMystery.a2 = 0;
    }
    
    @SubscribeEvent
    public void o(final RenderWorldLastEvent ev) {
        if (MurderMystery.mc.thePlayer.getWorldScoreboard() != null && MurderMystery.mc.thePlayer.getWorldScoreboard().getObjectiveInDisplaySlot(1) != null) {
            final String d = MurderMystery.mc.thePlayer.getWorldScoreboard().getObjectiveInDisplaySlot(1).getDisplayName();
            if (d.contains("MURDER") || d.contains("MYSTERY")) {
                for (final EntityPlayer en : MurderMystery.mc.theWorld.playerEntities) {
                    if (en != MurderMystery.mc.thePlayer && !en.isInvisible() && !AntiBot.isServerBot((Entity)en)) {
                        if (en.getHeldItem() != null && en.getHeldItem().hasDisplayName()) {
                            final Item i = en.getHeldItem().getItem();
                            if (i instanceof ItemSword || i instanceof ItemAxe || en.getHeldItem().getDisplayName().replaceAll("¡ì", "").equals("aKnife")) {
                                if (!MurderMystery.m.contains(en)) {
                                    MurderMystery.m.add(en);
                                    MurderMystery.mc.thePlayer.playSound("note.pling", 1.0f, 1.0f);
                                    Helper.sendMessage(en.getName() + " is the murderer!");
                                    if (this.Murder.getValue()) {
                                        MurderMystery.mc.thePlayer.sendChatMessage(en.getName() + " is the murderer!");
                                    }
                                }
                            }
                            else if (i instanceof ItemBow && !MurderMystery.bw.contains(en)) {
                                MurderMystery.bw.add(en);
                                Helper.sendMessage("[WARNING]" + en.getName() + " have bow! he maybe will kill you.");
                                if (this.Bow.getValue()) {
                                    MurderMystery.mc.thePlayer.sendChatMessage(en.getName() + " have bow.");
                                }
                            }
                        }
                        int rgb = Color.green.getRGB();
                        if ((MurderMystery.m.contains(en) && !MurderMystery.bw.contains(en)) || (MurderMystery.m.contains(en) && MurderMystery.bw.contains(en))) {
                            rgb = Color.red.getRGB();
                        }
                        if (MurderMystery.m.contains(en) || !MurderMystery.bw.contains(en)) {
                            continue;
                        }
                        rgb = Color.orange.getRGB();
                    }
                }
            }
            else {
                this.c();
            }
        }
        else {
            this.c();
        }
    }
    
    private void c() {
        if (MurderMystery.m.size() > 0) {
            MurderMystery.m.clear();
        }
        if (MurderMystery.bw.size() > 0) {
            MurderMystery.bw.clear();
        }
    }
    
    static {
        MurderMystery.a1 = false;
        MurderMystery.a2 = 0;
        MurderMystery.m = new ArrayList<EntityPlayer>();
        MurderMystery.bw = new ArrayList<EntityPlayer>();
    }
}
