// L Bape, Decompiled by ImCzf233

package mc.bape.module.other;

import mc.bape.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import net.minecraft.entity.player.*;
import mc.bape.utils.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class MCC extends Module
{
    public MCC() {
        super("NameChecker", 0, ModuleType.Other, "Check entity name in you pressed");
        MCC.Chinese = "Debug";
    }
    
    @SubscribeEvent
    public void keyInput(final InputEvent.KeyInputEvent event) {
        if (this.state && Keyboard.isKeyDown(48) && MCC.mc.objectMouseOver.entityHit != null) {
            final EntityPlayer player = (EntityPlayer)MCC.mc.objectMouseOver.entityHit;
            final String playername = player.getName();
            Helper.sendMessage(playername);
            System.out.print(playername);
            System.out.print(player.getDisplayName());
        }
    }
}
