package mc.bape.manager;

import mc.bape.module.*;
import java.util.*;
import mc.bape.module.movement.*;
import mc.bape.module.blatant.*;
import mc.bape.module.combat.*;
import mc.bape.module.other.*;
import mc.bape.module.world.*;
import mc.bape.module.Config.*;
import mc.bape.module.player.*;
import mc.bape.module.render.*;

public class ModuleManager
{
    static ArrayList<Module> Modules;
    
    public static ArrayList<Module> getModules() {
        return ModuleManager.Modules;
    }
    
    public static void registerModule(final Module tagrtModule) {
        ModuleManager.Modules.add(tagrtModule);
    }
    
    public static Module getModule(final String name) {
        for (final Module m : ModuleManager.Modules) {
            if (m.getName().toLowerCase().equalsIgnoreCase(name.toLowerCase())) {
                return m;
            }
        }
        return null;
    }
    
    public static List<Module> getModulesInType(final ModuleType t) {
        final ArrayList<Module> output = new ArrayList<Module>();
        final ArrayList<Module> module = new ArrayList<Module>();
        module.addAll(module);
        for (final Module m : module) {
            if (m.getCategory() != t) {
                continue;
            }
            output.add(m);
        }
        return output;
    }
    
    static {
        ModuleManager.Modules = new ArrayList<Module>();
        registerModule(new InvManager());
        registerModule(new AutoArmor());
        registerModule(new AutoGG());
        registerModule(new AntiBot());
        registerModule(new Speed());
        registerModule(new Sprint());
        registerModule(new ClickGUI());
        registerModule(new IGN());
        registerModule(new StateMessage());
        registerModule(new HUD());
        registerModule(new FullBright());
        registerModule(new AutoTools());
        registerModule(new AutoL());
        registerModule(new LoadConfig());
        registerModule(new SaveConfig());
        registerModule(new Aimbot());
        registerModule(new Uninject());
        registerModule(new InvMove());
        registerModule(new Killaura());
        registerModule(new BowAimBot());
        registerModule(new AutoMLG());
        registerModule(new MurderMystery());
        registerModule(new Reach());
        registerModule(new StorageESP());
        registerModule(new ESP());
        registerModule(new ChestStealer());
        registerModule(new AntiAFK());
        registerModule(new AutoClicker());
        registerModule(new NoCommand());
        registerModule(new Scaffold());
        registerModule(new Criticals());
        registerModule(new Teams());
        registerModule(new Tracers());
        registerModule(new Fly());
        registerModule(new Velocity());
        registerModule(new AutoPot());
        registerModule(new Refill());
        registerModule(new Chams());
        registerModule(new FastPlace());
        registerModule(new FuckServer());
        registerModule(new Projectiles());
        registerModule(new Disabler());
        registerModule(new NameTags());
        registerModule(new ReachAssist());
        registerModule(new DragScreen());
    }
}
