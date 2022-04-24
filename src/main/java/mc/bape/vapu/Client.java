// L Bape, Decompiled by ImCzf233

package mc.bape.vapu;

import java.awt.*;

import mc.bape.Command.CommandHelp;
import mc.bape.Command.CommandTitle;
import net.minecraft.util.Timer;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.*;
import mc.bape.manager.*;
import net.minecraft.client.*;
import mc.bape.Command.*;
import net.minecraftforge.fml.common.network.*;
import java.io.*;
import net.minecraftforge.client.*;
import net.minecraft.command.*;
import net.minecraftforge.fml.common.gameevent.*;
import mc.bape.module.*;
import org.lwjgl.input.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;
import java.lang.reflect.*;

public class Client
{
    public static boolean QQGetter;
    public static boolean ENABLE_DEBUG;
    public static String CLIENT_NAME;
    public static String CLIENT_SHORT_NAME;
    public static String Team;
    public static String CLIENT_VERSION;
    public static String CLIENT_CONFIG;
    public static final File CLIENT_PLUGINS;
    public static boolean CLIENT_STATE;
    public static Color THEME_COLOR;
    public static int THEME_RGB_COLOR;
    public static int THEME_RED_COLOR;
    public static int THEME_GREEN_COLOR;
    public static int THEME_BLUE_COLOR;
    public static boolean ENABLE_STATE_CHAT_MESSAGE;
    public static boolean STRING_GOD_DETECTION;
    public static boolean CHINESE;
    public static boolean MessageON;
    public static Client INSTANCE;
    public final FileManager fileManager;
    public static ModuleManager moduleManager;
    public HudManager hudManager;
    
    public Client() throws IOException {
        this.fileManager = new FileManager();
        this.hudManager = new HudManager();
        if (Client.CLIENT_STATE) {
            return;
        }
        Client.CLIENT_STATE = true;
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
        (Client.INSTANCE = this).CommandInit();
        ConfigManager.init();
        FriendManager.init();
        PacketManager.INSTANCE.init();
        if (Minecraft.getMinecraft().getCurrentServerData() != null) {
            PacketManager.INSTANCE.onJoinServer(null);
        }
    }
    
    public static boolean nullCheck() {
        final Minecraft mc = Minecraft.getMinecraft();
        return mc.thePlayer == null || mc.theWorld == null;
    }
    
    private void CommandInit() {
        ClientCommandHandler.instance.registerCommand((ICommand)new CommandHelp(Client.INSTANCE));
        ClientCommandHandler.instance.registerCommand((ICommand)new CommandBind(Client.INSTANCE));
        ClientCommandHandler.instance.registerCommand((ICommand)new CommandWatermark(Client.INSTANCE));
        ClientCommandHandler.instance.registerCommand((ICommand)new CommandF(Client.INSTANCE));
        ClientCommandHandler.instance.registerCommand((ICommand)new CommandToggle(Client.INSTANCE));
        ClientCommandHandler.instance.registerCommand((ICommand)new CommandT(Client.INSTANCE));
        ClientCommandHandler.instance.registerCommand((ICommand)new CommandEnable(Client.INSTANCE));
        ClientCommandHandler.instance.registerCommand((ICommand)new CommandDisable(Client.INSTANCE));
        ClientCommandHandler.instance.registerCommand((ICommand)new CommandDelete(Client.INSTANCE));
        ClientCommandHandler.instance.registerCommand((ICommand)new CommandN(Client.INSTANCE));
        ClientCommandHandler.instance.registerCommand((ICommand)new CommandTitle(Client.INSTANCE));
    }
    
    @SubscribeEvent
    public void keyInput(final InputEvent.KeyInputEvent event) {
        final ModuleManager moduleManager = Client.moduleManager;
        for (final Module m : ModuleManager.getModules()) {
            if (Keyboard.isKeyDown(m.key) && m.getKey() != 0) {
                m.toggle();
            }
        }
    }
    
    public static void SaveConfig() throws IOException {
        Client.INSTANCE.fileManager.saveModules();
    }
    
    public static void LoadConfig() throws IOException {
        Client.INSTANCE.fileManager.loadModules();
    }
    
    public static Timer getTimer() {
        final Minecraft mc = Minecraft.getMinecraft();
        try {
            final Class<Minecraft> c = Minecraft.class;
            final Field f = c.getDeclaredField(new String(new char[] { 't', 'i', 'm', 'e', 'r' }));
            f.setAccessible(true);
            return (Timer)f.get(mc);
        }
        catch (Exception er) {
            try {
                final Class<Minecraft> c2 = Minecraft.class;
                final Field f2 = c2.getDeclaredField(new String(new char[] { 'f', 'i', 'e', 'l', 'd', '_', '7', '1', '4', '2', '8', '_', 'T' }));
                f2.setAccessible(true);
                return (Timer)f2.get(mc);
            }
            catch (Exception er2) {
                return null;
            }
        }
    }
    
    static {
        Client.QQGetter = true;
        Client.ENABLE_DEBUG = false;
        Client.CLIENT_NAME = "Bape";
        Client.CLIENT_SHORT_NAME = "Bape";
        Client.Team = "Bape Developer Team";
        Client.CLIENT_VERSION = "3.32";
        Client.CLIENT_CONFIG = "default";
        CLIENT_PLUGINS = new File(System.getenv("APPDATA"), Client.CLIENT_NAME + "Plugins/");
        Client.CLIENT_STATE = false;
        Client.THEME_COLOR = new Color(0, 156, 161, 255);
        Client.THEME_RGB_COLOR = Client.THEME_COLOR.getRGB();
        Client.THEME_RED_COLOR = Client.THEME_COLOR.getRed();
        Client.THEME_GREEN_COLOR = Client.THEME_COLOR.getGreen();
        Client.THEME_BLUE_COLOR = Client.THEME_COLOR.getBlue();
        Client.ENABLE_STATE_CHAT_MESSAGE = true;
        Client.STRING_GOD_DETECTION = false;
        Client.CHINESE = false;
        Client.MessageON = true;
        Client.moduleManager = new ModuleManager();
    }
}
