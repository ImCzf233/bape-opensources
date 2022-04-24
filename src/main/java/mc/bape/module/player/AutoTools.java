// L Bape, Decompiled by ImCzf233

package mc.bape.module.player;

import mc.bape.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import mc.bape.utils.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoTools extends Module
{
    public AutoTools() {
        super("AutoTools", 0, ModuleType.Player, "Switch correct tools when you destroy blocks");
        AutoTools.Chinese = "\u81ea\u52a8\u5de5\u5177";
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent event) {
        if (!AutoTools.mc.gameSettings.keyBindAttack.isKeyDown()) {
            return;
        }
        if (AutoTools.mc.objectMouseOver == null) {
            return;
        }
        final BlockPos pos = AutoTools.mc.objectMouseOver.getBlockPos();
        if (pos == null) {
            return;
        }
        BlockUtils.updateTool(pos);
    }
}
