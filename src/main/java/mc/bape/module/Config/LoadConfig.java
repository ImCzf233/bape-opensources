package mc.bape.module.Config;

import mc.bape.module.*;
import mc.bape.vapu.*;
import mc.bape.manager.*;
import org.lwjgl.input.*;
import mc.bape.values.*;
import mc.bape.utils.*;
import java.util.*;

public class LoadConfig extends Module
{
    public LoadConfig() {
        super("LoadConfig", 0, ModuleType.Global, "Load your configs");
        LoadConfig.Chinese = "\u52a0\u8f7d\u914d\u7f6e";
        LoadConfig.NoToggle = true;
    }
    
    @Override
    public void enable() {
        final List<String> binds = ConfigManager.read(Client.CLIENT_CONFIG + "-binds.cfg");
        for (final String v : binds) {
            final String name = v.split(":")[0];
            final String bind = v.split(":")[1];
            final Module m = ModuleManager.getModule(name);
            if (m == null) {
                continue;
            }
            m.setKey(Keyboard.getKeyIndex(bind.toUpperCase()));
        }
        final List<String> enabled = ConfigManager.read(Client.CLIENT_CONFIG + "-modules.cfg");
        for (final String v2 : enabled) {
            final Module i = ModuleManager.getModule(v2);
            if (i == null) {
                continue;
            }
            i.setState(true);
        }
        final List<String> vals = ConfigManager.read(Client.CLIENT_CONFIG + "-values.cfg");
        for (final String v3 : vals) {
            final String name2 = v3.split(":")[0];
            final String values = v3.split(":")[1];
            final Module j = ModuleManager.getModule(name2);
            if (j == null) {
                continue;
            }
            for (final Value value : j.getValues()) {
                if (!value.getName().equalsIgnoreCase(values)) {
                    continue;
                }
                if (value instanceof Option) {
                    value.setValue(Boolean.parseBoolean(v3.split(":")[2]));
                }
                else if (value instanceof Numbers) {
                    value.setValue(Double.parseDouble(v3.split(":")[2]));
                }
                else {
                    ((Mode)value).setMode(v3.split(":")[2]);
                }
            }
        }
        final List<String> cfg = ConfigManager.read(Client.CLIENT_CONFIG + "-client.cfg");
        if (cfg.size() != 0) {
            Client.CLIENT_NAME = cfg.get(0);
            Helper.sendMessage("Configs Loaded.");
        }
        else {
            Helper.sendMessage("Failed to load config");
        }
        this.state = false;
    }
}
