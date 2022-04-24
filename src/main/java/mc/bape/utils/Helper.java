// L Bape, Decompiled by ImCzf233

package mc.bape.utils;

import net.minecraft.client.*;
import mc.bape.vapu.*;
import net.minecraft.util.*;

public class Helper
{
    public static Minecraft mc;
    
    public static void sendMessage(String message) {
        message = "[" + Client.CLIENT_NAME + "] " + message;
        new ChatUtil.ChatMessageBuilder(true, true).appendText(message).setColor(EnumChatFormatting.LIGHT_PURPLE).build().displayClientSided();
    }
    
    public static void Get_information(final String string) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage((IChatComponent)new ChatComponentText(string));
    }
    
    public static boolean onServer(final String server) {
        return !Helper.mc.isSingleplayer() && Helper.mc.getCurrentServerData().serverIP.toLowerCase().contains(server);
    }
    
    public static void sendMessageWithoutPrefix(final String string) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage((IChatComponent)new ChatComponentText(string));
    }
    
    static {
        Helper.mc = Minecraft.getMinecraft();
    }
}
