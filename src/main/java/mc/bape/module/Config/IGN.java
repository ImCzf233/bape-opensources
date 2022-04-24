// L Bape, Decompiled by ImCzf233

package mc.bape.module.Config;

import mc.bape.module.*;
import java.awt.*;
import java.awt.datatransfer.*;

public class IGN extends Module
{
    public IGN() {
        super("CopyName", 0, ModuleType.Global, "copy your name for party");
        IGN.Chinese = "\u590d\u5236\u540d\u5b57";
        IGN.NoToggle = true;
    }
    
    @Override
    public void enable() {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(IGN.mc.thePlayer.getName()), null);
        this.state = false;
    }
}
