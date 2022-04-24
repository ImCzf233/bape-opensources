package mc.bape.module.Config;

import mc.bape.module.*;
import mc.bape.values.*;
import mc.bape.vapu.*;
import mc.bape.manager.*;
import org.lwjgl.input.*;
import mc.bape.utils.*;
import java.util.*;

public class SaveConfig extends Module
{
    public SaveConfig() {
        super("SaveConfig", 0, ModuleType.Global, "Save your module setting (config)");
        SaveConfig.Chinese = "\u4fdd\u5b58\u914d\u7f6e";
        SaveConfig.NoToggle = true;
    }
    
    @Override
    public void enable() {
        String values = "";
        for (final Module m : ModuleManager.getModules()) {
            for (final Value v : m.getValues()) {
                values = String.valueOf(values) + String.format("%s:%s:%s%s", m.getName(), v.getName(), v.getValue(), System.lineSeparator());
            }
        }
        ConfigManager.save(Client.CLIENT_CONFIG + "-values.cfg", values, false);
        String enabled = "";
        final ArrayList<Module> modules = new ArrayList<Module>();
        for (final Module i : ModuleManager.getModules()) {
            modules.add(i);
        }
        for (final Module i : modules) {
            if (i != null && i.getState() && i != this) {
                enabled = String.valueOf(enabled) + String.format("%s%s", i.getName(), System.lineSeparator());
            }
        }
        String content = "";
        for (final Module i : ModuleManager.getModules()) {
            content += String.format("%s:%s%s", i.getName(), Keyboard.getKeyName(i.getKey()), System.lineSeparator());
        }
        ConfigManager.save(Client.CLIENT_CONFIG + "-binds.cfg", content, false);
        ConfigManager.save(Client.CLIENT_CONFIG + "-modules.cfg", enabled, false);
        ConfigManager.save(Client.CLIENT_CONFIG + "-client.cfg", Client.CLIENT_NAME, false);
        Helper.sendMessage("Configs Saved.");
        this.state = false;
    }
}
