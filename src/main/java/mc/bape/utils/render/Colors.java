package mc.bape.utils.render;

import java.awt.*;

public enum Colors
{
    BLACK(-16711423), 
    BLUE(-12028161), 
    DARKBLUE(-12621684), 
    GREEN(-9830551), 
    DARKGREEN(-9320847), 
    WHITE(-65794), 
    AQUA(-7820064), 
    DARKAQUA(-12621684), 
    GREY(-9868951), 
    DARKGREY(-14342875), 
    RED(-65536), 
    DARKRED(-8388608), 
    ORANGE(-29696), 
    DARKORANGE(-2263808), 
    YELLOW(-256), 
    DARKYELLOW(-2702025), 
    MAGENTA(-18751), 
    SLOWLY(-13220000), 
    SLOWLY2(-106165216), 
    SLOWLY3(-17791255), 
    SLOWLY4(-15425034), 
    DARKMAGENTA(-2252579);
    
    public int c;
    
    private Colors(final int co) {
        this.c = co;
    }
    
    public static int getColor(final Color color) {
        return getColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    
    public static int getColor(final int brightness) {
        return getColor(brightness, brightness, brightness, 255);
    }
    
    public static int getColor(final int brightness, final int alpha) {
        return getColor(brightness, brightness, brightness, alpha);
    }
    
    public static int getColor(final int red, final int green, final int blue) {
        return getColor(red, green, blue, 255);
    }
    
    public static int getColor(final int red, final int green, final int blue, final int alpha) {
        int color = 0;
        color |= alpha << 24;
        color |= red << 16;
        color |= green << 8;
        color |= blue;
        return color;
    }
    
    public static int getEnemyInvisible() {
        return getColor(255, 255, 0, 255);
    }
    
    public static int getEnemyVisible() {
        return getColor(255, 0, 0, 255);
    }
}
