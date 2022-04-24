// L Bape, Decompiled by ImCzf233

package mc.bape.manager;

import mc.bape.gui.font.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import java.io.*;

public abstract class FontManager
{
    public static CFontRenderer F14;
    public static CFontRenderer F16;
    public static CFontRenderer F18;
    public static CFontRenderer F20;
    public static CFontRenderer F22;
    public static CFontRenderer F23;
    public static CFontRenderer F24;
    public static CFontRenderer F30;
    public static CFontRenderer F40;
    public static CFontRenderer C12;
    public static CFontRenderer C14;
    public static CFontRenderer C16;
    public static CFontRenderer C18;
    public static CFontRenderer C20;
    public static CFontRenderer C22;
    public static CFontRenderer C30;
    public static CFontRenderer Logo;
    public static ArrayList<CFontRenderer> fonts;
    
    public static CFontRenderer getFontRender(final int size) {
        return FontManager.fonts.get(size - 10);
    }
    
    public static Font getFont(final int size) {
        Font font;
        try {
            final InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("font/AlibabaSans-Regular.otf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, (float)size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    
    public static Font getComfortaa(final int size) {
        Font font;
        try {
            final InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("font/AlibabaSans-Regular.otf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, (float)size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    
    public static Font getNovo(final int size) {
        Font font;
        try {
            final InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("font/NovICON.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, (float)size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    
    static {
        FontManager.F14 = new CFontRenderer(getFont(14), true, true);
        FontManager.F16 = new CFontRenderer(getFont(16), true, true);
        FontManager.F18 = new CFontRenderer(getFont(18), true, true);
        FontManager.F20 = new CFontRenderer(getFont(20), true, true);
        FontManager.F22 = new CFontRenderer(getFont(22), true, true);
        FontManager.F23 = new CFontRenderer(getFont(23), true, true);
        FontManager.F24 = new CFontRenderer(getFont(24), true, true);
        FontManager.F30 = new CFontRenderer(getFont(30), true, true);
        FontManager.F40 = new CFontRenderer(getFont(40), true, true);
        FontManager.C12 = new CFontRenderer(getComfortaa(12), true, true);
        FontManager.C14 = new CFontRenderer(getComfortaa(14), true, true);
        FontManager.C16 = new CFontRenderer(getComfortaa(16), true, true);
        FontManager.C18 = new CFontRenderer(getComfortaa(18), true, true);
        FontManager.C20 = new CFontRenderer(getComfortaa(20), true, true);
        FontManager.C22 = new CFontRenderer(getComfortaa(22), true, true);
        FontManager.C30 = new CFontRenderer(getComfortaa(30), true, true);
        FontManager.Logo = new CFontRenderer(getNovo(40), true, true);
        FontManager.fonts = new ArrayList<CFontRenderer>();
    }
}
