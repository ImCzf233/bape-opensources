package mc.bape;

import java.lang.instrument.*;
import net.minecraft.launchwrapper.*;
import mc.bape.vapu.*;

public class TextureFilter
{
    public static void agentmain(final String args, final Instrumentation instrumentation) throws Exception {
        for (final Class<?> classes : instrumentation.getAllLoadedClasses()) {
            if (classes.getName().startsWith("net.minecraft.client.Minecraft")) {
                final LaunchClassLoader classLoader = (LaunchClassLoader)classes.getClassLoader();
                classLoader.addURL(TextureFilter.class.getProtectionDomain().getCodeSource().getLocation());
                final Class<?> client = (Class<?>)classLoader.loadClass(Client.class.getName());
                client.newInstance();
            }
        }
    }
}
