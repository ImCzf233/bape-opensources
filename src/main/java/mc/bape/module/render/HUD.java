// L Bape, Decompiled by ImCzf233

package mc.bape.module.render;

import mc.bape.module.*;
import mc.bape.vapu.*;
import mc.bape.values.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import java.awt.*;
import mc.bape.manager.*;
import net.minecraft.util.*;
import mc.bape.utils.*;
import mc.bape.legit.hud.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class HUD extends Module
{
    private Mode<Enum> mode;
    private Option<Boolean> Health;
    private Option<Boolean> WaterMark;
    private Option<Boolean> ArrayList;
    private Mode<Enum> sexymode;
    public Mode<Enum> NotificationType;
    private int width;
    
    public HUD() {
        super("HUD", 35, ModuleType.Render, "Show " + Client.CLIENT_NAME + " HUD Screen");
        this.mode = new Mode<Enum>("Mode", "mode", MODE.values(), MODE.Text);
        this.Health = new Option<Boolean>("Health", "Health", false);
        this.WaterMark = new Option<Boolean>("WaterMark", "WaterMark", true);
        this.ArrayList = new Option<Boolean>("ArrayList", "ArrayList", true);
        this.sexymode = new Mode<Enum>("Mode", "mode", SEXYMODE.values(), SEXYMODE.None);
        this.NotificationType = new Mode<Enum>("NotificationRenderType", "NotificationRenderType", NofiType.values(), NofiType.Black1);
        this.addValues(this.mode, this.sexymode, this.Health, this.WaterMark, this.ArrayList, this.NotificationType);
        HUD.Chinese = "HUD\u754c\u9762";
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final ScaledResolution s = new ScaledResolution(HUD.mc);
        final int width = new ScaledResolution(HUD.mc).getScaledWidth();
        final int height = new ScaledResolution(HUD.mc).getScaledHeight();
        final int y = 1;
        if (HUD.mc.currentScreen != null && !(HUD.mc.currentScreen instanceof GuiMainMenu)) {
            return;
        }
        if (this.mode.getValue() == MODE.Bape) {
            final String text = Client.CLIENT_NAME;
            FontManager.C18.drawStringWithShadow(" | " + (HUD.mc.isSingleplayer() ? "localhost:25565" : (HUD.mc.getCurrentServerData().serverIP.contains(":") ? HUD.mc.getCurrentServerData().serverIP : (HUD.mc.getCurrentServerData().serverIP + ":25565"))) + " | " + Minecraft.getDebugFPS() + "fps", 65.0, 25.0, new Color(255, 255, 255).hashCode());
            RenderUtils.drawImage(7.0f, 9.0f, 62, 25, new ResourceLocation("JAT/Bape.png"), new Color(255, 255, 255));
        }
        else if (this.mode.getValue() == MODE.Text) {
            FontManager.C18.drawStringWithShadow(" | " + Client.CLIENT_NAME + " | " + (HUD.mc.isSingleplayer() ? "localhost:25565" : (HUD.mc.getCurrentServerData().serverIP.contains(":") ? HUD.mc.getCurrentServerData().serverIP : (HUD.mc.getCurrentServerData().serverIP + ":25565"))) + " | " + Minecraft.getDebugFPS() + "fps", 7.0, 9.0, new Color(255, 255, 255).hashCode());
        }
        else if (this.mode.getValue() == MODE.Rainbow) {
            FontManager.C18.drawStringWithShadow(" | " + Client.CLIENT_NAME + " | " + (HUD.mc.isSingleplayer() ? "localhost:25565" : (HUD.mc.getCurrentServerData().serverIP.contains(":") ? HUD.mc.getCurrentServerData().serverIP : (HUD.mc.getCurrentServerData().serverIP + ":25565"))) + " | " + Minecraft.getDebugFPS() + "fps", 7.0, 9.0, ColorUtils.rainbow(2));
        }
        else if (this.mode.getValue() == MODE.Lunar) {
            RenderUtils.drawImage(7.0f, 9.0f, 70, 70, new ResourceLocation("JAT/Lunar.png"), new Color(220, 220, 220));
            FontManager.C30.drawStringWithShadow("LUNAR CLIENT", 77.0, 25.0, new Color(255, 255, 255).hashCode());
        }
        final ArrayList<Module> modules = new ArrayList<Module>();
        final Client instance = Client.INSTANCE;
        final ModuleManager moduleManager = Client.moduleManager;
        for (final Module m : ModuleManager.getModules()) {
            modules.add(m);
        }
        final int i = 0;
        if (this.Health.getValue()) {
            if (HUD.mc.thePlayer.getHealth() >= 0.0f && HUD.mc.thePlayer.getHealth() < 10.0f) {
                this.width = 3;
            }
            if (HUD.mc.thePlayer.getHealth() >= 10.0f && HUD.mc.thePlayer.getHealth() < 100.0f) {
                this.width = 5;
            }
            HUD.mc.fontRendererObj.drawStringWithShadow("\u2665" + MathHelper.ceiling_float_int(HUD.mc.thePlayer.getHealth()), (float)(new ScaledResolution(HUD.mc).getScaledWidth() / 2 - this.width), (float)(new ScaledResolution(HUD.mc).getScaledHeight() / 2 - 15), -1);
        }
        if (this.sexymode.getValue() == SEXYMODE.SexyHuTao) {
            RenderUtils.drawImage(2.0f, 240.0f, 200, 270, new ResourceLocation("JAT/H.png"), new Color(220, 220, 220));
        }
        else if (this.sexymode.getValue() == SEXYMODE.PaiMon) {
            RenderUtils.drawImage(550.0f, 200.0f, 200, 270, new ResourceLocation("JAT/PaiMon.png"), new Color(220, 220, 220));
        }
        else if (this.sexymode.getValue() == SEXYMODE.None) {}
        if (ModuleManager.getModule("Sprint").getState() && this.mode.getValue() == MODE.Lunar) {
            HUD.mc.fontRendererObj.drawStringWithShadow("Sprinting (Toggled)", (float)(new ScaledResolution(HUD.mc).getScaledWidth() / 2 - 40), 25.0f, new Color(255, 255, 255).hashCode());
        }
        else if (this.mode.getValue() == MODE.Lunar) {
            HUD.mc.fontRendererObj.drawStringWithShadow("Sprinting (Vanilla)", (float)(new ScaledResolution(HUD.mc).getScaledWidth() / 2 - 40), 25.0f, new Color(255, 255, 255).hashCode());
        }
        ClientUtil.drawNotifications();
        if (!(HUD.mc.currentScreen instanceof HUDConfigScreen)) {
            Client.INSTANCE.hudManager.renderMods();
        }
    }
    
    enum MODE
    {
        Bape, 
        Text, 
        Rainbow, 
        Lunar;
    }
    
    public enum NofiType
    {
        Black1, 
        Black2;
    }
    
    enum SEXYMODE
    {
        None, 
        SexyHuTao, 
        PaiMon;
    }
}
