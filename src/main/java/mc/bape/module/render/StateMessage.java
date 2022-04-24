package mc.bape.module.render;

import mc.bape.module.*;
import mc.bape.vapu.*;

public class StateMessage extends Module
{
    public StateMessage() {
        super("NoStateMessage", 0, ModuleType.Render, "Hide Modules State info");
        StateMessage.Chinese = "\u65e0\u5f00\u5173\u4fe1\u606f";
    }
    
    @Override
    public void enable() {
        Client.ENABLE_STATE_CHAT_MESSAGE = true;
    }
    
    @Override
    public void disable() {
        Client.ENABLE_STATE_CHAT_MESSAGE = false;
    }
}
