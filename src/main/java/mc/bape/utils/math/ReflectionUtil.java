// L Bape, Decompiled by ImCzf233

package mc.bape.utils.math;

import net.minecraft.client.*;
import java.lang.reflect.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.settings.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.shader.*;

public class ReflectionUtil
{
    public static final Field delayTimer;
    public static final Field running;
    public static final Field pressed;
    public static final Field theShaderGroup;
    public static final Field listShaders;
    
    public static void rightClickMouse() {
        try {
            final String s = "rightClickMouse";
            final Minecraft mc = Minecraft.getMinecraft();
            final Class<?> c = mc.getClass();
            final Method m = c.getDeclaredMethod(s, (Class<?>[])new Class[0]);
            m.setAccessible(true);
            m.invoke(mc, new Object[0]);
        }
        catch (Exception ex) {}
    }
    
    public static void leftClickMouse() {
        try {
            final String s = "clickMouse";
            final Minecraft mc = Minecraft.getMinecraft();
            final Class<?> c = mc.getClass();
            final Method m = c.getDeclaredMethod(s, (Class<?>[])new Class[0]);
            m.setAccessible(true);
            m.invoke(mc, new Object[0]);
        }
        catch (Exception ex) {}
    }
    
    static {
        delayTimer = ReflectionHelper.findField((Class)Minecraft.class, new String[] { "rightClickDelayTimer", "rightClickDelayTimer" });
        running = ReflectionHelper.findField((Class)Minecraft.class, new String[] { "running", "running" });
        pressed = ReflectionHelper.findField((Class)KeyBinding.class, new String[] { "pressed", "pressed" });
        theShaderGroup = ReflectionHelper.findField((Class)EntityRenderer.class, new String[] { "theShaderGroup", "theShaderGroup" });
        listShaders = ReflectionHelper.findField((Class)ShaderGroup.class, new String[] { "listShaders", "listShaders" });
        ReflectionUtil.delayTimer.setAccessible(true);
        ReflectionUtil.running.setAccessible(true);
        ReflectionUtil.pressed.setAccessible(true);
        ReflectionUtil.theShaderGroup.setAccessible(true);
        ReflectionUtil.listShaders.setAccessible(true);
    }
}
