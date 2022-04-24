// L Bape, Decompiled by ImCzf233

package mc.bape.module.render;

import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraft.client.gui.*;
import mc.bape.gui.MatrixClickGui.*;

public class ClickGUI extends Module
{
    private Mode<Enum> mode;
    public static Mode<Enum> SexyMode;
    
    public ClickGUI() {
        super("ClickGUI", 54, ModuleType.Render, "Open ClickGui");
        this.mode = new Mode<Enum>("Mode", "mode", GuiMode.values(), GuiMode.Wuyutong);
        this.addValues(this.mode, ClickGUI.SexyMode);
        ClickGUI.Chinese = "\u70b9\u51fbGUI";
    }
    
    @Override
    public void enable() {
        this.setState(false);
        if (this.mode.getValue() == GuiMode.Wuyutong) {
            ClickGUI.mc.displayGuiScreen((GuiScreen)new ClickUi());
        }
    }
    
    static {
        ClickGUI.SexyMode = new Mode<Enum>("Mode", "mode", SexyMode1.values(), SexyMode1.zerotwo);
    }
    
    enum GuiMode
    {
        Wuyutong;
    }
    
    public enum SexyMode1
    {
        zerotwo, 
        None, 
        KeQing, 
        Mona, 
        Paimon, 
        Misaka;
    }
}
