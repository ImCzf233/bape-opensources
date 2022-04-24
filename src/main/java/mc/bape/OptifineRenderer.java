package mc.bape;

import java.net.*;
import java.lang.reflect.*;

public class OptifineRenderer
{
    public static void inject(final Thread[] threads) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        for (final Thread thread : threads) {
            if (thread != null && thread.getName().equals("Client thread")) {
                final URLClassLoader classLoader = (URLClassLoader)thread.getContextClassLoader();
                Thread.currentThread().setContextClassLoader(classLoader);
                try {
                    final Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    method.invoke(classLoader, OptifineRenderer.class.getProtectionDomain().getCodeSource().getLocation());
                }
                catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex2) {
                    ex2.printStackTrace();
                }
                final Class<?> clazz = classLoader.loadClass("mc.bape.vapu.Client");
                clazz.newInstance();
                break;
            }
        }
    }
}
