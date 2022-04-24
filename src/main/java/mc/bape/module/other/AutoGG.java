// L Bape, Decompiled by ImCzf233

package mc.bape.module.other;

import mc.bape.module.*;
import mc.bape.utils.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoGG extends Module
{
    private int counter;
    private boolean warnAlready;
    static String[] GGs;
    
    public AutoGG() {
        super("AutoGG", 0, ModuleType.Other, "send GG when you press G");
        this.counter = 0;
        this.warnAlready = false;
        AutoGG.Chinese = "\ufffd\ufffd\ufffd\u0536\ufffdGG";
    }
    
    @Override
    public void enable() {
        Helper.sendMessage("Press G to send GG");
    }
    
    @SubscribeEvent
    public void keyInput(final InputEvent.KeyInputEvent event) {
        if (this.state && Keyboard.isKeyDown(34)) {
            final Random r = new Random();
            AutoGG.mc.thePlayer.sendChatMessage(AutoGG.GGs[r.nextInt(AutoGG.GGs.length)] + " --> BapeClient Dot XYZ");
        }
    }
    
    static {
        AutoGG.GGs = new String[] { "GG", "gg", "Gg", "gG" };
    }
}
