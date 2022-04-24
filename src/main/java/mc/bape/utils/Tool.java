package mc.bape.utils;

import org.apache.logging.log4j.*;
import org.apache.commons.lang3.exception.*;

public class Tool
{
    public static void displayChatMessage(final String message) {
        Helper.sendMessage(message);
    }
    
    public static void log(final String s, final Object... args) {
        LogManager.getLogger().log(Level.INFO, String.format(s, args));
    }
    
    public static void logerror(final String s, final Object... args) {
        LogManager.getLogger().log(Level.ERROR, String.format(s, args));
    }
    
    public static void logException(final Throwable e) {
        logerror(ExceptionUtils.getStackTrace(e), new Object[0]);
    }
}
