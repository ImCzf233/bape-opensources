// L Bape, Decompiled by ImCzf233

package mc.bape.module.Config;

import mc.bape.module.*;
import mc.bape.vapu.*;
import net.minecraftforge.common.*;
import mc.bape.manager.*;
import java.util.*;

public class Uninject extends Module
{
    public Uninject() {
        super("Uninject", 0, ModuleType.Global, "Uninject " + Client.CLIENT_NAME);
        Uninject.Chinese = "\u5378\u8f7d";
        Uninject.NoToggle = true;
    }
    
    @Override
    public void enable() {
        Uninject.mc.thePlayer.closeScreen();
        final ArrayList<Module> modules = new ArrayList<Module>(ModuleManager.getModules());
        for (final Module m : modules) {
            if (m != null) {
                m.setState(false);
            }
        }
        Client.CLIENT_STATE = false;
        if (Client.INSTANCE != null) {
            MinecraftForge.EVENT_BUS.unregister((Object)Client.INSTANCE);
            PacketManager.INSTANCE.uninject();
            Client.INSTANCE = null;
        }
        this.state = false;
    }
}
