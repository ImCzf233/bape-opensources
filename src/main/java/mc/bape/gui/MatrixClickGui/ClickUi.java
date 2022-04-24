// L Bape, Decompiled by ImCzf233

package mc.bape.gui.MatrixClickGui;

import java.util.*;

import mc.bape.utils.render.ColorUtils;
import mc.bape.values.*;
import mc.bape.module.render.*;
import mc.bape.module.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import mc.bape.utils.render.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import java.awt.*;
import mc.bape.utils.*;
import org.lwjgl.input.*;
import java.io.*;
import mc.bape.manager.*;
import com.google.common.collect.*;

public class ClickUi extends GuiScreen
{
    public static ArrayList<Window> windows;
    public static boolean binding;
    public double opacity;
    public int scrollVelocity;
    private int scroll;
    Mode<Enum> mode;
    
    public ClickUi() {
        this.opacity = 0.0;
        this.mode = ClickGUI.SexyMode;
        this.updateScreen();
        if (ClickUi.windows.isEmpty()) {
            int x = 5;
            for (final ModuleType c : ModuleType.values()) {
                ClickUi.windows.add(new Window(c, x, 5));
                x += 105;
            }
        }
    }
    
    public void drawScreen(final int mouseX, int mouseY, final float partialTicks) {
        GlStateManager.translate(0.0f, (float)this.scroll, 0.0f);
        mouseY -= this.scroll;
        this.opacity = ((this.opacity + 10.0 < 200.0) ? (this.opacity += 10.0) : 200.0);
        drawRect(0, 0, Display.getWidth(), Display.getHeight(), ColorUtils.reAlpha(1, 0.3f));
        final ScaledResolution res = new ScaledResolution(this.mc);
        GlStateManager.pushMatrix();
        final ScaledResolution scaledRes = new ScaledResolution(this.mc);
        final float scale = scaledRes.getScaleFactor() / (float)Math.pow(scaledRes.getScaleFactor(), 2.0);
        final int finalMouseY = mouseY;
        ClickUi.windows.forEach(w -> w.render(mouseX, finalMouseY));
        final int finalMouseY2 = mouseY;
        ClickUi.windows.forEach(w -> w.mouseScroll(mouseX, finalMouseY2, this.scrollVelocity));
        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.popMatrix();
        if (this.mode.getValue() == ClickGUI.SexyMode1.None) {
            RenderUtils.drawImage(800.0f, 600.0f, 100, 55, new ResourceLocation("JAT/BapelogoR.png"), new Color(255, 255, 255));
        }
        else if (this.mode.getValue() == ClickGUI.SexyMode1.Mona) {
            RenderUtils.drawImage(700.0f, 65.0f, 300, 470, new ResourceLocation("JAT/Mona.png"), new Color(220, 220, 220));
            RenderUtils.drawImage(835.0f, 440.0f, 120, 55, new ResourceLocation("JAT/BapelogoR.png"), new Color(255, 255, 255));
        }
        else if (this.mode.getValue() == ClickGUI.SexyMode1.KeQing) {
            RenderUtils.drawImage(2.0f, 65.0f, 300, 470, new ResourceLocation("JAT/H2.png"), new Color(220, 220, 220));
            RenderUtils.drawImage(835.0f, 440.0f, 120, 55, new ResourceLocation("JAT/BapelogoR.png"), new Color(255, 255, 255));
        }
        else if (this.mode.getValue() == ClickGUI.SexyMode1.Misaka) {
            RenderUtils.drawImage(835.0f, 440.0f, 120, 55, new ResourceLocation("JAT/BapelogoR.png"), new Color(255, 255, 255));
            RenderUtils.drawImage(350.0f, 65.0f, 300, 470, new ResourceLocation("JAT/Misaka.png"), new Color(220, 220, 220));
        }
        else if (this.mode.getValue() == ClickGUI.SexyMode1.zerotwo) {
            RenderUtils.drawImage(835.0f, 440.0f, 120, 55, new ResourceLocation("JAT/BapelogoR.png"), new Color(255, 255, 255));
            RenderUtils.drawImage(350.0f, 95.0f, 330, 400, new ResourceLocation("JAT/02.png"), new Color(220, 220, 220));
        }
        if (Mouse.hasWheel()) {
            final int wheel = Mouse.getDWheel();
            this.scrollVelocity = ((wheel < 0) ? -120 : ((wheel > 0) ? 120 : 0));
            if (wheel < 0) {
                this.scroll -= 15;
            }
            else if (wheel > 0) {
                this.scroll += 15;
                if (this.scroll > 0) {
                    this.scroll = 0;
                }
            }
        }
    }
    
    protected void mouseClicked(final int mouseX, int mouseY, final int mouseButton) throws IOException {
        final int finalMouseY;
        mouseY = (finalMouseY = mouseY - this.scroll);
        ClickUi.windows.forEach(w -> w.click(mouseX, finalMouseY, mouseButton));
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (keyCode == 1 && !ClickUi.binding) {
            this.mc.displayGuiScreen((GuiScreen)null);
            return;
        }
        ClickUi.windows.forEach(w -> w.key(typedChar, keyCode));
    }
    
    public void initGui() {
        super.initGui();
    }
    
    public void onGuiClosed() {
        ModuleManager.getModule("ClickGui").setState(false);
    }
    
    public synchronized void sendToFront(final Window window) {
        RenderUtils.drawImage(105.0f, 105.0f, 160, 200, new ResourceLocation("vapeclickgui/module.png"), new Color(220, 220, 220));
        int panelIndex = 0;
        for (int i = 0; i < ClickUi.windows.size(); ++i) {
            if (ClickUi.windows.get(i) == window) {
                panelIndex = i;
                break;
            }
        }
        final Window t = ClickUi.windows.get(ClickUi.windows.size() - 1);
        ClickUi.windows.set(ClickUi.windows.size() - 1, ClickUi.windows.get(panelIndex));
        ClickUi.windows.set(panelIndex, t);
    }
    
    static {
        ClickUi.windows = Lists.newArrayList();
        ClickUi.binding = false;
    }
}
