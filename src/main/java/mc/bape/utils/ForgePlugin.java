package mc.bape.utils;

import net.minecraftforge.fml.relauncher.*;
import java.util.*;

@IFMLLoadingPlugin.Name("VapuForgePlugin")
public class ForgePlugin implements IFMLLoadingPlugin
{
    public String[] getASMTransformerClass() {
        return new String[] { "com.xue.vapu.ClassTransformer" };
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
}
