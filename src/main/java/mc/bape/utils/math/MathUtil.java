// L Bape, Decompiled by ImCzf233

package mc.bape.utils.math;

import java.util.*;
import net.minecraft.util.*;
import java.awt.*;
import java.util.concurrent.*;
import net.minecraft.client.*;
import net.minecraft.potion.*;

public class MathUtil
{
    public static Random random;
    
    public static double toDecimalLength(final double in, final int places) {
        return Double.parseDouble(String.format("%." + places + "f", in));
    }
    
    public static double round(final double in, int places) {
        places = (int)MathHelper.clamp_double((double)places, 0.0, 2.147483647E9);
        return Double.parseDouble(String.format("%." + places + "f", in));
    }
    
    public static String removeColorCode(final String text) {
        String finalText = text;
        if (text.contains("¡ì")) {
            for (int i = 0; i < finalText.length(); ++i) {
                if (Character.toString(finalText.charAt(i)).equals("¡ì")) {
                    try {
                        final String part1 = finalText.substring(0, i);
                        final String part2 = finalText.substring(Math.min(i + 2, finalText.length()), finalText.length());
                        finalText = part1 + part2;
                    }
                    catch (Exception ex) {}
                }
            }
        }
        return finalText;
    }
    
    public static int reAlpha(final int color, final float alpha) {
        final Color c = new Color(color);
        final float r = 0.003921569f * c.getRed();
        final float g = 0.003921569f * c.getGreen();
        final float b = 0.003921569f * c.getBlue();
        return new Color(r, g, b, alpha).getRGB();
    }
    
    public static boolean parsable(final String s, final byte type) {
        try {
            switch (type) {
                case 0: {
                    Short.parseShort(s);
                    break;
                }
                case 1: {
                    Byte.parseByte(s);
                    break;
                }
                case 2: {
                    Integer.parseInt(s);
                    break;
                }
                case 3: {
                    Float.parseFloat(s);
                    break;
                }
                case 4: {
                    Double.parseDouble(s);
                    break;
                }
                case 5: {
                    Long.parseLong(s);
                    break;
                }
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static double square(final double in) {
        return in * in;
    }
    
    public static double randomDouble(final double min, final double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
    
    public static double getBaseMovementSpeed() {
        double baseSpeed = 0.2873;
        if (Minecraft.getMinecraft().thePlayer.isPotionActive(Potion.moveSpeed)) {
            final int amplifier = Minecraft.getMinecraft().thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier();
            baseSpeed *= 1.0 + 0.2 * (amplifier + 1);
        }
        return baseSpeed;
    }
    
    public static float randomFloat(final float min, final float max) {
        return min + MathUtil.random.nextFloat() * (max - min);
    }
    
    public static double getIncremental(final double val, final double inc) {
        final double one = 1.0 / inc;
        return Math.round(val * one) / one;
    }
    
    public static double randomNumber(final double max, final double min) {
        return Math.random() * (max - min) + min;
    }
    
    static {
        MathUtil.random = new Random();
    }
    
    public static class NumberType
    {
        public static final byte SHORT = 0;
        public static final byte BYTE = 1;
        public static final byte INT = 2;
        public static final byte FLOAT = 3;
        public static final byte DOUBLE = 4;
        public static final byte LONG = 5;
        
        public static byte getByType(final Class cls) {
            if (cls == Short.class) {
                return 0;
            }
            if (cls == Byte.class) {
                return 1;
            }
            if (cls == Integer.class) {
                return 2;
            }
            if (cls == Float.class) {
                return 3;
            }
            if (cls == Double.class) {
                return 4;
            }
            if (cls == Long.class) {
                return 5;
            }
            return -1;
        }
    }
}
