// L Bape, Decompiled by ImCzf233

package mc.bape.module.world;

import mc.bape.utils.*;
import mc.bape.module.*;
import mc.bape.values.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;

public class ChestStealer extends Module
{
    private Numbers<Double> delay;
    private TimerUtil timer;
    
    public ChestStealer() {
        super("ChestStealer", 0, ModuleType.Blatant, "Auto Steal a chest when you open it");
        this.delay = new Numbers<Double>("Delay", "delay", 50.0, 0.0, 1000.0, 10.0);
        this.timer = new TimerUtil();
        this.addValues(this.delay);
        ChestStealer.Chinese = "\u5feb\u901f\u5237\u7bb1";
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent event) {
        if (!this.getState()) {
            return;
        }
        if (ChestStealer.mc.thePlayer.openContainer != null && ChestStealer.mc.thePlayer.openContainer instanceof ContainerChest) {
            final ContainerChest container = (ContainerChest)ChestStealer.mc.thePlayer.openContainer;
            for (int i = 0; i < container.getLowerChestInventory().getSizeInventory(); ++i) {
                if (container.getLowerChestInventory().getStackInSlot(i) != null && this.timer.hasReached(this.delay.getValue())) {
                    ChestStealer.mc.playerController.windowClick(container.windowId, i, 0, 1, (EntityPlayer)ChestStealer.mc.thePlayer);
                    this.timer.reset();
                }
            }
            if (this.isEmpty()) {
                ChestStealer.mc.thePlayer.closeScreen();
            }
        }
    }
    
    private boolean isEmpty() {
        if (ChestStealer.mc.thePlayer.openContainer != null && ChestStealer.mc.thePlayer.openContainer instanceof ContainerChest) {
            final ContainerChest container = (ContainerChest)ChestStealer.mc.thePlayer.openContainer;
            for (int i = 0; i < container.getLowerChestInventory().getSizeInventory(); ++i) {
                final ItemStack itemStack = container.getLowerChestInventory().getStackInSlot(i);
                if (itemStack != null && itemStack.getItem() != null) {
                    return false;
                }
            }
        }
        return true;
    }
}
