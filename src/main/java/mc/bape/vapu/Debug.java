package mc.bape.vapu;

import mc.bape.utils.*;

public class Debug
{
    public static void sendMessage(final String Message) {
        if (Client.ENABLE_DEBUG) {
            Helper.sendMessage(Message);
        }
    }
}
