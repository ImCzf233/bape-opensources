package mc.bape.utils;

import java.awt.*;

public class ColorUtils
{
    public static int rainbow(final int delay) {
        double rainbowState = Math.ceil((double)((System.currentTimeMillis() + delay) / 20L));
        rainbowState %= 360.0;
        return Color.getHSBColor((float)(rainbowState / 360.0), 0.8f, 0.7f).getRGB();
    }
}
