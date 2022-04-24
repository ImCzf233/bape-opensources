package mc.bape.vapu;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;
import java.io.*;
import net.minecraftforge.fml.relauncher.*;

@Mod(modid = "BAPE", name = "BAPE", version = "version", acceptedMinecraftVersions = "[1.8.9]")
public class ForgeMod
{
    @SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void init(final FMLPreInitializationEvent event) throws IOException {
        new Client();
    }
    
    @SideOnly(Side.SERVER)
    @Mod.EventHandler
    public void initServer(final FMLPreInitializationEvent event) {
        System.out.println("fuck you loser");
    }
}
