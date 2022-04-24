package mc.bape.utils;

import org.apache.commons.lang3.reflect.*;
import net.minecraft.launchwrapper.*;
import java.io.*;
import net.minecraftforge.fml.relauncher.*;
import java.net.*;
import org.apache.logging.log4j.*;
import java.lang.reflect.*;
import java.util.*;
import java.security.*;

@IFMLLoadingPlugin.Name("ProductPlugin")
public class ProductPlugin implements IFMLLoadingPlugin
{
    public ProductPlugin() {
        try {
            final URLClassLoader appClassLoader = (URLClassLoader)Launch.class.getClassLoader();
            final Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            method.invoke(appClassLoader, this.getClass().getProtectionDomain().getCodeSource().getLocation());
            MethodUtils.invokeStaticMethod((Class)appClassLoader.loadClass(this.getClass().getName()), "registerTransformer", new Object[0]);
        }
        catch (Exception e) {
            Tool.logException(e);
        }
    }
    
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
    }
    
    public static void registerTransformer() {
        try {
            final LaunchClassLoader classLoader = Launch.classLoader;
            final Field field = LaunchClassLoader.class.getDeclaredField("transformers");
            field.setAccessible(true);
            final List<IClassTransformer> transformers = (List<IClassTransformer>)field.get(classLoader);
            final IClassTransformer trasformer = (IClassTransformer)Class.forName("gq.vapu.vapu.ClassTransformer", true, (ClassLoader)classLoader).newInstance();
            final ModList list = new ModList();
            field.set(classLoader, list);
            Tool.logerror("interupt transforms list", new Object[0]);
            Tool.logerror("interupt transforms list", new Object[0]);
            Tool.logerror("interupt transforms list", new Object[0]);
            Tool.logerror("interupt transforms list", new Object[0]);
            Tool.logerror("interupt transforms list", new Object[0]);
            for (final IClassTransformer transformer : transformers) {
                list.add(transformer);
                Tool.logerror(transformer.toString(), new Object[0]);
            }
            list.add(trasformer);
            final CodeSource codeSource = ProductPlugin.class.getProtectionDomain().getCodeSource();
            if (codeSource != null) {
                final URL location = codeSource.getLocation();
                try {
                    final File file = new File(location.toURI());
                    if (file.isFile()) {
                        CoreModManager.getIgnoredMods().remove(file.getName());
                    }
                }
                catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            else {
                LogManager.getLogger().warn("No CodeSource, if this is not a development environment we might run into problems!");
                LogManager.getLogger().warn((Object)ProductPlugin.class.getProtectionDomain());
            }
        }
        catch (NoSuchFieldException e2) {
            Tool.logException(e2);
        }
        catch (SecurityException e3) {
            Tool.logException(e3);
        }
        catch (IllegalArgumentException e4) {
            Tool.logException(e4);
        }
        catch (IllegalAccessException e5) {
            Tool.logException(e5);
        }
        catch (InstantiationException e6) {
            Tool.logException(e6);
        }
        catch (ClassNotFoundException e7) {
            Tool.logException(e7);
        }
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
}
